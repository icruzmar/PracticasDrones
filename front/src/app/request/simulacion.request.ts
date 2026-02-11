import { Dron } from "../models/dron.model";
import { Matriz } from "../models/matriz.model";

export interface Simulacion {
    matriz: Matriz;
    drones: Dron[];
}