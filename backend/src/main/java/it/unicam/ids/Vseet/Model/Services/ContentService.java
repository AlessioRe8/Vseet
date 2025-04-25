package it.unicam.ids.Vseet.Model.Services;

import it.unicam.ids.Vseet.Model.Entities.Content;
import it.unicam.ids.Vseet.Model.Entities.Itinerary;
import it.unicam.ids.Vseet.Model.Entities.POI.ConcretePointOfInterest;
import it.unicam.ids.Vseet.Model.Entities.POI.LogicPointOfInterest;
import it.unicam.ids.Vseet.Model.Entities.Projections.ContentProjection;
import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Repositories.ConcretePOIRepository;
import it.unicam.ids.Vseet.Model.Repositories.ContentRepository;
import it.unicam.ids.Vseet.Model.Repositories.ItineraryRepository;
import it.unicam.ids.Vseet.Model.Repositories.LogicPOIRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {
    private final ContentRepository contentRepository;
    private final LogicPOIRepository logicPOIRepository;
    private final ConcretePOIRepository concretePOIRepository;
    public final ItineraryRepository itineraryRepository;

    public ContentService(ContentRepository contentRepository, LogicPOIRepository logicPOIRepository,
                          ConcretePOIRepository concretePOIRepository, ItineraryRepository itineraryRepository) {
        this.contentRepository = contentRepository;
        this. logicPOIRepository = logicPOIRepository;
        this.concretePOIRepository = concretePOIRepository;
        this.itineraryRepository = itineraryRepository;
    }


    public Content verify(Long id) throws ContentNotFoundException {

        if (contentRepository.findById(id).orElseThrow() instanceof LogicPointOfInterest) {
            Content content = contentRepository.findById(id).orElseThrow();
            content.verify();
            logicPOIRepository.save((LogicPointOfInterest) content);
            contentRepository.save(content);
            return content;

        } else if (contentRepository.findById(id).orElseThrow() instanceof ConcretePointOfInterest) {
            Content content = contentRepository.findById(id).orElseThrow();
            content.verify();
            concretePOIRepository.save((ConcretePointOfInterest) content);
            contentRepository.save(content);
            return content;

        } else if (contentRepository.findById(id).orElseThrow() instanceof Itinerary) {
            Content content = contentRepository.findById(id).orElseThrow();
            content.verify();
            itineraryRepository.save((Itinerary) content);
            contentRepository.save(content);
            return content;

        } else {
            throw new ContentNotFoundException();
        }
    }

    public List<Content> getAll() {
        List<Content> contents = new ArrayList<>();
        contentRepository.findAll().forEach(contents::add);
        return contents;
    }

    public List<ContentProjection> getAllContentsProjectedBy() {
        return contentRepository.findAllProjectedBy();
    }

    public List<Content> getAllUnverified() {
        List<Content> contents = new ArrayList<>();
        contentRepository.findAll().forEach(content -> {
            if (!content.isVerified()) contents.add(content);
        });
        return contents;
    }

    public Content getById(Long id) throws ContentNotFoundException{
            return contentRepository.findById(id).orElseThrow(ContentNotFoundException::new);
    }
}
