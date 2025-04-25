package it.unicam.ids.Vseet.Controller;

import it.unicam.ids.Vseet.Model.Entities.DTO.PointOfInterestDTO;
import it.unicam.ids.Vseet.Model.Entities.POI.PointOfInterest;
import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Services.POIService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pois")
public class POIController {

    private final POIService poiService;

    @Autowired
    public POIController(POIService poiService) {
        this.poiService = poiService;
    }

   // @PreAuthorize("hasRole('CONTRIBUTOR') || hasRole('TOURIST') || hasRole('PLATFORM_MANAGER') ||" +
    //        " hasRole('ANIMATOR') || hasRole('AUTHORIZED_CONTRIBUTOR') || hasRole('CURATOR')")
    @PostMapping("/create")
    public ResponseEntity<?> createUnverified(@RequestPart("poi") PointOfInterestDTO pointOfInterest,
                                    @RequestParam("type") String type) {
        PointOfInterest poi = poiService.createPointOfInterest(pointOfInterest, type);
        if (poi != null) {
            return new ResponseEntity<>(poi, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //@PreAuthorize("hasRole('PLATFORM_MANAGER') || hasRole('ANIMATOR') || hasRole('AUTHORIZED_CONTRIBUTOR') || hasRole('CURATOR')")
    @PostMapping("/create/verified")
    public ResponseEntity<?> createVerified(@RequestPart("poi") PointOfInterestDTO pointOfInterest,
                                            @RequestParam("type") String type) {
        PointOfInterest poi = poiService.createVerifiedPointOfInterest(pointOfInterest, type);
        if (poi != null) {
            return new ResponseEntity<>(poi, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws ContentNotFoundException {
        return new ResponseEntity<>(poiService.getById(id), HttpStatus.OK);
    }
    
    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(poiService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/contributions")
    public ResponseEntity<?> getMyPois(Authentication authentication) {
        String email = authentication.getName();
        return new ResponseEntity<>(poiService.getMyPois(email), HttpStatus.OK);
}


    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) throws ContentNotFoundException{
        poiService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
public ResponseEntity<?> updatePoi(@PathVariable Long id,
                                   @RequestBody PointOfInterestDTO updatedPoi) throws ContentNotFoundException {
    return new ResponseEntity<>(poiService.updatePoi(id, updatedPoi), HttpStatus.OK);
}
}
