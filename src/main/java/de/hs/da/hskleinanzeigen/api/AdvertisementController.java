package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.persistence.OfferEntity;
import de.hs.da.hskleinanzeigen.persistence.RequestEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.OfferRepository;
import de.hs.da.hskleinanzeigen.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class AdvertisementController {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private RequestRepository requestRepository;

    // Get an Advertisement details
    @GetMapping(produces = "application/json", path = "/advertisements/{id}")
    public @ResponseBody
    AdvertisementEntity findAdvertisementById(@PathVariable Integer id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Advertisement not found"));
    }

    // Create a new Advertisement, all entities will be assumed as "Offer" first
    @SuppressWarnings("Duplicates")
    @PostMapping(consumes = "application/json", path = "/advertisements")
    public ResponseEntity insertAdvertisement(@RequestBody final OfferEntity newAd) {
        // If price is set, then save it to the offerRepository
        try {
            if (newAd.getPrice() > 0.0) {
                OfferEntity newOffer = new OfferEntity();
                newOffer.setCategory(newAd.getCategory());
                newOffer.setTitle(newAd.getTitle());
                newOffer.setDescription(newAd.getDescription());
                newOffer.setPrice(newAd.getPrice());
                newOffer.setLocation(newAd.getLocation());
                newOffer.setCreated(newAd.getCreated());
                return new ResponseEntity<>(offerRepository.save(newOffer), HttpStatus.CREATED);
            }
            // If no price is set, then save it to the requestRepository
            else {
                RequestEntity newRequest = new RequestEntity();
                newRequest.setCategory(newAd.getCategory());
                newRequest.setTitle(newAd.getTitle());
                newRequest.setDescription(newAd.getDescription());
                newRequest.setLocation(newAd.getLocation());
                newRequest.setCreated(newAd.getCreated());
                return new ResponseEntity<>(requestRepository.save(newRequest), HttpStatus.CREATED);
            }
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete payload", ex);
        }
    }

    // Search an Advertisement, how it works: User must first
    // determine whether they're searching for "Offer" or "Request"
    @SuppressWarnings("Duplicates")
    @GetMapping(path = "/advertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getDetails(
            @RequestParam(value = "type", required = false, defaultValue = "null") String type,
            @RequestParam(value = "title", required = false, defaultValue = "null") String title,
            @RequestParam(value = "category", required = false, defaultValue = "") CategoryEntity category,
            @RequestParam(value = "location", required = false, defaultValue = "null") String location,
            @RequestParam(value = "minPrice", required = false, defaultValue = "0") float minPrice,
            @RequestParam(value = "maxPrice", required = false, defaultValue = "999999") float maxPrice) {

        Iterable<AdvertisementEntity> foundAdvertisements = new ArrayList();
        Iterable<OfferEntity> allOffers = offerRepository.findAll();
        Iterable<RequestEntity> allRequests = requestRepository.findAll();

        boolean isCategory;
        boolean isTitle;
        boolean isLocation;
        boolean isPrice;

        // Handle search for "Offer"
        if (type.equals("Offer")) {
            for (OfferEntity offer : allOffers) {

                if (offer.getCategory().equals(category) || category == null) {
                    isCategory = true;
                } else {
                    isCategory = false;
                }

                if (offer.getTitle().contains(title) || title.equals("null")) {
                    isTitle = true;
                } else {
                    isTitle = false;
                }

                if (offer.getLocation().contains(location) || location.equals("null")) {
                    isLocation = true;
                } else {
                    isLocation = false;
                }

                if (minPrice <= offer.getPrice() && offer.getPrice() <= maxPrice) {
                    isPrice = true;
                } else {
                    isPrice = false;
                }

                if (isCategory && isTitle && isLocation && isPrice) {
                    ((ArrayList<AdvertisementEntity>) foundAdvertisements).add(offer);
                }
            }
        }
        // Handle search for "Request"
        else if (type.equals("Request")) {
            for (RequestEntity request : allRequests) {

                if (request.getCategory().equals(category) || category == null) {
                    isCategory = true;
                } else {
                    isCategory = false;
                }

                if (request.getTitle().contains(title) || title.equals("null")) {
                    isTitle = true;
                } else {
                    isTitle = false;
                }

                if (request.getLocation().contains(location) || location.equals("null")) {
                    isLocation = true;
                } else {
                    isLocation = false;
                }

                if (isCategory && isTitle && isLocation) {
                    ((ArrayList<AdvertisementEntity>) foundAdvertisements).add(request);
                }
            }
        }

        Integer size = ((ArrayList<AdvertisementEntity>) foundAdvertisements).size();

        // Return search results
        if (size > 0) {
            return foundAdvertisements;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "No advertisement found");
        }

    }

    /*
    -------------------
    NON ESSENTIAL PATHS
    -------------------
     */

    // Get all Advertisements
    @GetMapping(path = "/alladvertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getAllAds() {
        return advertisementRepository.findAll();
    }

    // Test Path
    @GetMapping(path = "/test")
    public @ResponseBody
    String getTest() {
        return "Path /test is working";
    }

}