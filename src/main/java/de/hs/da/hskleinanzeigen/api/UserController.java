package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.exception.UserExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

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
                return new ResponseEntity(HttpStatus.CONFLICT);
            }

        }

        //return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
        try {
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete data", ex);
        }
    }


    @GetMapping(produces = "application/json", path = "/users/{id}")
    public @ResponseBody
    UserEntity findUserById(@PathVariable Integer id) throws UserExceptionInterceptor {
        return userRepository.findById(id).orElseThrow(() -> new UserExceptionInterceptor(id));
    }

    @GetMapping(produces = "application/json", path = "/users")
    Page<UserEntity> list(Pageable pageable,
                          @RequestParam(value = "page", required = true) Integer page,
                          @RequestParam(value = "size", required = true) Integer size,
                          @RequestParam(value = "sort", defaultValue = "created") String sort){

        Page<UserEntity> persons = userRepository.findAll(pageable);
        return persons;
    }


}