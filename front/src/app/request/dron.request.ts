import { ValorOrientacion } from "../models/valorOrientacion.model";

export interface DronRequest {
    id:number;
    nombre:string;
    modelo:string;
    x:number;
    y:number;
    orientacion:ValorOrientacion
}