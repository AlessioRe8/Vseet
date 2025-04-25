package it.unicam.ids.Vseet.Model.Services;

import it.unicam.ids.Vseet.Model.Entities.POI.*;
import it.unicam.ids.Vseet.Model.Entities.Projections.POIProjection;
import it.unicam.ids.Vseet.Model.Entities.ContentCategory;
import it.unicam.ids.Vseet.Model.Entities.Itinerary;
import it.unicam.ids.Vseet.Model.Entities.Position;
import it.unicam.ids.Vseet.Model.Entities.User;
import it.unicam.ids.Vseet.Model.Entities.DTO.PointOfInterestDTO;
import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Repositories.ConcretePOIRepository;
import it.unicam.ids.Vseet.Model.Repositories.ContentRepository;
import it.unicam.ids.Vseet.Model.Repositories.ItineraryRepository;
import it.unicam.ids.Vseet.Model.Repositories.LogicPOIRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class POIService {
    private final PointOfInterestFactory pointOfInterestFactory;
    private final ConcretePOIRepository concretePOIRepository;
    private final LogicPOIRepository logicPOIRepository;
    private final UserService userService;
    private final ContentRepository contentRepository;
    private final ItineraryRepository itineraryRepository;

    @Autowired
    public POIService(PointOfInterestFactory pointOfInterestFactory, ConcretePOIRepository concretePOIRepository,
                      LogicPOIRepository logicPOIRepository, UserService userService, ContentRepository contentRepository,
                      ItineraryRepository itineraryRepository) {
        this.pointOfInterestFactory = pointOfInterestFactory;
        this.concretePOIRepository = concretePOIRepository;
        this.logicPOIRepository = logicPOIRepository;
        this.userService = userService;
        this.contentRepository = contentRepository;
        this.itineraryRepository = itineraryRepository;
    }



    public POIProjection getById(Long id) throws ContentNotFoundException {
        if (concretePOIRepository.existsById(id)) {
            return concretePOIRepository.findProjectionByID(id);
        }else if (logicPOIRepository.existsById(id)) {
          return logicPOIRepository.findProjectionByID(id);
        } else throw new ContentNotFoundException();
    }

    public List<POIProjection> getAll() {
        List<POIProjection> pointOfInterests = new ArrayList<>();
        logicPOIRepository.findAllProjectedBy().forEach(pointOfInterests::add);
        concretePOIRepository.findAllProjectedBy().forEach(pointOfInterests::add);
        return pointOfInterests;
    }

    public List<POIProjection> getMyPois(String email) {
        List<POIProjection> pointOfInterests = new ArrayList<>();
        logicPOIRepository.findByCreatorEmail(email).forEach(pointOfInterests::add);
        concretePOIRepository.findByCreatorEmail(email).forEach(pointOfInterests::add);
        return pointOfInterests;
    }

    public PointOfInterest createPointOfInterest(PointOfInterestDTO pointOfInterest, String type) {
        //sets the creator as the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.getByUsername(username);

        PointOfInterest poi = pointOfInterestFactory.createPOI(type, pointOfInterest.getName(),
                pointOfInterest.getDescription(), pointOfInterest.getContentCategory(), user, pointOfInterest.getPosition());
        if (poi instanceof ConcretePointOfInterest) {
            ConcretePointOfInterest concretePOI = (ConcretePointOfInterest) poi;
            contentRepository.save(concretePOI);
            return concretePOIRepository.save(concretePOI);
        } else if (poi instanceof LogicPointOfInterest) {
            LogicPointOfInterest logicPOI = (LogicPointOfInterest) poi;
            contentRepository.save(logicPOI);
            return logicPOIRepository.save(logicPOI);
        } else throw new IllegalArgumentException("Unknown point of interest type.");
    }
    public PointOfInterest createVerifiedPointOfInterest(PointOfInterestDTO pointOfInterest, String type) {
        //sets the creator as the authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.getByUsername(username);

        PointOfInterest poi = pointOfInterestFactory.createPOI(type, pointOfInterest.getName(),
                pointOfInterest.getDescription(), pointOfInterest.getContentCategory(), user, pointOfInterest.getPosition());
        if (poi instanceof ConcretePointOfInterest) {
            ConcretePointOfInterest concretePOI = (ConcretePointOfInterest) poi;
            concretePOI.verify();
            contentRepository.save(concretePOI);
            return concretePOIRepository.save(concretePOI);
        } else if (poi instanceof LogicPointOfInterest) {
            LogicPointOfInterest logicPOI = (LogicPointOfInterest) poi;
            logicPOI.verify();
            contentRepository.save(logicPOI);
            return logicPOIRepository.save(logicPOI);
        } else throw new IllegalArgumentException("Unknown point of interest type.");
    }

    @Transactional
    public void deleteById(Long id) throws ContentNotFoundException {
        List<Itinerary> its = itineraryRepository.findByPoints_ID(id); //TODO Problema del delete se il poi è in un itinerary

    for (Itinerary it : its) {
        it.getPoints().removeIf(p -> p.getID().equals(id));
        itineraryRepository.save(it);
    }
        if (concretePOIRepository.existsById(id)) {
            contentRepository.deleteById(id);
            concretePOIRepository.deleteById(id);
        } else if (logicPOIRepository.existsById(id)) {
            contentRepository.deleteById(id);
            logicPOIRepository.deleteById(id);
        } else {
            throw new ContentNotFoundException();
        }
    }

    public PointOfInterest updatePoi(Long id, PointOfInterestDTO dto) throws ContentNotFoundException{
        if (logicPOIRepository.existsById(id)) {
            LogicPointOfInterest poi = logicPOIRepository.findById(id).orElseThrow();
            poi.setName(dto.getName());
            poi.setDescription(dto.getDescription());
            poi.setContentCategory(dto.getContentCategory());
            poi.setPosition(new Position(dto.getPosition().getLatitude(), dto.getPosition().getLongitude()));
            return logicPOIRepository.save(poi);
        }
        if (concretePOIRepository.existsById(id)) {
            ConcretePointOfInterest poi = concretePOIRepository.findById(id).orElseThrow();
            poi.setName(dto.getName());
            poi.setDescription(dto.getDescription());
            poi.setContentCategory(dto.getContentCategory());
            poi.setPosition(new Position(dto.getPosition().getLatitude(), dto.getPosition().getLongitude()));
            return concretePOIRepository.save(poi);
        }
        else throw new ContentNotFoundException();
    }
    
     @PostConstruct
    public void loadPois() {
        concretePOIRepository.save(new ConcretePointOfInterest("Bar", "Un bar", ContentCategory.ENTERTAINMENT, userService.getByUsername("alessio.re@example.it"), new Position(34.0,55.30)));
        concretePOIRepository.save(new ConcretePointOfInterest("Museo","Un museo archeologico", ContentCategory.CULTURE, userService.getByUsername("alessio.re@example.it"), new Position(28.0, 5.0)));
    } 
}
