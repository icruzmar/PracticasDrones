package com.example.drones.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.drones.domain.model.*;
import com.example.drones.domain.port.DronRepository;


@Service
public class DronService {
    private DronRepository dronreposity;

    public DronService(DronRepository dronreposity) {
        this.dronreposity = dronreposity;
    }

    private List<Dron> listaDrones = new ArrayList<>();

    public void cargarDronesbyDB(Matriz matriz) {
        this.listaDrones = dronreposity.findAll();

        for (Dron dron : listaDrones) {

            if (matriz.posicionValida(dron.getId(), dron.getX(), dron.getY())) {
                matriz.registrarPosicion(dron.getId(), dron.getX(), dron.getY());
            } else {
                System.out.println("ADVERTENCIA: Dron " + dron.getId() + " en posición inválida o en colisión.");
            }
        }

        System.out.println("INFO: Se han cargado " + listaDrones.size() + " drones desde la BD.");
    }

    public List<Dron> getListarDrones() {
        return listaDrones;
    }
}