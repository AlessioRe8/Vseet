package it.unicam.ids.Vseet.Model.Repositories;

import it.unicam.ids.Vseet.Model.Entities.Itinerary;
import it.unicam.ids.Vseet.Model.Entities.Projections.ItineraryProjection;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends CrudRepository<Itinerary, Long> {
    List<ItineraryProjection> findAllProjectedBy();


    @Query("SELECT i FROM Itinerary i WHERE i.id = :id")
    Optional<ItineraryProjection> findProjectedById(@Param("id") Long id);
    List<ItineraryProjection> findByCreatorEmail(String email);


    List<Itinerary> findByPoints_ID(Long poiID);
}
