package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.persistence.*;
import de.hs.da.hskleinanzeigen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

@SpringBootApplication
public class HsKleinanzeigenApplication {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotepadRepository notepadRepository;

    public static void main(String[] args) {
        SpringApplication.run(HsKleinanzeigenApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        Calendar cal = Calendar.getInstance();
        Timestamp time = Timestamp.valueOf("2019-01-17 16:26:38.00000");
        cal.setTimeInMillis(time.getTime());

        System.out.println("Initializing... Filling database with data");

        insertMainCategory("Bücher");
        insertMainCategory("Wohnung");
        insertMainCategory("Elektronik");
        insertSubCategory("WG", "Wohnung");
        insertSubCategory("Handy", "Elektronik");

        insertAd("Offer", "WG", "1-Zimmer in 4er-WG", "Lorem ipsum", 300, "Darmstadt", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertAd("Offer", "Handy", "Samsung S11", "Lorem ipsum", 1500, "Frankfurt", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertAd("Offer", "WG", "Wohnung im UG", "Lorem ipsum", 500, "Darmstadt", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertAd("Request", "Bücher", "Deutsch A1", "Lorem ipsum", 0, "Wiesbaden", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertAd("Request", "Elektronik", "Amazon Echo Dot", "Lorem ipsum", 0, "Darmstadt", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertAd("Offer", "WG", "1-Zimmer in 10-er WG", "Lorem ipsum", 150, "Mannheim", Timestamp.valueOf("2019-01-17 16:26:38.00000"));

        for (int i = 0; i < 1000; i++) {
            Integer sec = 1;
            cal.add(Calendar.SECOND, sec);
            time = new Timestamp(cal.getTime().getTime());
            insertUser("mail" + i + "@mustermail.de",
                    "dummy_pass_" + i,
                    "User" + i,
                    "Synthesis" + i,
                    "0176" + i,
                    "Musterstadt",
                    time);
        }

        insertNotepad(1, 1, "Nette Mitbewohner", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
        insertNotepad(1, 2, "Günstig", Timestamp.valueOf("2019-01-17 16:26:38.00000"));
    }

    @SuppressWarnings("Duplicates")
    void insertMainCategory(String name) {
        Integer existingId = null;
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();
        for (CategoryEntity category : allCategories) {
            if (category.getName().contains(name))
                existingId = category.getId();
        }
        CategoryEntity newCat = new CategoryEntity();
        newCat.setId(existingId);
        newCat.setName(name);
        categoryRepository.save(newCat);
    }

    @SuppressWarnings("Duplicates")
    void insertSubCategory(String name, String parentName) {
        Integer existingId = null;
        CategoryEntity parentCat = null;
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();

        for (CategoryEntity category : allCategories) {
            if (category.getName().contains(parentName))
                parentCat = category;
        }

        for (CategoryEntity category : allCategories) {
            if (category.getName().contains(name))
                existingId = category.getId();
        }

        CategoryEntity newCat = new CategoryEntity();
        newCat.setId(existingId);
        newCat.setParentId(parentCat);
        newCat.setName(name);
        categoryRepository.save(newCat);
    }

    @SuppressWarnings("Duplicates")
    void insertAd(String type, String category, String title, String description, float price, String location, Timestamp created) {

        CategoryEntity catObj = null;
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();
        for (CategoryEntity cat : allCategories) {
            if (cat.getName().equals(category)) {
                catObj = cat;
            }
        }

        Integer existingId = null;
        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();
        for (AdvertisementEntity advertisement : allAdvertisements) {
            if (advertisement.getTitle().equals(title)) {
                existingId = advertisement.getId();
            }
        }

        if (type.equals("Offer")) {
            OfferEntity newOffer = new OfferEntity();
            newOffer.setId(existingId);
            newOffer.setCategory(catObj);
            newOffer.setTitle(title);
            newOffer.setDescription(description);
            newOffer.setPrice(price);
            newOffer.setLocation(location);
            newOffer.setCreated(created);
            offerRepository.save(newOffer);
        } else if (type.equals("Request")) {
            RequestEntity newRequest = new RequestEntity();
            newRequest.setId(existingId);
            newRequest.setCategory(catObj);
            newRequest.setTitle(title);
            newRequest.setDescription(description);
            newRequest.setLocation(location);
            newRequest.setCreated(created);
            requestRepository.save(newRequest);
        } else {
            System.out.println("Advertisement type not defined!");
        }
    }

    void insertUser(String email, String password, String first_name, String last_name, String phone, String location, Timestamp created) {
        Integer existingId = null;
        Iterable<UserEntity> allUsers = userRepository.findAll();

        for (UserEntity user : allUsers) {
            if (user.getEmail().equals(email)) {
                existingId = user.getId();
            }
        }

        UserEntity newUser = new UserEntity();
        newUser.setId(existingId);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFirst_name(first_name);
        newUser.setLast_name(last_name);
        newUser.setPhone(phone);
        newUser.setLocation(location);
        newUser.setCreated(created);
        userRepository.save(newUser);
    }

    void insertNotepad(Integer user_id, Integer ad_id, String note, Timestamp created) {

        Integer existingNoteId = null;
        Iterable<NotepadEntity> allNotes = notepadRepository.findAll();

        for (NotepadEntity notepad : allNotes) {
            if (notepad.getId().equals(ad_id)) {
                existingNoteId = notepad.getId();
            }
        }

        UserEntity userObj = null;
        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user : allUsers) {
            if (user.getId().equals(user_id)) {
                userObj = user;
            }
        }

        AdvertisementEntity adObj = null;
        Iterable<AdvertisementEntity> allAds = advertisementRepository.findAll();
        for (AdvertisementEntity ad : allAds) {
            if (ad.getId().equals(ad_id)) {
                adObj = ad;
            }
        }

        NotepadEntity newNote = new NotepadEntity();
        newNote.setId(existingNoteId);
        newNote.setUser(userObj);
        newNote.setAd(adObj);
        newNote.setNote(note);
        newNote.setCreated(created);
        notepadRepository.save(newNote);
    }
}