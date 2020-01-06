package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.repository.AdvertisementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdvertisementControllerUT {

    @InjectMocks
    private AdvertisementController adController;

    @Mock
    private AdvertisementRepository adRepository;

    // Check if mockAd in the repository equals with the one that the controller found
    @Test
    void findAdById() {
        AdvertisementEntity mockAd = Mockito.mock(AdvertisementEntity.class);
        Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));
        assertEquals(adRepository.findById(mockAd.getId()).get(), adController.findAdvertisementById((mockAd.getId())));
    }

    // Check if an Exception is thrown when mockAd is not found
    @Test
    void findAdByIdNotExist() {
        AdvertisementEntity mockAd = Mockito.mock(AdvertisementEntity.class);
        Mockito.when(adRepository.findById(mockAd.getId())).thenReturn(java.util.Optional.ofNullable(mockAd));

//        Integer mockAdId = mockAd.getId(); // mockAdId = 0
//        System.out.println("mockAdId: " + mockAdId);
        assertThrows(ResponseStatusException.class,() -> adController.findAdvertisementById(99));

        Integer dummyAdId = mockAd.getId();


        assertThrows(ResponseStatusException.class,
                () -> adController.findAdvertisementById(dummyAdId));

    }

}