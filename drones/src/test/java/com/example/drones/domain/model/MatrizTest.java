package com.example.drones.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatrizTest {

    private Matriz matriz;

    @BeforeEach
    void setup() {
        matriz = new Matriz();
        matriz.setMaxX(10);
        matriz.setMaxY(10);
    }

    @Test
    void limite_matriz() {
        assertFalse(matriz.posicionValida(1L, -1, 5));
        assertFalse(matriz.posicionValida(1L, 10, 15));
        assertFalse(matriz.posicionValida(1L, 1, -5));
        assertFalse(matriz.posicionValida(1L, 15, 10));

        matriz.registrarPosicion(1L, 0, 0);
        assertFalse(matriz.posicionValida(1L, 0, 0));
        assertFalse(matriz.posicionValida(2L, 0, 0));

        assertTrue(matriz.posicionValida(2L, 5, 5));

    }

    @Test
    void limite2_matriz() {
        assertTrue(matriz.posicionValida2(1L, 5, 5));
        matriz.registrarPosicion(1L, 5, 5);
        assertFalse(matriz.posicionValida2(1L, 5, 5));
        assertFalse(matriz.posicionValida2(2L, 5, 5));
        assertTrue(matriz.posicionValida2(1L, 6, 6));
    }

    @Test
    void colisiones_dron() {
        matriz.registrarPosicion(1L, 5, 5);

        Boolean novalido = matriz.posicionValida(2L, 5, 5);
        Boolean valido = matriz.posicionValida(3L, 0, 0);
        assertTrue(valido);
        assertFalse(novalido);
    }

    @Test
    void get_X() {
        assertEquals(10, matriz.getMaxX());
    }

    @Test
    void get_y() {
        assertEquals(10, matriz.getMaxY());
    }
}
