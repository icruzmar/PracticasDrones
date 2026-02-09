import { Dron } from "../models/dron.model";
import { Matriz } from "../models/matriz.model";
import { DronRequest } from "./dron.request";

export interface Simulacion {
    matriz: Matriz;
    dronesEntrada: Dron[];
    drones:DronRequest[];
}