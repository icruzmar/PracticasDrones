package com.example.drones.domain.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ValorOrdenTest {
    @Test
    void debe_contner_todos_los_valores(){
        assertAll("Valores del Enum",
            ()-> assertEquals("TURN_LEFT", ValorOrden.TURN_LEFT.name()),
            ()-> assertEquals("TURN_RIGHT", ValorOrden.TURN_RIGHT.name()),
            ()-> assertEquals("MOVE_FORWARD", ValorOrden.MOVE_FORWARD.name())
        );
    } 

    @Test
    void cantidad_de_enum(){
        assertEquals(3,ValorOrden.values().length);
    }
}
