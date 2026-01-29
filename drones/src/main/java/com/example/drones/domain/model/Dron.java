package com.example.drones.domain.model;

import java.util.List;

public class Dron {

    private Long id;
    private String nombre;
    private String modelo;
    private int x;
    private int y;
    private ValorOrientacion orientacion;
    private List<ValorOrden> ordenes;

    public void ejecutarOrdenes(Matriz mapa) {
        for (ValorOrden orden : ordenes) {
            ejecutarOrden(orden, mapa);
        }
    }

    public void ejecutarOrden(ValorOrden orden, Matriz mapa) {
        switch (orden) {
            case TURN_LEFT -> turnLeft();
            case TURN_RIGHT -> turnRight();
            case MOVE_FORWARD -> moveForward(mapa);
        }
    }

    public void turnLeft() {
        switch (orientacion) {
            case N -> orientacion = ValorOrientacion.O;
            case E -> orientacion = ValorOrientacion.N;
            case O -> orientacion = ValorOrientacion.S;
            case S -> orientacion = ValorOrientacion.E;
            default -> throw new IllegalStateException("Orientacion no reconocida");
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

    public void moveForward(Matriz mapa) {
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
