package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
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

    /*
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("kontol bgst");
        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();

        addCategory("Buecher");
        addCategory("Wohnung");
        addCategory("Elektronik");
        addSubCategory("WG", "Wohnung");
        addSubCategory("Handy", "Elektronik");


        addAds("1 Zimmer in 4er WG", "Darmstadt", (float) 300, "sehr guenstig", AdvertisementType.Offer, "WG");
        addAds("Samsung S11", "Frankfurt", (float) 1500, "prototype model", AdvertisementType.Offer, "Handy");
        addAds("Wohnung im Untergeschoss", "Darmstadt", (float) 500, "zwischenmieter gesucht", AdvertisementType.Offer, "WG");
        addAds("Deutsch A1", "Wiesbaden", (float) 50, "looking for this book", AdvertisementType.Request, "Buecher");
        addAds("Amazon Echo Dot", "Darmstadt", (float) 50, "dringend", AdvertisementType.Request, "Elektronik");
        addAds("1 Zimmer in 10er WG", "Mannheim", (float) 150, "nur fuer Manner", AdvertisementType.Offer, "WG");


    }
    */

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

    /*
    void addAds(String title, String location, Float price, String desc, AdvertisementType type, String category) {
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
        AdvertisementEntity newAd = new AdvertisementEntity();
        newAd.setId(existingid);
        newAd.setTitle(title);
        newAd.setLocation(location);
        newAd.setPrice(price);
        newAd.setDescription(desc);
        newAd.setType(type);
        newAd.setCategory(catObj);
        advertisementRepository.save(newAd);

    }
*/

    public static void main(String[] args) {

        SpringApplication.run(HsKleinanzeigenApplication.class, args);

    }
}