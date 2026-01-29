package com.example.drones.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;

class SimulacionRquestTest {
    
    private SimulacionRequest simuRe;

    @BeforeEach
    void setup(){
        simuRe = new SimulacionRequest(null, null);
    }

    // Test Matriz
    @Test
    void get_and_set_matriz(){
        Matriz ma = new Matriz();
        simuRe.setMatriz(ma);
        assertEquals(ma, simuRe.getMatriz());
    }

    // Test Drones
    @Test
    void get_and_set_drones(){
        List<Dron> lita = new ArrayList<>();
        simuRe.setDrones(lita);
        assertEquals(lita, simuRe.getDrones());
    }

    // Test DronRequest
    @Test
    void get_and_set_dronrequest(){
        List<DronRequest> lista = new ArrayList<>();
        simuRe.setDrones2(lista);
        assertEquals(lista, simuRe.getDrones2());
    }

    // Test Contructor
    @Test
    void Contructor(){
        Matriz ma = new Matriz();
        List<DronRequest> lista = new ArrayList<>();

        simuRe = new SimulacionRequest(ma, lista);

        assertEquals(ma, simuRe.getMatriz());
        assertEquals(lista, simuRe.getDrones2());
    }
}
