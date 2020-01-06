package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.persistence.*;
import de.hs.da.hskleinanzeigen.repository.*;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


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


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("kontol bgst");
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();

        addCategory("Buecher");
        addCategory("Wohnung");
        addCategory("Elektronik");
        addSubCategory("WG", "Wohnung");
        addSubCategory("Handy", "Elektronik");


        addAds("1 Zimmer in 4er WG", "Darmstadt", (float) 300, "sehr guenstig", "Offer", "WG");
        addAds("Samsung S11", "Frankfurt", (float) 1500, "prototype model", "Offer", "Handy");
        addAds("Wohnung im Untergeschoss", "Darmstadt", (float) 500, "zwischenmieter gesucht", "Offer", "WG");
        addAds("Deutsch A1", "Wiesbaden", (float) 0, "looking for this book", "Request", "Buecher");
        addAds("Amazon Echo Dot", "Darmstadt", (float) 0, "dringend", "Request", "Elektronik");
        addAds("1 Zimmer in 10er WG", "Mannheim", (float) 150, "nur fuer Manner", "Offer", "WG");


        for (int i = 0; i < 1000 ; i++) {

            addUser("test"+i+"@testmail.com",
                    "passtest"+i,
                    "Test"+i,
                    "Testman"+1,
                    "0152"+i,
                    "Darmstadt");
        }
    }


    void addCategory(String name) {
        Integer existingid = null;
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();
        for (CategoryEntity category : allCategories) {
            if (category.getName().contains(name))
                existingid = category.getId();
        }
        CategoryEntity newCat = new CategoryEntity();
        newCat.setId(existingid);
        newCat.setName(name);
        categoryRepository.save(newCat);

    }

    void addSubCategory(String name, String parentName) {
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


    void addAds(String title, String location, Float price, String desc, String type, String category) {
        Integer existingid = null;
        CategoryEntity catObj = null;
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();
        for (CategoryEntity cat : allCategories) {
            if (cat.getName().contains(category))
                catObj = cat;
        }

        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();
        for (AdvertisementEntity advertisement : allAdvertisements) {
            if (advertisement.getTitle().contains(title))
                existingid = advertisement.getId();
        }


        if(type.equals("Offer")) {
            OfferEntity newOffer = new OfferEntity();
            newOffer.setId(existingid);
            newOffer.setTitle(title);
            newOffer.setPrice(price);
            newOffer.setCategory(catObj);
            newOffer.setLocation(location);
            newOffer.setDescription(desc);
            offerRepository.save(newOffer);
        }

        else {
            RequestEntity newRequest = new RequestEntity();
            newRequest.setId(existingid);
            newRequest.setTitle(title);
            newRequest.setCategory(catObj);
            newRequest.setLocation(location);
            newRequest.setDescription(desc);
            requestRepository.save(newRequest);
        }
    }

    void addUser(String email, String password, String first_name, String last_name, String phone, String location){
        Integer existingid = null;
        Iterable<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user : allUsers) {
            if (user.getEmail().contains(email))
                existingid = user.getId();
        }

        UserEntity newUser = new UserEntity();
        newUser.setId(existingid);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setFirst_name(first_name);
        newUser.setLast_name(last_name);
        newUser.setPhone(phone);
        newUser.setLocation(location);
        userRepository.save(newUser);
    }


    public static void main(String[] args) {

        SpringApplication.run(HsKleinanzeigenApplication.class, args);

    }
}