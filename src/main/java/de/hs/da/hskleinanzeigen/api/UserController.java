package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.exception.UserExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;



    // Create new Advertisement
    @PostMapping(consumes = "application/json", path = "/users")
    UserEntity newUser(@RequestBody final UserEntity newUser) {
        return userRepository.save(newUser);
    }


    @GetMapping(produces = "application/json", path = "/users/{id}")
    public @ResponseBody
    UserEntity findUserById(@PathVariable Integer id) throws UserExceptionInterceptor {
        return userRepository.findById(id).orElseThrow(() -> new UserExceptionInterceptor(id));
    }

}