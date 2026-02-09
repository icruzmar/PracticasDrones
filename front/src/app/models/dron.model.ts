import { ValorOrientacion } from "./valorOrientacion.model"
import { ValorOrden } from "./valorOrden.model"

export interface Dron {
    id?: number;
    nombre: string;
    modelo: string;
    x: number;
    y: number;
    orientacion: ValorOrientacion;
    ordenes: ValorOrden[];
}