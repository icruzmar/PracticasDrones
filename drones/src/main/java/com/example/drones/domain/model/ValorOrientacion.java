package com.example.drones.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example = "N", description = "Representa los puntos cardinales (N=Norte)")
public enum ValorOrientacion {
    N, E, S, O
}
