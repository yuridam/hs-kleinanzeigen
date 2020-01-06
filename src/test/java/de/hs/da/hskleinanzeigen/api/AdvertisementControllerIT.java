package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.validation.constraints.Null;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdvertisementControllerIT {

    @Autowired
    AdvertisementController adController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingAd() throws Exception {
        String payload = "{\n" +
                "    \"id\": 1,\n" +
                "    \"category\": {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"WG\",\n" +
                "        \"parentId\": {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"Wohnung\",\n" +
                "            \"parentId\": null\n" +
                "        }\n" +
                "    },\n" +
                "    \"title\": \"1-Zimmer in 4er-WG\",\n" +
                "    \"description\": \"Lorem ipsum\",\n" +
                "    \"location\": \"Darmstadt\",\n" +
                "    \"created\": \"2019-01-17T15:26:38.000+0000\",\n" +
                "    \"price\": 300\n" +
                "}";
        Integer newAdId = 1; // Input ID to search
        String uri = "/api/advertisements/" + newAdId;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    public void createNewAd() throws Exception {
        // To test create a new "Request" Advertisement, delete price from json payload
        String payload = "{\n" +
                "    \"category\": {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Elektronik\",\n" +
                "        \"parentId\": null\n" +
                "    },\n" +
                "    \"title\": \"Samsung OLED 8K\",\n" +
                "    \"description\": \"Lorem ipsum\",\n" +
                "    \"location\": \"Berlin\",\n" +
                "    \"price\": 7999\n" +
                "}";
        String uri = "/api/advertisements";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void searchAd() throws Exception {
        // @RequestParams, uses String because it doesn't matter for the uri
        String type = "Offer";
        String title = "";
        String category = "5";
        String location = "Frankfurt";
        String minPrice = "";
        String maxPrice = "";
        String payload = "[\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"type\": \"Offer\",\n" +
                "        \"category\": {\n" +
                "            \"id\": 5,\n" +
                "            \"name\": \"Handy\",\n" +
                "            \"parentId\": {\n" +
                "                \"id\": 3,\n" +
                "                \"name\": \"Elektronik\",\n" +
                "                \"parentId\": null\n" +
                "            }\n" +
                "        },\n" +
                "        \"title\": \"Samsung S11\",\n" +
                "        \"description\": \"Lorem ipsum\",\n" +
                "        \"location\": \"Frankfurt\",\n" +
                "        \"created\": \"2019-01-17T15:26:38.000+0000\",\n" +
                "        \"price\": 1500\n" +
                "    }\n" +
                "]";
        String uri = "/api/advertisements?type=" + type
                + "&title=" + title
                + "&category=" + category
                + "&location=" + location
                + "&minPrice=" + minPrice
                + "&maxPrice=" + maxPrice;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }
}