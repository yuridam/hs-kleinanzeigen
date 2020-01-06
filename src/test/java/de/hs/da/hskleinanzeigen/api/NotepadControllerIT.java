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
class NotepadControllerIT {

    @Autowired
    NotepadController notepadController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertNewNotepad() throws Exception {
        String payload = "{\n" +
                "   \"ad\": {\n" +
                "   \t\"id\": 2\n" +
                "   },\n" +
                "   \"note\": \"Günstig und in gutem Zustand\"\n" +
                "}";
        Integer userId = 10;
        String uri = "/api/users/" + userId + "/notepad";
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(payload))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getNotepadFromUser() throws Exception {
        String payload = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"user\": {\n" +
                "            \"id\": 1,\n" +
                "            \"email\": \"mail0@mustermail.de\",\n" +
                "            \"password\": \"dummy_pass_0\",\n" +
                "            \"first_name\": \"User0\",\n" +
                "            \"last_name\": \"Synthesis0\",\n" +
                "            \"phone\": \"01760\",\n" +
                "            \"location\": \"Musterstadt\",\n" +
                "            \"created\": \"2019-01-18T00:09:05.490+0000\"\n" +
                "        },\n" +
                "        \"ad\": {\n" +
                "            \"id\": 2,\n" +
                "            \"type\": \"Offer\",\n" +
                "            \"category\": {\n" +
                "                \"id\": 5,\n" +
                "                \"name\": \"Handy\",\n" +
                "                \"parentId\": {\n" +
                "                    \"id\": 3,\n" +
                "                    \"name\": \"Elektronik\",\n" +
                "                    \"parentId\": null\n" +
                "                }\n" +
                "            },\n" +
                "            \"title\": \"Samsung S11\",\n" +
                "            \"description\": \"Lorem ipsum\",\n" +
                "            \"location\": \"Frankfurt\",\n" +
                "            \"created\": \"2019-01-17T15:26:38.000+0000\",\n" +
                "            \"price\": 1500\n" +
                "        },\n" +
                "        \"note\": \"Günstig und in gutem Zustand\",\n" +
                "        \"created\": \"2019-01-18T00:24:09.667+0000\"\n" +
                "    }\n" +
                "]";
        Integer userId = 1; // Input ID to get the Notepads
        String uri = "/api/users/" + userId + "/notepad";
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json(payload));
    }

    @Test
    public void deleteNotepad() throws Exception {
        Integer userId = 1; // Input User ID to choose
        Integer notepadId = 1; // Input Notepad ID to delete
        String uri = "/api/users/" + userId + "/notepad/" + notepadId;
        mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }
}