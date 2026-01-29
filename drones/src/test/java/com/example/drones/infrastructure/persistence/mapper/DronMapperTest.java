package com.example.drones.infrastructure.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.drones.domain.model.Dron;
import com.example.drones.infrastructure.persistence.entity.DronEntity;

class DronMapperTest {

    private DronMapper dronMa;

    @BeforeEach
    void setup(){
        dronMa = new DronMapper();
    }
    
    // Test to Domain
    @Test
    void devuelve_dron(){
        DronEntity dronEn = new DronEntity();
        Dron dron = dronMa.toDomain(dronEn);

        assertEquals(Dron.class, dron.getClass());
    }

    @Test
    void devuelve_dron_nulo(){
        assertNull(dronMa.toDomain(null));
    }

    // Test to Entity
    @Test
    void devuelve_entity(){
        Dron dronEn = new Dron();
        DronEntity dron = dronMa.toEntity(dronEn);

        assertEquals(DronEntity.class, dron.getClass());
    }

    @Test
    void devuelve_entity_nulo(){
        assertNull(dronMa.toEntity(null));
    }
}
