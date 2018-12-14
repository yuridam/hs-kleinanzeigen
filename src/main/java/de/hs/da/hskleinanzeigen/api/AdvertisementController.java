package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.OfferEntity;
import de.hs.da.hskleinanzeigen.persistence.RequestEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.OfferRepository;
import de.hs.da.hskleinanzeigen.repository.RequestRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static java.sql.Types.NULL;

@RestController
@RequestMapping(path = "/api")
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private RequestRepository requestRepository;





    // Get Advertisement details
    @GetMapping(produces = "application/json", path = "/advertisements/{id}")
    public @ResponseBody
    AdvertisementEntity findAdvertisementById(@PathVariable Integer id) throws AdExceptionInterceptor {
        return advertisementRepository.findById(id).orElseThrow(() -> new AdExceptionInterceptor(id));
    }

    // Create new Advertisement
    @PostMapping(consumes = "application/json", path = "/advertisements")
    AdvertisementEntity insertAdvertisement(@RequestBody final AdvertisementEntity newAd) {

        if(newAd.getPrice() > 0.0) {
            OfferEntity newOffer = new OfferEntity();
            newOffer.setTitle(newAd.getTitle());
            newOffer.setPrice(newAd.getPrice());
            newOffer.setCategory(newAd.getCategory());
            newOffer.setCreated(newAd.getCreated());
            newOffer.setLocation(newAd.getLocation());
            newOffer.setDescription(newAd.getDescription());
            return offerRepository.save(newOffer);
        }

        else {
            RequestEntity newRequest = new RequestEntity();
            newRequest.setTitle(newAd.getTitle());
            newRequest.setPrice(NULL);
            newRequest.setCategory(newAd.getCategory());
            newRequest.setCreated(newAd.getCreated());
            newRequest.setLocation(newAd.getLocation());
            newRequest.setDescription(newAd.getDescription());
            return requestRepository.save(newRequest);
        }
    }



    // Get all Advertisements
    @GetMapping(path = "/alladvertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getAllAds() {
        return advertisementRepository.findThemAll();
    }

    /*
    // Search an Advertisement
    @GetMapping(path = "/advertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getDetails(
            @RequestParam(value = "type", required = false, defaultValue = "null") AdvertisementType type,
            @RequestParam(value = "title", required = false, defaultValue = "null") String title,
            @RequestParam(value = "category", required = false, defaultValue = "null") Integer category,
            @RequestParam(value = "location", required = false, defaultValue = "null") String location) {

        Iterable<AdvertisementEntity> foundAdvertisements = new ArrayList();
        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();

        for (AdvertisementEntity _advertisement : allAdvertisements) {
            if (_advertisement.getLocation().contains(location)
                    && _advertisement.getTitle().contains(title)
                    && _advertisement.getType().equals(type)) {
                ((ArrayList<AdvertisementEntity>) foundAdvertisements).add(_advertisement);
            }
        }
        return foundAdvertisements;
    }
    */

    // Test Path
    @GetMapping(path = "/test")
    public @ResponseBody
    String getTest() {
        return "Test";
    }


}