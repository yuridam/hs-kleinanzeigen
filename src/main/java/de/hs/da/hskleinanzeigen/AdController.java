package de.hs.da.hskleinanzeigen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import de.hs.da.hskleinanzeigen.model.Ad;
import de.hs.da.hskleinanzeigen.AdRepository;

import java.util.Optional;

@Controller
@RequestMapping(path="/api")
public class AdController {
    @Autowired
    private AdRepository adRepository;

    @GetMapping(path = "/advertisements/{id}", produces = "application/json")
    public @ResponseBody Ad getAd(@PathVariable Integer id){
        Optional<Ad> byId = adRepository.findById(id);
        return byId.get();
    }

    @PostMapping(path = "/advertisements", produces = "application/json")
    public @ResponseBody Ad insertAd (@RequestBody Ad adBody) {

        return adRepository.save(adBody);
    }
}