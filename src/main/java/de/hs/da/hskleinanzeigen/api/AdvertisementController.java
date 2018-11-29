package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;



    // Get Advertisement details
    @GetMapping(produces = "application/json", path = "/advertisements/{id}")
    public @ResponseBody
    AdvertisementEntity findAdvertisementById(@PathVariable Integer id) throws AdExceptionInterceptor {
        return advertisementRepository.findById(id).orElseThrow(() -> new AdExceptionInterceptor(id));
    }

    // Create new Advertisement
    @PostMapping(consumes = "application/json", path = "/advertisements")
    AdvertisementEntity insertAdvertisement(@RequestBody final AdvertisementEntity newAdvertisement) {
        return advertisementRepository.save(newAdvertisement);
    }

    // Get all Advertisements
    @GetMapping(path = "/alladvertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getAllAds() {
        return advertisementRepository.findAll();
    }

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

    // Test Path
    @GetMapping(path = "/test")
    public @ResponseBody
    String getTest() {
        return "Test";
    }


}