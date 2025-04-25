package it.unicam.ids.Vseet.Model.Services;

import it.unicam.ids.Vseet.Model.Entities.User;
import it.unicam.ids.Vseet.Model.Entities.UserRole;
import it.unicam.ids.Vseet.Model.Entities.Projections.UserProjection;
import it.unicam.ids.Vseet.Model.Exceptions.UserNotFoundException;
import it.unicam.ids.Vseet.Model.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public UserProjection getUserDetailsByEmail(String email) {
        return userRepository.getUserDetailsByEmail(email);

    }
    public User getByUsername(String username){
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void deleteById(Long id) throws UserNotFoundException{
        if(!userRepository.existsById(id)) throw new UserNotFoundException();
        userRepository.deleteById(id);
    }

    public User editUserRole(Long id, UserRole newRole) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.setRole(newRole);
        return userRepository.save(user);
    } 

    @PostConstruct
    public void loadUsers() {
        User user1 = new User("alessio.re@example.it", passwordEncoder.encode("password"),  UserRole.PLATFORM_MANAGER);
        User user2 = new User("andrea.bianchi@example.it", passwordEncoder.encode("password"),  UserRole.ANIMATOR);
        User user3 = new User("mario.rossi@example.it", passwordEncoder.encode("password"),  UserRole.CONTRIBUTOR);
        User user4 = new User("anna.verdi@example.it", passwordEncoder.encode("password"),  UserRole.TOURIST);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }


/*

    public User getByUsername(String username){
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public User getById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }



*/


}
