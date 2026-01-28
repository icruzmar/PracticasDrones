package com.example.drones.aplication.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.drones.application.usecase.SimulacionService;
import com.example.drones.domain.model.Dron;
import com.example.drones.domain.port.DronRepository;
import com.example.drones.service.DronService;

@ExtendWith(MockitoExtension.class)
public class SimulacionServiceTest {
    @Mock
    DronRepository repo;


    @Mock
    DronService ds;

    @InjectMocks
    SimulacionService service;
    // Test De FindById 
    @Test
    void deberia_volver_dron_cuando_exista(){
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setNombre("Dron A");
        when(repo.findById(1L)).thenReturn(Optional.of(dron));

        Dron resultado = service.findDronById(1L);

        assertEquals(1L,resultado.getId());
        assertEquals("Dron A", resultado.getNombre());

    }
    @Test
    void deberia_lanzar_404_cuando_dron_no_existe(){
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class ,()-> service.findDronById(99L));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    // Test de FindAll
    @Test
    void deberia_volver_una_lista_de_drones(){
        Dron dron1 = new Dron();
        dron1.setNombre("Dron 1");
        Dron dron2 = new Dron();
        dron2.setNombre("Dron 2");

        List<Dron> lista = new ArrayList<>();
        lista.add(dron1);
        lista.add(dron2);

        when(repo.findAll()).thenReturn(lista);

        List<Dron> resultado = repo.findAll();

        assertEquals(lista, resultado);
        assertEquals(lista.get(0).getNombre(), "Dron 1");
        assertEquals(lista.get(1).getNombre(), "Dron 2");
    }

    @Test
    void deberia_volver_lista_vacia(){
        when(repo.findAll()).thenReturn(List.of());

        List<Dron> list = repo.findAll();

        assertNotNull(list);
        assertTrue(list.isEmpty());

    }
}
