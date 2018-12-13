package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.api.model.Category;
import de.hs.da.hskleinanzeigen.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIT {

    @Mock
    CategoryRepository categoryRepository;

    @Autowired
    CategoryController categoryController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createNewCategory() throws Exception {
        String json = "{\n" +
                "    \"name\": \"Test\",\n" +
                "    \"parentId\": null\n" +
                "}";
        String uri = "/api/categories";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());

    }

}