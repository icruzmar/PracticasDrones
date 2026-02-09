import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Simulacion } from "../request/simulacion.request";

@Injectable({ providedIn: 'root' })
export class SimulacionService {
    private http = inject(HttpClient);
    private readonly URL_BASE = 'http://localhost:8080/api';

    ejecutar (simu: Simulacion){
        return this.http.post<Simulacion>(`${this.URL_BASE}/ejecutar`,simu);
    }
}