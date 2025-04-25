package it.unicam.ids.Vseet.Model.Repositories;

import it.unicam.ids.Vseet.Model.Entities.POI.LogicPointOfInterest;
import it.unicam.ids.Vseet.Model.Entities.Projections.POIProjection;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogicPOIRepository extends CrudRepository<LogicPointOfInterest, Long> {
    List<POIProjection> findAllProjectedBy();
    List<POIProjection> findByCreatorEmail(String email);
    POIProjection findProjectionByID(Long ID);
}
