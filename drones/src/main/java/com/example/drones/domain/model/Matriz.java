package com.example.drones.domain.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Component
public class Matriz {
    @JsonIgnore
    private final HashMap<Long, int[]> ocupados = new HashMap<>();

    private int maxX;
    private int maxY;

    public boolean posicionValida(Long id, int x, int y) {
        if (x < 0 || y < 0 || x > maxX || y > maxY){
            return false;
        }
        int[] corde = { x, y };
        boolean esValido = true;

        for (Entry<Long, int[]> entrada : ocupados.entrySet()) {
            Long otroId = entrada.getKey();
            int[] otrasCoordenadas = entrada.getValue();
            if (!otroId.equals(id)) {
                if (Arrays.equals(otrasCoordenadas, corde)) {
                    esValido = false;
                    break; 
                }
            }else{
                esValido= false;
            }
        }
        return esValido;
    }
    
    public boolean posicionValida2(Long id, int x, int y) {
        int[] corde = { x, y };
        boolean esValido = true;

        for (Entry<Long, int[]> entrada : ocupados.entrySet()) {
            Long otroId = entrada.getKey();
            int[] otrasCoordenadas = entrada.getValue();
            if (otroId.equals(id)) {
                if (Arrays.equals(otrasCoordenadas, corde)) {
                    esValido = false;
                    break; 
                }
            }else{
                esValido= false;
            }
        }
        return esValido;
    }

    public void registrarPosicion(Long id, int x, int y) {
        ocupados.put(id, new int[] { x, y });
        
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

}
