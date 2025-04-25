package it.unicam.ids.Vseet.Model.Services;

import it.unicam.ids.Vseet.Model.Entities.*;
import it.unicam.ids.Vseet.Model.Entities.DTO.ItineraryDTO;
import it.unicam.ids.Vseet.Model.Entities.Projections.ItineraryProjection;
import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Repositories.ContentRepository;
import it.unicam.ids.Vseet.Model.Repositories.ItineraryRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final UserService userService;
    private final ContentRepository contentRepository;
    private final ContentService contentService;

    public ItineraryService(ItineraryRepository itineraryRepository, ContentRepository contentRepository,
                            UserService userService, ContentService contentService) {
        this.itineraryRepository = itineraryRepository;
        this.contentRepository = contentRepository;
        this.userService = userService;
        this.contentService = contentService;
    }

    public Itinerary create(ItineraryDTO itDTO) {
        //sets the creator as the authenticated user.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.getByUsername(username);

        Itinerary itinerary = new Itinerary(itDTO.getName(), itDTO.getDescription(),user, itDTO.getContentCategory());
        contentRepository.save(itinerary);
        return itineraryRepository.save(itinerary);
    }

    public Itinerary createVerified(ItineraryDTO itDTO) {
        //sets the creator as the authenticated user.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = ((UserDetails) auth.getPrincipal()).getUsername();
        User user = userService.getByUsername(username);

        Itinerary itinerary = new Itinerary(itDTO.getName(), itDTO.getDescription(),user, itDTO.getContentCategory());
        itinerary.verify();
        contentRepository.save(itinerary);
        return itineraryRepository.save(itinerary);
    }

    public List<ItineraryProjection> getAll() {
        return itineraryRepository.findAllProjectedBy();
    }

    public ItineraryProjection getItineraryById(Long id) throws ContentNotFoundException{
        return itineraryRepository.findProjectedById(id).orElseThrow(ContentNotFoundException::new);
    }

    public List<ItineraryProjection> getMyItineraries(String email) {
        return itineraryRepository.findByCreatorEmail(email);
    }

    public void deleteItinerary(Long id) throws ContentNotFoundException {
        if (!itineraryRepository.existsById(id)) {
            throw new ContentNotFoundException();
        } else {
            contentRepository.deleteById(id);
            itineraryRepository.deleteById(id);
        }
    }

    public Itinerary addContent(Long idItinerary, Long idContent) throws ContentNotFoundException{
        Content content = contentService.getById(idContent);
        Itinerary itinerary = itineraryRepository.findById(idItinerary)
            .orElseThrow(ContentNotFoundException::new);

        if (content instanceof Itinerary) {
            Itinerary sourceItinerary = (Itinerary) content;
            addPointsFromItinerary(itinerary, sourceItinerary);

        } else {
            if(!itinerary.getPoints().contains(content)) {
            itinerary.addContent(content);        
            }
    }
    itineraryRepository.save(itinerary);
    contentRepository.save(itinerary);
    return itinerary;
}

     private void addPointsFromItinerary(Itinerary targetItinerary, Itinerary sourceItinerary) throws ContentNotFoundException{
        for (Content point : sourceItinerary.getPoints()) {
            if (!targetItinerary.getPoints().contains(point)) {
                targetItinerary.addContent(point);;
            }
        }
    }

     public Itinerary updateItinerary(Long id, ItineraryDTO dto) throws ContentNotFoundException{
            Itinerary itinerary = itineraryRepository.findById(id).orElseThrow(ContentNotFoundException::new);
            itinerary.setName(dto.getName());
            itinerary.setDescription(dto.getDescription());
            itinerary.setContentCategory(dto.getContentCategory());
            return itineraryRepository.save(itinerary); 
     }
}

