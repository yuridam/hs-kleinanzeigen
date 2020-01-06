package de.hs.da.hskleinanzeigen.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerIT {

    @Autowired
    CategoryController categoryController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingCategory() throws Exception {
        String payload = "{\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Elektronik\",\n" +
                "    \"parentId\": null\n" +
                "}";
        Integer newCatId = 3;
        String uri = "/api/categories/" + newCatId;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    public void createNewCategory() throws Exception {
        String payload = "{\n" +
                "    \"name\": \"Kamera\",\n" +
                "    \"parentId\": {\n" +
                "    \t\"id\": 3\n" +
                "    }\n" +
                "}";
        String uri = "/api/categories";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }
}