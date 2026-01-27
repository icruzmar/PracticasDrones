package com.example.drones.dto;

import java.util.List;

import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulacionRequest {
    private Matriz matriz;
    private List<Dron> dronesEntrada;
    private List<DronRequest> drones;

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public List<Dron> getDrones() {
        return dronesEntrada;
    }

    public void setDrones(List<Dron> drones1) {
        this.dronesEntrada = drones1;
    }
    
    public SimulacionRequest(Matriz matriz, List<DronRequest> d) {
        this.matriz = matriz;
        this.drones = d;
    }

    public List<DronRequest> getDrones2() {
        return drones;
    }

    public void setDrones2(List<DronRequest> drones2) {
        this.drones = drones2;
    }
    
}
