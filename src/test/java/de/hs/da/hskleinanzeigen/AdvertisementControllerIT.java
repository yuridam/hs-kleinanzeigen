package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.model.Ad;
import de.hs.da.hskleinanzeigen.model.AdType;
import de.hs.da.hskleinanzeigen.model.Category;
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

public class AdControllerIT {



    @Mock
    AdRepository adRepository;

    @Autowired
    AdController adController;

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
        String json = "{\"type\":\"Offer\",\"title\":\"Laptop Racer\",\"description\":\"mit ssd\",\"price\":1000.0,\"location\":\"Wiesbaden\",\"category\":{\"id\":14}}";
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