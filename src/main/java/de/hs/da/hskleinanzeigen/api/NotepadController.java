package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.NotepadEntity;
import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(produces = "application/json", path = "/users/{userid}/notepad")
    public @ResponseBody
    Iterable<NotepadEntity> findNotepadByUserId(@PathVariable Integer userid){

        Integer notepadID = null;
        Iterable<NotepadEntity> allNotepads = notepadRepository.findAll();
        Iterable<NotepadEntity> foundNotepads = new ArrayList<>();
        for (NotepadEntity notepad:allNotepads
             ) {
            if (notepad.getUser().getId().equals(userid))
                //notepadID = notepad.getId();
                ((ArrayList<NotepadEntity>) foundNotepads).add(notepad);
        }
        return foundNotepads;
    }


    //Insert new Notepad
    @PostMapping(consumes = "application/json", path = "/users/{userid}/notepad")
    NotepadEntity insertNotepad(@PathVariable Integer userid,@RequestBody final NotepadEntity newNotepad) {

        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user:allUsers
        ) {
            if (user.getId().equals(userid))
                newNotepad.setUser(user);
        }

        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();
        for (AdvertisementEntity ad:allAdvertisements
        ) {
            if (ad.getId().equals(newNotepad.getAd().getId()))
                newNotepad.setAd(ad);
        }


        //newNotepad.setUser();
        return notepadRepository.save(newNotepad);
    }


    @DeleteMapping(path = "/users/{userid}/notepad/{notepadid}")
    public ResponseEntity deleteNotepad(@PathVariable Integer userid, @PathVariable Integer notepadid) {

        Iterable<NotepadEntity> allNotepads = notepadRepository.findAll();

        for (NotepadEntity notepad:allNotepads
        ) {
            if (notepad.getUser().getId().equals(userid) && notepad.getId().equals(notepadid)){
                notepadRepository.delete(notepad);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
}
        }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
