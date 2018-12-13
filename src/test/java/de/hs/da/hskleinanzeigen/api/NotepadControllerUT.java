package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.AdvertisementEntity;
import de.hs.da.hskleinanzeigen.persistence.NotepadEntity;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NotepadControllerUT {


    @InjectMocks
    private NotepadController notepadController;

    @Mock
    private NotepadRepository noteRepository;


}