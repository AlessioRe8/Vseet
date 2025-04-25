package it.unicam.ids.Vseet.Controller;

import it.unicam.ids.Vseet.Model.Exceptions.ContentNotFoundException;
import it.unicam.ids.Vseet.Model.Services.ContentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;

    public ContentController(ContentService contentService){
        this.contentService = contentService;
    }

    @PreAuthorize("hasAnyAuthority('PLATFORM_MANAGER', 'ANIMATOR', 'CURATOR')")
    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam("id") Long id) throws ContentNotFoundException {
        return new ResponseEntity<>(contentService.verify(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(contentService.getAllContentsProjectedBy(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('PLATFORM_MANAGER') || hasRole('CURATOR') || hasRole('ANIMATOR')")
    @GetMapping("/unverified")
    public ResponseEntity<?> getAllUnverified() {
        return new ResponseEntity<>(contentService.getAllUnverified(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws ContentNotFoundException{
        return new ResponseEntity<>(contentService.getById(id), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> edit() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping()
    public ResponseEntity<?> delete() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
