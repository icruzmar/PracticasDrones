package com.example.drones.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.drones.domain.model.ValorOrientacion;

class DronRequestTest {

    private DronRequest dr;

    @BeforeEach
    void setup() {
        dr = new DronRequest(1L, "Nombre", "Modelo", 5, 5, ValorOrientacion.E);
    }

    // Test Id
    @Test
    void get_id() {
        assertEquals(1L, dr.getId());
    }

    @Test
    void set_id() {
        dr.setId(2L);
        assertNotEquals(1L, dr.getId());
    }

    // Test Nombre
    @Test
    void get_nombre() {
        assertEquals("Nombre", dr.getNombre());
    }

    @Test
    void set_nombre() {
        dr.setNombre("Nombre 2");
        assertNotEquals("Nombre", dr.getNombre());
    }

    // Test Modelo
    @Test
    void get_modelo() {
        assertEquals("Modelo", dr.getModelo());
    }

    @Test
    void set_modelo() {
        dr.setModelo("MOdelo 2");
        assertNotEquals("Modelo", dr.getModelo());
    }

    // Test X
    @Test
    void get_x() {
        assertEquals(5, dr.getX());
    }

    @Test
    void set_x() {
        dr.setX(2);
        assertNotEquals(5, dr.getX());
    }

    // Test Y
    @Test
    void get_y() {
        assertEquals(5, dr.getY());
    }

    @Test
    void set_Y() {
        dr.setY(2);
        assertNotEquals(5, dr.getY());
    }

        
    // Test Orientacion
    @Test
    void get_orientacio() {
        assertEquals(ValorOrientacion.E, dr.getOrientacion());
    }

    @Test
    void set_orientacio() {
        dr.setOrientacion(ValorOrientacion.N);;
        assertNotEquals(ValorOrientacion.E, dr.getOrientacion());
    }
}
