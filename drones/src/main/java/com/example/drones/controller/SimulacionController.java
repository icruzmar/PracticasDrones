package com.example.drones.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api")
public class SimulacionController {
    private final SimulacionService simu;

    public SimulacionController(SimulacionService simu) {
        this.simu = simu;
    }

    @PostMapping("/ejecutar")
    public SimulacionRequest ejecutar(@RequestBody SimulacionRequest request) {
        Matriz matriz = request.getMatriz();
        List<DronRequest> drones = simu.ejecutarSimulacion(matriz, request.getDrones());

        return new SimulacionRequest(matriz, drones);
    }

    @GetMapping("/drones")
    public List<Dron> listall() {
        return simu.findallDrones();
    }

    @GetMapping("/find/{id}")
    public Dron findbyId(@PathVariable Long id) {
        return simu.findDronById(id);
    }

    @DeleteMapping("/delete")
    public List<Dron> delete(){
        return simu.deleteall();
    }

    @DeleteMapping("/delete/{id}")
    public Dron deletebyId(@PathVariable Long id){
        return simu.deleteById(id);
    }

    @PatchMapping("/edit/{id}")
    public Dron editall(@PathVariable Long id,@RequestBody Dron dron){
        return simu.editall(id,dron);
    }

    public record CordenadasDTO(int x , int y) {}
    @PatchMapping("/edit/cord/{id}")
    public Dron editbyId(@PathVariable Long id,@RequestBody CordenadasDTO cord){
        return simu.editbyId(id, cord.x(),cord.y());
    }

    @GetMapping("/find/cord")
    public Dron findbyCord(@RequestParam int x, @RequestParam int y){
        return simu.findByCord(x,y);
    }
    @GetMapping("/ok")
    public String ok() {
        return "Okey";
    }
}
