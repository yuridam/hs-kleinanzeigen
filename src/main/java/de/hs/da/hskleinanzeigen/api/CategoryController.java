package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/api")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Create a new Category
    @PostMapping(consumes = "application/json", path = "/categories")
    CategoryEntity insertCategory(@RequestBody final CategoryEntity newCategory) {

        try {
            return categoryRepository.save(newCategory);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Incomplete data or parent category not found", ex);
        }
    }

    // Get a specific Category details
    @GetMapping(produces = "application/json", path = "/categories/{id}")
    public @ResponseBody
    CategoryEntity findCategoryById(@PathVariable Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Category not found"));
    }

    // Get all Categories
    @GetMapping(path = "/allcategories")
    public @ResponseBody
    Iterable<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}