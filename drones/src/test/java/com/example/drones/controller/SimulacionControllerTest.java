package com.example.drones.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.drones.application.usecase.SimulacionService;
import com.example.drones.domain.model.Dron;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SimulacionController.class)
class SimulacionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimulacionService simu;

    @Autowired
    private ObjectMapper objectMapper;

    private Dron dron;

    @BeforeEach
    void setup(){
        dron = new Dron();
        dron.setId(1L);
        dron.setNombre("Dron A");
        dron.setModelo("Modelo");
    }

    @Test
    void find_by_id() throws Exception{
        when(simu.findDronById(1L)).thenReturn(dron);
        mockMvc.perform(get("/api/find/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(dron.getNombre()));
    }
}
