package com.example.drones.infrastructure.persistence.entity;

import java.util.List;

import com.example.drones.domain.model.ValorOrden;
import com.example.drones.domain.model.ValorOrientacion;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drones")
public class DronEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String modelo;
    private int x;
    private int y;

    @Enumerated(EnumType.STRING)
    private ValorOrientacion orientacion;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ValorOrden> ordenes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ValorOrientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(ValorOrientacion orientacion) {
        this.orientacion = orientacion;
    }

    public List<ValorOrden> getOrdenes() {
        return ordenes;
    }

    public void setOrdenes(List<ValorOrden> ordenes) {
        this.ordenes = ordenes;
    }
}
