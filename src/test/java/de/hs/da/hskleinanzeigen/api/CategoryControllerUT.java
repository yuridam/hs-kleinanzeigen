package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryControllerUT {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryRepository categoryRepository;

    // Check if mockCat in the repository equals with the one that controller found
    @Test
    void findCategoryById() {
        CategoryEntity mockCat = Mockito.mock(CategoryEntity.class);
        Mockito.when(categoryRepository.findById(mockCat.getId())).thenReturn(java.util.Optional.ofNullable(mockCat));
        assertEquals(categoryRepository.findById(mockCat.getId()).get(), categoryController.findCategoryById(mockCat.getId()));
    }

    // Check if an Exception is thrown when mockCat is not found
    @Test
    void findCategoryByIdNotExist() {
        CategoryEntity mockCat = Mockito.mock(CategoryEntity.class);
        Mockito.when(categoryRepository.findById(mockCat.getId())).thenReturn(java.util.Optional.ofNullable(mockCat));
//        Integer mockCatId = mockCat.getId();
//        System.out.println("mockCatId: " + mockCatId);
        assertThrows(ResponseStatusException.class, () -> categoryController.findCategoryById(99));
    }
}