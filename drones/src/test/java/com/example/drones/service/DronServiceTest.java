package com.example.drones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.example.drones.domain.port.DronRepository;
import com.example.drones.domain.service.DronService;

@ExtendWith(MockitoExtension.class)
class DronServiceTest {

    @Mock
    private DronRepository dronRepo;

    @InjectMocks
    private DronService dronSer;

    // Test Gargar Drones de BD
    @Test
    void cargar_drones_debe_registrar_en_matriz() {
        Dron dron = new Dron();
        dron.setX(2);
        dron.setY(2);
        dron.setId(1L);
        List<Dron> lista = new ArrayList<>();
        lista.add(dron);

        when(dronRepo.findAll()).thenReturn(lista);

        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);

        dronSer.cargarDronesbyDB(matriz);

        assertFalse(matriz.posicionValida(10L, 2, 2));
    }

    @Test
    void cargar_drones_debe_colisionar() {
        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);
        matriz.registrarPosicion(99L, 5, 5);

        Dron conflicto = new Dron();
        conflicto.setId(1L);
        conflicto.setX(5);
        conflicto.setY(5);

        when(dronRepo.findAll()).thenReturn(List.of(conflicto));

        dronSer.cargarDronesbyDB(matriz);

        assertFalse(matriz.posicionValida(1L, 5, 5));
    }

    // Test Get Listar Drones
    @Test
    void get_listar_drones() {
        Dron dron = new Dron();
        dron.setId(1L);
        dron.setX(1);
        dron.setY(1);
        List<Dron> lista = List.of(dron);

        when(dronRepo.findAll()).thenReturn(lista);

        Matriz matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);

        dronSer.cargarDronesbyDB(matriz);

        List<Dron> resutado = dronSer.getListarDrones();

        assertEquals(1, resutado.size());
        assertEquals(dron.getId(), resutado.get(0).getId());
    }
}
