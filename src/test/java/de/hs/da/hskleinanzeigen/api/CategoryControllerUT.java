package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryControllerUT {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void newCategory() {

        CategoryEntity testCategory = new CategoryEntity();
        testCategory.setName("Test Category");

        categoryController.insertCategory(testCategory);

    }
}