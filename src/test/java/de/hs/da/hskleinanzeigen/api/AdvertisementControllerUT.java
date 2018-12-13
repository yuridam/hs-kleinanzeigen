package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.api.AdvertisementController;
import de.hs.da.hskleinanzeigen.api.model.Advertisement;
import de.hs.da.hskleinanzeigen.exception.AdExceptionInterceptor;
import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
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
class AdvertisementControllerUT {

    @InjectMocks
    private AdvertisementController adController;

    @Mock
    private AdvertisementRepository adRepository;


    //Check if mockAd in the repository equals with the one that the controller found
    @Test
    void findAdById() throws AdExceptionInterceptor {
    AdvertisementEntity mockAd = Mockito.mock(AdvertisementEntity.class);
    Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));

    assertEquals(adRepository.findById(mockAd.getId()).get(), adController.findAdvertisementById((mockAd.getId())));
    }


    //Check if an Exception thrown when an Ad is not found
    @Test
    void findAdByIdNotExist() {

        AdvertisementEntity mockAd = Mockito.mock(AdvertisementEntity.class);
        Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));

        Integer dummyAdId = mockAd.getId();


        assertThrows(AdExceptionInterceptor.class,
                () -> adController.findAdvertisementById(9999));

    }
}