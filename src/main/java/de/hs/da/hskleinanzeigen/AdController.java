package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.model.AdType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.hs.da.hskleinanzeigen.model.Ad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class AdController {
    @Autowired
    private AdRepository adRepository;

    //an Ad details
    @GetMapping(produces="application/json", path = "/advertisements/{id}")
    public @ResponseBody Ad finAdById(@PathVariable Integer id) throws AdNotFoundException {

        return adRepository.findById(id).orElseThrow(() -> new AdNotFoundException(id));

    }


    //Create new Ad
    @PostMapping(consumes="application/json", path = "/advertisements/")
    Ad createAd (@RequestBody final Ad newAd) {

        return adRepository.save(newAd);

    }

    //Get all Ads
    @GetMapping(path = "/alladvertisements")
    public @ResponseBody Iterable<Ad> getAllAds(){
        return adRepository.findAll();
    }

    //Find Ads
    @GetMapping(path = "/advertisements")
    public @ResponseBody Iterable<Ad> getDetails(
                             @RequestParam(value="type", required=false) AdType type,
                             @RequestParam(value="description", required=false) String description,
                             @RequestParam(value="category", required=false) String category,
                             @RequestParam(value="location", required=false) String location){
        String kontol = null;
        Iterable<Ad> foundAds = new ArrayList();
        Iterable<Ad> allAds = adRepository.findAll();
        for (Ad ad1: allAds
             ) {
            if (
                    (ad1.getLocation().contains(location)) &&
                    ad1.getDescription().contains(description) &&
                    ad1.getType().equals(type)
                            ){
                ((ArrayList<Ad>) foundAds).add(ad1);

            }
        }


        return foundAds;

    }



    @GetMapping(path = "/test")
    public @ResponseBody String getTest(){
        return "wtf";
    }




}