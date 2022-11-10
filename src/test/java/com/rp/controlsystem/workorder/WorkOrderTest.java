package com.rp.controlsystem.workorder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.controlsystem.models.Address;
import com.rp.controlsystem.models.Client;
import com.rp.controlsystem.models.Equipment;
import com.rp.controlsystem.models.WorkOrder;
import com.rp.controlsystem.models.enums.Status;
import com.rp.controlsystem.repositories.ClientRepository;
import com.rp.controlsystem.repositories.WorkOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkOrderTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void not_found_when_workOrder_does_not_exist() throws Exception {
        mockMvc.perform(get("/ordens/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_add_new_workOrder() throws Exception {

    }
}
