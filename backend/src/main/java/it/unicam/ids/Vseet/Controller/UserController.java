package it.unicam.ids.Vseet.Controller;

import it.unicam.ids.Vseet.Model.Entities.User;
import it.unicam.ids.Vseet.Model.Entities.DTO.RoleUpdateRequest;
import it.unicam.ids.Vseet.Model.Entities.Projections.UserProjection;
import it.unicam.ids.Vseet.Model.Exceptions.UserNotFoundException;
import it.unicam.ids.Vseet.Model.Services.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProjection> authenticatedUser(Authentication authentication) {
        String email = authentication.getName();
        UserProjection user = userService.getUserDetailsByEmail(email);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasAuthority('PLATFORM_MANAGER')")
    @GetMapping()
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }

    @PreAuthorize("hasAuthority('PLATFORM_MANAGER')")
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("id") Long id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<?> editUserRole(@PathVariable Long id, @RequestBody RoleUpdateRequest req) throws UserNotFoundException{
        userService.editUserRole(id, req.getRole());
        return ResponseEntity.ok().build();
    }
}

/* @RestController
@PreAuthorize("hasRole('PLATFORM_MANAGER')")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

/*     @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user) {
        User savedUser = userService.create(user);
        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } */
/* 
    @GetMapping("/search")
    public ResponseEntity<?> getById(@RequestParam("id") Long id) throws UserNotFoundException{
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(),HttpStatus.OK);
    } */

/*     @PutMapping("/roles")
    public ResponseEntity<?> editUserRole(@RequestParam("id") Long id,
                                          @RequestParam("newRole") UserRole newRole) throws UserNotFoundException {
        User user = userService.editUserRole(id, newRole);
        return new ResponseEntity<>(user, HttpStatus.OK);
    } */


/* 
    @PutMapping
    public ResponseEntity<?> edit(Long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    } */
