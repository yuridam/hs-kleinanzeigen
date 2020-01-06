package de.hs.da.hskleinanzeigen.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getExistingUser() throws Exception {
        String payload = "{\n" +
                "    \"id\": 1,\n" +
                "    \"email\": \"mail0@mustermail.de\",\n" +
                "    \"password\": \"dummy_pass_0\",\n" +
                "    \"first_name\": \"User0\",\n" +
                "    \"last_name\": \"Synthesis0\",\n" +
                "    \"phone\": \"01760\",\n" +
                "    \"location\": \"Musterstadt\",\n" +
                "    \"created\": \"2019-01-17T15:26:39.000+0000\"\n" +
                "}";
        Integer newUserId = 1; // Input ID to search
        String uri = "/api/users/" + newUserId;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    public void createNewUser() throws Exception {
        String json = "{\n" +
                "   \"email\": \"email@domain.com\",\n" +
                "   \"password\": \"verystrongpassword\",\n" +
                "   \"first_name\": \"Max\",\n" +
                "   \"last_name\": \"Mustermann\",\n" +
                "   \"phone\": \"01761234\",\n" +
                "   \"location\": \"Musterstadt\"\n" +
                "}";
        String uri = "/api/users";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void getUserPage() throws Exception {
        String page = "0";
        String size = "5";
        String payload = "{\n" +
                "    \"content\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"mail0@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_0\",\n" +
                "            \"first_name\": \"User0\",\n" +
                "            \"last_name\": \"Synthesis0\",\n" +
                "            \"phone\": \"01760\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-17T15:26:39.000+0000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"email\": \"mail1@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_1\",\n" +
                "            \"first_name\": \"User1\",\n" +
                "            \"last_name\": \"Synthesis1\",\n" +
                "            \"phone\": \"01761\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-17T15:26:40.000+0000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"email\": \"mail2@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_2\",\n" +
                "            \"first_name\": \"User2\",\n" +
                "            \"last_name\": \"Synthesis2\",\n" +
                "            \"phone\": \"01762\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-17T15:26:41.000+0000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"email\": \"mail3@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_3\",\n" +
                "            \"first_name\": \"User3\",\n" +
                "            \"last_name\": \"Synthesis3\",\n" +
                "            \"phone\": \"01763\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-17T15:26:42.000+0000\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 5,\n" +
                "            \"email\": \"mail4@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_4\",\n" +
                "            \"first_name\": \"User4\",\n" +
                "            \"last_name\": \"Synthesis4\",\n" +
                "            \"phone\": \"01764\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-17T15:26:43.000+0000\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"pageable\": {\n" +
                "        \"sort\": {\n" +
                "            \"unsorted\": true,\n" +
                "            \"sorted\": false,\n" +
                "            \"empty\": true\n" +
                "        },\n" +
                "        \"offset\": 0,\n" +
                "        \"pageNumber\": 0,\n" +
                "        \"pageSize\": 5,\n" +
                "        \"paged\": true,\n" +
                "        \"unpaged\": false\n" +
                "    },\n" +
                "    \"totalPages\": 201,\n" +
                "    \"last\": false,\n" +
                "    \"totalElements\": 1001,\n" +
                "    \"size\": 5,\n" +
                "    \"number\": 0,\n" +
                "    \"sort\": {\n" +
                "        \"unsorted\": true,\n" +
                "        \"sorted\": false,\n" +
                "        \"empty\": true\n" +
                "    },\n" +
                "    \"numberOfElements\": 5,\n" +
                "    \"first\": true,\n" +
                "    \"empty\": false\n" +
                "}";
        String uri = "/api/users?page=" + page + "&size=" + size;
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }
}