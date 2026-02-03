package com.example.drones.domain.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class Dron {

    @Schema(example = "1", description = "Id unico autogenerado")
    private Long id;
    @Schema(example = "Nombre",description = "Es el nombre proporcionado al dron")
    private String nombre;
    @Schema(example = "Modelo",description = "Es el modelo proporcionado al dron")
    private String modelo;
    @Schema(example = "5",description = "Es la Cordenada de la posicion X")
    private int x;
    @Schema(example = "5",description = "Es la cordenada de la posicion Y")
    private int y;
    private ValorOrientacion orientacion;
    private List<ValorOrden> ordenes;

    public void ejecutarOrdenes() {
        for (ValorOrden orden : ordenes) {
            ejecutarOrden(orden);
        }
    }

    public void ejecutarOrden(ValorOrden orden) {
        switch (orden) {
            case TURN_LEFT -> turnLeft();
            case TURN_RIGHT -> turnRight();
            case MOVE_FORWARD -> moveForward();
        }
    }

    public void turnLeft() {
        switch (orientacion) {
            case N -> orientacion = ValorOrientacion.O;
            case E -> orientacion = ValorOrientacion.N;
            case O -> orientacion = ValorOrientacion.S;
            case S -> orientacion = ValorOrientacion.E;
        }
    }

    public void turnRight() {
        switch (orientacion) {
            case N -> orientacion = ValorOrientacion.E;
            case E -> orientacion = ValorOrientacion.S;
            case O -> orientacion = ValorOrientacion.N;
            case S -> orientacion = ValorOrientacion.O;
        }
    }

    public void moveForward() {
        int nuevoX = x;
        int nuevoY = y;

        switch (orientacion) {
            case N -> nuevoY++;
            case S -> nuevoY--;
            case E -> nuevoX++;
            case O -> nuevoX--;
           
        }

        x = nuevoX;
        y = nuevoY;

    }

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
