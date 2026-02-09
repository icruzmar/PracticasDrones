package com.example.drones.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.drones.application.usecase.SimulacionService;
import com.example.drones.domain.model.Dron;
import com.example.drones.domain.model.Matriz;
import com.example.drones.dto.DronRequest;
import com.example.drones.dto.SimulacionRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class SimulacionController {
    private final SimulacionService simu;

    public SimulacionController(SimulacionService simu) {
        this.simu = simu;
    }

    @Operation(summary = "Ejecucion de Drones en un Mapa", description = "Esta funcion guarda los drones en la base de datos y hace que las ordenes que tienen se ejecute y que su posicion cambien si es posible, si se mantiene en la misma en la que entro es que no ha sido posible el cambio de posicion por que esa posicion esta ocupada")
    @PostMapping("/ejecutar")
    public SimulacionRequest ejecutar(@Valid @RequestBody SimulacionRequest request) {
        log.info("Iniciando ejecución de simulacion");
        log.debug("Datos recibidos: MAtriz, Drones: []", request.getMatriz(), request.getDrones());

        Matriz matriz = request.getMatriz();
        List<DronRequest> drones = simu.ejecutarSimulacion(matriz, request.getDrones());

        log.info("Simulacion finalizada con exito");
        return new SimulacionRequest(matriz, drones);
    }

    @Operation(summary = "Lista de Drones guardado", description = "Esta funcion lista todos los drones que hay guardado en la base de datos y te proporciona todos los datos de estos.")
    @GetMapping("/drones")
    public List<Dron> listall() {
        return simu.findallDrones();
    }

    @Operation(summary = "Busca un Dron especifico", description = "Esta funcion le proporcionas un Id y si es valido le entrega un dron con toda su informacion")
    @GetMapping("/find/{id}")
    public Dron findbyId(@Valid @PathVariable Long id) {
        log.info("Buscando dron con ID: {}", id);
        Dron dron = simu.findDronById(id);

        if (dron == null) {
            log.warn("No se encontro el dron con ID: {}", id);
        }

        return dron;
    }

    @Operation(summary = "Borrar Todo", description = "Esta funcion se encarga de borrar todo de la base de datos")
    @DeleteMapping("/delete")
    public List<Dron> delete() {
        log.info("Listado de todos los Drones");
        return simu.deleteall();
    }

    @Operation(summary = "Delete By ID", description = "Esta funcion se encarga de borrar un dron segun su Id")
    @DeleteMapping("/delete/{id}")
    public Dron deletebyId(@Valid @PathVariable Long id) {
        log.info("Peticion para eliminar dron ID: {}", id);
        try {
            return simu.deleteById(id);
        } catch (Exception e) {
            log.error("Error al intentar borrar el dron {}: ", id, e);
            throw e;
        }
    }

    @Operation(summary = "Edit All By ID", description = "Esta funcion edita un dron entero menos su id que no cambia")
    @PatchMapping("/edit/{id}")
    public Dron editall(@Valid @PathVariable Long id,@Valid @RequestBody Dron dron) {
        log.info("Peticion para editar dron ID: {}", id);
        try {
            return simu.editall(id, dron);
        } catch (Exception e) {
            log.error("Error al intentar al editar el dron {}", id, e);
            throw e;
        }
    }

    public record CordenadasDTO(int x, int y) {
    }

    @Operation(summary = "Edit Cord By ID", description = "Esta funcion edita las cordenadas de un dron y verifica si es posible su posicion ahi")
    @PatchMapping("/edit/cord/{id}")
    public Dron editbyId(@Valid @PathVariable Long id,@Valid @RequestBody CordenadasDTO cord) {
        log.info("Actualizando coordenadas dron ID {}: Nueva pos [{}, {}]", id, cord.x(), cord.y());
        return simu.editbyId(id, cord.x(), cord.y());
    }

    @Operation(summary = "Find By Cord", description = "Esta funcion busca un dron dependiendo de las cordenadas que les pase")
    @GetMapping("/find/cord")
    public Dron findbyCord(@Valid @RequestParam int x,@Valid @RequestParam int y) {
        log.info("Buscando Dron con las Cordenadad", x, y);
        Dron dron = simu.findByCord(x, y);
        if (dron == null) {
            log.warn("No se encontro el dron con las Cordenadas", x, y);
        }

        return dron;
    }

    @Operation(summary = "Okey :)", description = "Esta funcion devuelve un 'Okey'. Fue usada para la prueba")
    @GetMapping("/ok")
    public String ok() {
        log.debug("Endpoint /ok consultado");
        return "Okey";
    }
}
