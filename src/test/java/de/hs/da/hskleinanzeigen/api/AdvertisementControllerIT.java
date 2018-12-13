package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.api.AdvertisementController;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import de.hs.da.hskleinanzeigen.type.AdvertisementType;
import de.hs.da.hskleinanzeigen.persistence.CategoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.Timestamp;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

class AdControllerIT {



    @Mock
    public AdvertisementRepository adRepository;

    @Autowired
    AdvertisementController adController;

    @Autowired
    private MockMvc mockMvc;




    @Test
    public void getExistingAd() throws Exception {

        Integer newAdId = 1; //write id to search
        String uri = "/api/advertisements/" + newAdId;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"id\":1,\"type\":\"Offer\",\"title\":\"WG Zimmer\",\"description\":\"teuer\",\"price\":2000.0,\"location\":\"Darmstadt\",\"category\":{\"id\":1,\"name\":\"Wohnung\",\"parent_id\":null},\"created\":\"2018-11-08T15:20:46.000+0000\"}\n" +
                        ""));
    }

    @Test
    public void createNewAd() throws Exception {
        String json = "{\n" +
                "    \"type\": \"Offer\",\n" +
                "    \"category\": {\n" +
                "        \"id\": 4,\n" +
                "        \"name\": \"WG\",\n" +
                "        \"parentId\": {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"Wohnung\",\n" +
                "            \"parentId\": null\n" +
                "        }\n" +
                "    },\n" +
                "    \"title\": \"1 Zimmer in 6er WG\",\n" +
                "    \"description\": \"sehr guenstig\",\n" +
                "    \"price\": 200,\n" +
                "    \"location\": \"Darmstadt\"\n" +
                "}";
        String uri = "/api/advertisements";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print())
                ;

    }



}