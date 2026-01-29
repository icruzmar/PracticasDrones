package com.example.drones.domain.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ValorOrientacionTest {
    @Test
    void debe_contner_todos_los_valores() {
        assertAll("Valores de Orientacion",
            ()-> assertEquals("N", ValorOrientacion.N.name()),
            ()-> assertEquals("S",ValorOrientacion.S.name()),
            ()-> assertEquals("E",ValorOrientacion.E.name()),
            ()-> assertEquals("O",ValorOrientacion.O.name())
        );
    }

    @Test
    void cantidad_de_enum(){
        assertEquals(4,ValorOrientacion.values().length);
    }
}
