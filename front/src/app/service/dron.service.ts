import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Dron } from "../models/dron.model";

export interface CordenadasDTO {
    x: number;
    y: number;
}

@Injectable({ providedIn: 'root'})
export class DronService {
    private http = inject(HttpClient);
    private readonly URL_BASE = 'http://localhost:8080/api';

    listarTodos() {
        return this.http.get<Dron[]>(`${this.URL_BASE}/drones`);
    }

    findById(id: number) {
        return this.http.get<Dron>(`${this.URL_BASE}/find/${id}`);
    }

    deleteAll() {
        return this.http.delete<Dron[]>(`${this.URL_BASE}/delete`);
    }

    deleteById(id: number) {
        return this.http.delete<Dron>(`${this.URL_BASE}/delete/${id}`);
    }

    editAll(id: number, dron: Dron) {
        return this.http.patch<Dron>(`${this.URL_BASE}/edit/${id}`, dron);
    }

    editCord(id: number, cord: CordenadasDTO) {
        return this.http.patch<Dron>(`${this.URL_BASE}/edit/cord/${id}`, cord);
    }

    findByCord(x:number ,y:number){
        return this.http.get<Dron>(`${this.URL_BASE}/find/cord?x=${x}&y=${y}`);
    }
}