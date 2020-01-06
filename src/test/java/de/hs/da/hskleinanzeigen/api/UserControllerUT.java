package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerUT {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Test
    void findUserById() {
        UserEntity mockUser = Mockito.mock(UserEntity.class);
        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(java.util.Optional.ofNullable(mockUser));
        assertEquals(userRepository.findById(mockUser.getId()).get(), userController.findUserById(mockUser.getId()));
    }

    @Test
    void findUserByIdNotExist() {
        UserEntity mockUser = Mockito.mock(UserEntity.class);
        Mockito.when(userRepository.findById(mockUser.getId())).thenReturn(java.util.Optional.ofNullable(mockUser));
        assertThrows(ResponseStatusException.class, () -> userController.findUserById(99));
    }
}