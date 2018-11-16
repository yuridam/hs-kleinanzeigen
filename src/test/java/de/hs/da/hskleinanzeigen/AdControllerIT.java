package de.hs.da.hskleinanzeigen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc

public class AdControllerIT {

    @Autowired
    AdRepository adRepository;

    @Autowired
    AdController adController;

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void getExistingAd() throws Exception {

        String uri = "/api/advertisements/1";
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"id\":1,\"type\":\"Offer\",\"title\":\"4er WG\",\"description\":\"schön\",\"price\":555.0,\"location\":\"ffm\",\"category\":{\"id\":1,\"name\":\"Wohnung\",\"parent_id\":1},\"created\":\"2018-11-20T12:00:00.000+0000\"}"));
    }

    @Test
    public void getNonExistingAd() throws Exception {

        String uri = "/api/advertisements/455";
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.ALL))
                .andExpect(status().is5xxServerError());

         }
}