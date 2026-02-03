package com.example.drones.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example="TURN_LEFT", description = "Son las ordenes del Dron")
public enum ValorOrden {
    TURN_LEFT, TURN_RIGHT, MOVE_FORWARD
}
