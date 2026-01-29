package com.example.drones.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DronTest {
    
    private Dron dron;

    @BeforeEach
    void setup(){
        dron = new Dron();
        dron.setOrientacion(ValorOrientacion.N);
    }

    @Test
    void girar_izquierda(){
        dron.turnLeft();
        assertEquals(ValorOrientacion.O,dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.S,dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.E,dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.N,dron.getOrientacion());   
    }
    @Test
    void girar_izquierda_nulo(){
        dron.setOrientacion(null);
        assertThrows(NullPointerException.class,()->dron.turnLeft());
    }

    @Test
    void girar_izquierda_execcion(){
        dron.setOrientacion(null);
        assertThrows(Exception.class, ()-> dron.turnLeft());
    }
}
