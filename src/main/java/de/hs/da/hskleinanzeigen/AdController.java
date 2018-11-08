package de.hs.da.hskleinanzeigen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import de.hs.da.hskleinanzeigen.model.Ad;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/api")
public class AdController {
    @Autowired
    private AdRepository adRepository;

    //Ads query
    @GetMapping(path = "/advertisements/{id}")
    public @ResponseBody Map getAd(@PathVariable Integer id){
        Map result = new HashMap();
        result.put("result", adRepository.findById(id));

        return result;

    }

    //Get all Ads
    @GetMapping(path = "/advertisements")
    public @ResponseBody Iterable<Ad> getAllAds(){
        return adRepository.findAll();

    }


    @GetMapping(path = "/test")
    public @ResponseBody String getTest(){
        return "wtf";
    }



    @PostMapping(path = "/advertisements", produces = "application/json")
    public @ResponseBody Ad insertAd (@RequestBody Ad adBody) {

        return adRepository.save(adBody);
    }
}