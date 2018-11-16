package de.hs.da.hskleinanzeigen;

import de.hs.da.hskleinanzeigen.model.Ad;
import de.hs.da.hskleinanzeigen.model.AdType;
import de.hs.da.hskleinanzeigen.model.Category;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdControllerUT {

    @InjectMocks
    private AdController adController;

    @Mock
    private AdRepository adRepository;


    //Check if mockAd in the repository equals with the one that the controller found
    @Test
    void findAdById() throws AdNotFoundException {
    Ad mockAd = Mockito.mock(Ad.class);
    Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));

    assertEquals(adRepository.findById(mockAd.getId()).get(), adController.findAdById(mockAd.getId()));



    }


    //Check if an Exception thrown when an Ad is not found
    @Test
    void findAdByIdNotExist() {

        Ad mockAd = Mockito.mock(Ad.class);
        Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));

        Integer dummyAdId = mockAd.getId();


        assertThrows(AdNotFoundException.class,
                () -> adController.findAdById(9999));

    }
}