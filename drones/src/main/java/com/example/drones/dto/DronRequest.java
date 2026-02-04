package com.example.drones.dto;

import com.example.drones.domain.model.ValorOrientacion;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DronRequest {
   
    private Long id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    private String modelo;
    @NotBlank(message = "La cordenada X es obligatoria")
    @Min(value = 0 , message = "La cordebada X debe ser 0 o mayor" )
    private int x;
    @NotBlank(message = "La cordenada Y es obligatoria")
    @Min(value = 0 , message = "La cordebada Y debe ser 0 o mayor" )
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
