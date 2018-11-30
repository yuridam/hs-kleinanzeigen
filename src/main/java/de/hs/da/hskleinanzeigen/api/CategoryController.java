package de.hs.da.hskleinanzeigen.api;


import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {


    @Autowired
    private CategoryRepository categoryRepository;


    // Create new Category
    @PostMapping(consumes = "application/json", path = "/categories")
    CategoryEntity insertCategory(@RequestBody final CategoryEntity newCategory) {

        //return categoryRepository.save(newCategory);
        try {
            return categoryRepository.save(newCategory);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete data", ex);
        }
    }

    @GetMapping(produces = "application/json", path = "/categories/{id}")
    public @ResponseBody
    CategoryEntity findCategoryById(@PathVariable Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Incomplete data"));
    }
}