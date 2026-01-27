package com.example.drones.dto;

import com.example.drones.domain.model.ValorOrientacion;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DronRequest {
   
    private Long id;
    private String nombre;
    private String modelo;
    private int x;
    private int y;
    private ValorOrientacion orientacion;



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

}
