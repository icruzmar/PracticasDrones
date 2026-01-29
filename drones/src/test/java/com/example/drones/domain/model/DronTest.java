package com.example.drones.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DronTest {

    @Spy
    private Dron dronspy;

    private Dron dron;

    @BeforeEach
    void setup() {
        dron = new Dron();
        dron.setOrientacion(ValorOrientacion.N);
        dron.setX(5);
        dron.setY(5);
    }

    // Test de Girar a la izquierda
    @Test
    void girar_izquierda() {
        dron.turnLeft();
        assertEquals(ValorOrientacion.O, dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.S, dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.E, dron.getOrientacion());
        dron.turnLeft();
        assertEquals(ValorOrientacion.N, dron.getOrientacion());
    }

    @Test
    void girar_izquierda_execcion() {
        dron.setOrientacion(null);
        assertThrows(Exception.class, () -> dron.turnLeft());
    }

    // Test de girar a la derecha
    @Test
    void girar_derecha() {
        dron.turnRight();
        assertEquals(ValorOrientacion.E, dron.getOrientacion());
        dron.turnRight();
        assertEquals(ValorOrientacion.S, dron.getOrientacion());
        dron.turnRight();
        assertEquals(ValorOrientacion.O, dron.getOrientacion());
        dron.turnRight();
        assertEquals(ValorOrientacion.N, dron.getOrientacion());
    }

    @Test
    void girar_derecha_execcion() {
        dron.setOrientacion(null);
        assertThrows(Exception.class, () -> dron.turnRight());
    }

    // Test de moverse hacia delante
    @Test
    void mover_hacia_delante() {
        dron.moveForward();
        assertEquals(6, dron.getY());
        dron.setOrientacion(ValorOrientacion.S);
        dron.moveForward();
        assertEquals(5, dron.getY());
        dron.setOrientacion(ValorOrientacion.E);
        dron.moveForward();
        assertEquals(6, dron.getX());
        dron.setOrientacion(ValorOrientacion.O);
        dron.moveForward();
        assertEquals(5, dron.getX());
    }

    @Test
    void mover_hacia_delante_execcion() {
        dron.setOrientacion(null);
        assertThrows(Exception.class, () -> dron.moveForward());
    }

    // Test Ejecutar Ordenes
    @Test
    void ejecutar_ordenes() {
        dronspy.setOrdenes(Arrays.asList(ValorOrden.MOVE_FORWARD,ValorOrden.TURN_LEFT,ValorOrden.TURN_RIGHT));

        dronspy.setOrientacion(ValorOrientacion.N);
        dronspy.ejecutarOrdenes();

        verify(dronspy,times(3)).ejecutarOrden(any(ValorOrden.class));

        verify(dronspy).ejecutarOrden(ValorOrden.MOVE_FORWARD);
        verify(dronspy).ejecutarOrden(ValorOrden.TURN_LEFT);
        verify(dronspy).ejecutarOrden(ValorOrden.TURN_RIGHT);

    }

}
