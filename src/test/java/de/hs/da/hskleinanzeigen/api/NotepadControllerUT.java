package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.NotepadEntity;
import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.NotepadRepository;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
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
    private NotepadRepository notepadRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    void findNotepadById() {
        UserEntity mockUser = Mockito.mock(UserEntity.class);
        NotepadEntity mockNote = Mockito.mock(NotepadEntity.class);

        Mockito.when(notepadRepository.findById(mockNote.getId())).thenReturn(java.util.Optional.ofNullable((mockNote)));
        assertEquals(notepadRepository.findById(mockNote.getId()).get(), notepadController.findNotepadById(mockNote.getId()));
    }
}