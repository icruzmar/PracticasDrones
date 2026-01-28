package com.example.drones.application.usecase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.example.drones.dto.DronRequest;
import com.example.drones.domain.port.DronRepository;
import com.example.drones.service.DronService;

@Service
public class SimulacionService {

    private final DronRepository repo;
    private final DronService ds;

    public SimulacionService(DronRepository repo, DronService ds) {
        this.repo = repo;
        this.ds = ds;
    }

    public List<DronRequest> ejecutarSimulacion(Matriz matriz, List<Dron> drones) {
        List<DronRequest> resultado = new ArrayList<>();
        ds.cargarDronesbyDB(matriz);
        for (Dron dron : drones) {
            dron.ejecutarOrdenes(matriz);

            if (matriz.posicionValida(dron.getId(), dron.getX(), dron.getY())) {
                matriz.registrarPosicion(dron.getId(), dron.getX(), dron.getY());

                resultado.add(new DronRequest(
                        dron.getId(),
                        dron.getNombre(),
                        dron.getModelo(),
                        dron.getX(),
                        dron.getY(),
                        dron.getOrientacion()));

                dron.setId(null);
                repo.save(dron);
            } else {
                System.out.println("No se ha podido guardar el dron: Erro al coincidir el Id o la Posicion");
            }
        }
        return resultado;

    }

    public List<Dron> findallDrones() {
        return repo.findAll();
    }

    public Dron findDronById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe el dron con id: " + id));
    }

    public List<Dron> deleteall() {
        repo.deleteAll();
        return repo.findAll();
    }

    @Transactional
    public Dron deleteById(Long id) {
        Dron dronEliminar = findDronById(id);
        dronEliminar.getOrdenes().size();
        repo.delete(dronEliminar);
        return dronEliminar;
    }

    public Dron editall(Long id, Dron dron) {
        Dron dronOriginal = findDronById(id);
        Matriz matriz = new Matriz();
        ds.cargarDronesbyDB(matriz);
        if (matriz.posicionValida2(id, dron.getX(), dron.getY())) {
            dronOriginal.setNombre(dron.getNombre());
            dronOriginal.setModelo(dron.getModelo());
            dronOriginal.setX(dron.getX());
            dronOriginal.setY(dron.getY());
            dronOriginal.setOrientacion(dron.getOrientacion());
            dronOriginal.setOrdenes(dron.getOrdenes());
            repo.save(dronOriginal);
        }
        return dronOriginal;
    }

    public Dron editbyId(Long id, int x, int y) {
        Dron dron = findDronById(id);
        Matriz matriz = new Matriz();
        ds.cargarDronesbyDB(matriz);
        if (matriz.posicionValida2(id, x, y)) {
            dron.setX(x);
            dron.setY(y);
            repo.save(dron);

        }
        return dron;
    }

    public Dron findByCord(int x, int y) {
        return repo.findByXAndY(x, y).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No hay drones en las cordenadas: " + x + " " + y));
    }
}
