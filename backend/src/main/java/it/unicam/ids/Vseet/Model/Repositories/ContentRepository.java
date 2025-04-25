package it.unicam.ids.Vseet.Model.Repositories;

import it.unicam.ids.Vseet.Model.Entities.Content;
import it.unicam.ids.Vseet.Model.Entities.Projections.ContentProjection;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends CrudRepository<Content, Long> { 
    List<ContentProjection> findAllProjectedBy();
}
