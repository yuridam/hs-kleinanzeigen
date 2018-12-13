package de.hs.da.hskleinanzeigen.api;

import de.hs.da.hskleinanzeigen.persistence.UserEntity;
import de.hs.da.hskleinanzeigen.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserControllerUT {


    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;


    @Test
    void newUser() {

        UserEntity testUser = new UserEntity();
        testUser.setFirst_name("Testy");
        testUser.setLast_name("Testman");
        testUser.setEmail("test@testmail.com");
        testUser.setPassword("test");
        testUser.setLocation("Test Town");
        testUser.setPhone("12345");

        userController.newUser(testUser);

    }
}