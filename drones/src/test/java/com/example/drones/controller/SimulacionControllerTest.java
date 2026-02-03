package com.example.drones.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.drones.application.usecase.SimulacionService;
import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.example.drones.dto.DronRequest;
import com.example.drones.dto.SimulacionRequest;
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

    void setup() {
        dron = new Dron();
        dron.setId(1L);
        dron.setNombre("Dron A");
        dron.setModelo("Modelo");
        dron.setX(5);
        dron.setY(5);
    }

    // Test Find By Id

    @Test
    void find_by_id() throws Exception {
        when(simu.findDronById(1L)).thenReturn(dron);
        mockMvc.perform(get("/api/find/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(dron.getNombre()));
    }

    @Test
    void find_by_id_exection() throws Exception {
        when(simu.findDronById(99L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/find/99"))
                .andExpect(status().isBadRequest());
    }

    // Test Find All

    @Test
    void devuelve_una_lista_drones() throws Exception {
        List<Dron> lista = new ArrayList<>();
        lista.add(dron);

        when(simu.findallDrones()).thenReturn(lista);
        mockMvc.perform(get("/api/drones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value(lista.get(0).getNombre()));
    }

    @Test
    void devuelve_una_lista_drones_vacia() throws Exception {
        List<Dron> list = new ArrayList<>();
        when(simu.findallDrones()).thenReturn(list);

        mockMvc.perform(get("/api/drones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Test Find By Cord

    @Test
    void find_by_cord() throws Exception {
        when(simu.findByCord(5, 5)).thenReturn(dron);
        mockMvc.perform(get("/api/find/cord")
                .param("x", "5")
                .param("y", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(dron.getX()))
                .andExpect(jsonPath("$.y").value(dron.getY()));
    }

    @Test
    void find_by_cord_exection() throws Exception {
        when(simu.findByCord(99, 99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/find/cord?x=99&y=99"))
                .andExpect(status().isBadRequest());
    }

    // Test OK

    @Test
    void get_okey() throws Exception {
        mockMvc.perform(get("/api/ok"))
                .andExpect(status().isOk())
                .andExpect(content().string("Okey"));
    }

    // Test Delete All

    @Test
    void delete_devuelve_lista_dron() throws Exception {
        List<Dron> list = new ArrayList<>();
        list.add(dron);
        when(simu.deleteall()).thenReturn(list);
        mockMvc.perform(delete("/api/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value(list.get(0).getNombre()));
    }

    @Test
    void delete_devuelve_lista_dron_vacia() throws Exception {
        List<Dron> list = new ArrayList<>();
        when(simu.deleteall()).thenReturn(list);
        mockMvc.perform(delete("/api/delete")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Test Delete By Id

    @Test
    void delete_devuelve_dron_by_id() throws Exception {
        when(simu.deleteById(1L)).thenReturn(dron);
        mockMvc.perform(delete("/api/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(dron.getNombre()));
    }

    @Test
    void delete_devuelve_dron_by_id_exection() throws Exception {
        when(simu.deleteById(99L)).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/api/delete/99"))
                .andExpect(status().isBadRequest());
    }

    // Test Ejecutar

    @Test
    void ejecutar_devuelve_simulaciorequest() throws Exception {
        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);

        List<DronRequest> dronesEntrada = new ArrayList<>();
        SimulacionRequest sRequest = new SimulacionRequest(matriz, dronesEntrada);

        when(simu.ejecutarSimulacion(any(), any())).thenReturn(dronesEntrada);

        mockMvc.perform(post("/api/ejecutar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.matriz").exists())
                .andExpect(jsonPath("$.drones2").isArray());
    }

    @Test
    void ejecutar_devuelve_simulaciorequest_vacio() throws Exception {
        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);
        List<DronRequest> list = new ArrayList<>();
        SimulacionRequest request = new SimulacionRequest(matriz, list);

        when(simu.ejecutarSimulacion(any(), any())).thenReturn(Collections.emptyList());

        mockMvc.perform(post("/api/ejecutar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.drones2").isEmpty())
                .andExpect(jsonPath("$.drones2").isArray());
    }

    // Test Edit All

    @Test
    void edit_all_devuelve_dron_id() throws Exception {
        Dron nuevo = new Dron();
        nuevo.setId(1L);
        nuevo.setNombre("Alfa");
        when(simu.editall(eq(1L), any(Dron.class))).thenReturn(nuevo);
        mockMvc.perform(patch("/api/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Alfa"));
    }

    @Test
    void edit_all_devuelve_dron_id_exeptio() throws Exception {
        when(simu.editall(eq(99L), any(Dron.class))).thenThrow(new RuntimeException());

        mockMvc.perform(patch("/api/edit/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Dron())))
                .andExpect(status().isBadRequest());
    }

    // Test Edit Cord By Id

    @Test
    void edit_cord_devuelve_dron_id() throws Exception {
        Dron nuevo = new Dron();
        nuevo.setId(1L);
        nuevo.setX(0);
        nuevo.setY(0);
        when(simu.editbyId(eq(1L), anyInt(), anyInt())).thenReturn(nuevo);
        mockMvc.perform(patch("/api/edit/cord/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.x").value(0))
                .andExpect(jsonPath("$.y").value(0));
    }

    @Test
    void edit_cord_devuelve_dron_id_exection() throws Exception {
        when(simu.editbyId(eq(99L), anyInt(), anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(patch("/api/edit/cord/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Dron())))
                .andExpect(status().isBadRequest());
    }
}
