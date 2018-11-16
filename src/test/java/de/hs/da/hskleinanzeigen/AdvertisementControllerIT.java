package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.api.AdvertisementController;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
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

public class AdvertisementControllerIT {

    @Autowired
    AdvertisementRepository advertisementRepository;

    @Autowired
    AdvertisementController advertisementController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingAd() throws Exception {
        String uri = "/api/advertisements/5";
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"id\":5,\"type\":\"Offer\",\"category\":{\"id\":5,\"parentId\":3,\"name\":\"Schuhe\"},\"title\":\"Nike Air Huarache Gr. 45\",\"description\":\"Guter Zustand, selten getragen.\",\"price\":40.0,\"location\":\"Darmstadt\",\"created\":\"2018-11-16T15:42:23.592+0000\"}"));
    }

    /*
    @Test
    public void getNonExistingAd() throws Exception {
        String uri = "/api/advertisements/455";
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.ALL))
                .andExpect(status().is5xxServerError());
    }
    */
}