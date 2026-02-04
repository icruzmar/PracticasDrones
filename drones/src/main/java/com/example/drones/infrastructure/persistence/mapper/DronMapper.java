package com.example.drones.infrastructure.persistence.mapper;

import com.example.drones.domain.model.Dron;
import com.example.drones.infrastructure.persistence.entity.DronEntity;

public final class DronMapper {

    private DronMapper() {
        throw new UnsupportedOperationException("Clase de utilidad, no instanciar");
    }

    public static Dron toDomain(DronEntity entity) {
        if (entity == null) return null;

        Dron dron = new Dron();
        dron.setId(entity.getId());
        dron.setNombre(entity.getNombre());
        dron.setModelo(entity.getModelo());
        dron.setX(entity.getX());
        dron.setY(entity.getY());
        dron.setOrientacion(entity.getOrientacion());
        dron.setOrdenes(entity.getOrdenes());

        return dron;
    }

    public static  DronEntity toEntity(Dron domain) {
        if (domain == null) return null;

        DronEntity entity = new DronEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setModelo(domain.getModelo());
        entity.setX(domain.getX());
        entity.setY(domain.getY());
        entity.setOrientacion(domain.getOrientacion());
        entity.setOrdenes(domain.getOrdenes());

        return entity;
    }
}
