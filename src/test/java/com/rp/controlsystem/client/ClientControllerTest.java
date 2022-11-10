package com.rp.controlsystem.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.controlsystem.models.Address;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void should_add_new_client() throws Exception {
        Client c1 = new Client("Jose", "419822342", "j@gmail", new Address("Rua", 10, "Cidade", "Estado", "17526240"));

        mockMvc.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(c1)))
                .andExpect(status().isCreated());
    }

    @Test
    void not_found_when_client_does_not_exist() throws Exception {
        mockMvc.perform(get("/clientes/00")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
