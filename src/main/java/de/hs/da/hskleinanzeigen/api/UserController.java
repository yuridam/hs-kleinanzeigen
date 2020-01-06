package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Create new user
    @PostMapping(consumes = "application/json", path = "/users")
    public ResponseEntity newUser(@RequestBody @Validated final UserEntity newUser) {
        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user : allUsers) {
            if (user.getEmail().equals(newUser.getEmail())) {
//                return new ResponseEntity(HttpStatus.CONFLICT);
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
            }
        }

//        return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
        try {
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete payload", ex);
        }
    }

    // Get a specific User details
    @GetMapping(produces = "application/json", path = "/users/{id}")
    public @ResponseBody
    UserEntity findUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User not found"
        ));
    }

    // Page and sort Users
    @GetMapping(produces = "application/json", path = "/users")
    Page<UserEntity> list(Pageable pageable,
                          @RequestParam(value = "page"/*, defaultValue = "NULL"*/) Integer page,
                          @RequestParam(value = "size"/*, defaultValue = "NULL"*/) Integer size,
                          @RequestParam(value = "sort", defaultValue = "created") String sort) {
        Page<UserEntity> persons = userRepository.findAll(pageable);


        if (!persons.isEmpty()) {
            return persons;
        } else if (persons.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "User database is empty");
        } /* else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete payload");
        } */
        return persons;
    }

}