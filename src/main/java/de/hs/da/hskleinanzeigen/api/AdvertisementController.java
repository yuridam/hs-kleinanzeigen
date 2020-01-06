package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.api.model.Advertisement;
import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.OfferEntity;
import de.hs.da.hskleinanzeigen.persistence.RequestEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.BaseAdvertisementRepository;
import de.hs.da.hskleinanzeigen.repository.OfferRepository;
import de.hs.da.hskleinanzeigen.repository.RequestRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import org.hibernate.type.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    AdvertisementEntity findAdvertisementById(@PathVariable Integer id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Not Found"));
    }

    // Create new Advertisement
    @PostMapping(consumes = "application/json", path = "/advertisements")
    AdvertisementEntity insertAdvertisement(@RequestBody OfferEntity newAd) {

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
        return advertisementRepository.findAll();
    }


    // Search an Advertisement
    @GetMapping(path = "/advertisements")
    public @ResponseBody
    Iterable<AdvertisementEntity> getDetails(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) Integer category,
            @RequestParam(value = "location", required = false) String location) {

        Iterable<AdvertisementEntity> foundAdvertisements = new ArrayList();
        Iterable<AdvertisementEntity> allAdvertisements = advertisementRepository.findAll();
        Iterable<OfferEntity> allOffers = offerRepository.findAll();
        Iterable<RequestEntity> allRequests = requestRepository.findAll();


        if(type.equals("Offer")) {
            for (OfferEntity offer : allOffers) {
                if (offer.getLocation().contains(location)
                        && offer.getTitle().contains(title)
                ) {
                    ((ArrayList<AdvertisementEntity>) foundAdvertisements).add(offer);
                }
            }
            return foundAdvertisements;
        }

        if(type.equals("Request")){
            for (RequestEntity request : allRequests) {
                if (request.getLocation().contains(location)
                        && request.getTitle().contains(title)
                ) {
                    ((ArrayList<AdvertisementEntity>) foundAdvertisements).add(request);
                }
            }

            return foundAdvertisements;
        }


        for (AdvertisementEntity _advertisement : allAdvertisements) {
            if (_advertisement.getLocation().contains(location)
                    && _advertisement.getTitle().contains(title)
                    ) {
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