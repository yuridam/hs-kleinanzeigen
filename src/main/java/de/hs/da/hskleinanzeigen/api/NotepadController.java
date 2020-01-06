package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.NotepadEntity;
import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class NotepadController {

    @Autowired
    NotepadRepository notepadRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

    // Get a specific Notepad details from a User
    @GetMapping(produces = "application/json", path = "/users/1/notepad/{noteId]")
    public @ResponseBody
    NotepadEntity findNotepadById(@PathVariable Integer noteId) {
        return notepadRepository.findById(noteId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Notepad not found"));
    }

    // Get all Notepad details of a User
    @GetMapping(produces = "application/json", path = "/users/{userId}/notepad")
    public @ResponseBody
    Iterable<NotepadEntity> findNotepadByUserId(@PathVariable Integer userId) {

        boolean isUser = false;
        Iterable<NotepadEntity> allNotepads = notepadRepository.findAll();
        Iterable<NotepadEntity> foundNotepads = new ArrayList<>();

        for (NotepadEntity notepad : allNotepads) {
            if (notepad.getUser().getId().equals(userId))
                isUser = true;
                ((ArrayList<NotepadEntity>) foundNotepads).add(notepad);
        }

        Integer size = ((ArrayList<NotepadEntity>) foundNotepads).size();

        // Handle various cases
        if (size > 0 && isUser) {
            return foundNotepads;
        } else if (size == 0 && !isUser){
            // User not found
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "User not found");
        } else {
            // User found but don't have any Notepads
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "Notepad is empty");
        }
    }

    // Create a new Notepad
    @PostMapping(consumes = "application/json", path = "/users/{userId}/notepad")
    ResponseEntity insertNotepad(@PathVariable Integer userId, @RequestBody final NotepadEntity newNotepad) {

        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user : allUsers) {
            if (user.getId().equals(userId))
                newNotepad.setUser(user);
        }

        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();
        for (AdvertisementEntity ad : allAdvertisements) {
            if (ad.getId().equals(newNotepad.getAd().getId()))
                newNotepad.setAd(ad);
        }

//        newNotepad.setUser();
        try {
            return new ResponseEntity<>(notepadRepository.save(newNotepad), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete payload: User or advertisement not found", ex);
        }
    }

    @DeleteMapping(path = "/users/{userId}/notepad/{notepadId}")
    public ResponseEntity deleteNotepad(@PathVariable Integer userId, @PathVariable Integer notepadId) {

        boolean isDeleted = false;
        Iterable<NotepadEntity> allNotepads = notepadRepository.findAll();

        for (NotepadEntity notepad : allNotepads) {
            if (notepad.getUser().getId().equals(userId) && notepad.getId().equals(notepadId)) {
                notepadRepository.delete(notepad);
                isDeleted = true;
            }
        }

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted");
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User or advertisement not found");
        }
    }

}
