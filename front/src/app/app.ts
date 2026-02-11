import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CordenadasDTO, DronService } from './service/dron.service';
import { ValorOrientacion } from './models/valorOrientacion.model';
import { ValorOrden } from './models/valorOrden.model';
import { Dron } from './models/dron.model';
import { SimulacionService } from './service/simulacion.service';
import { Simulacion } from './request/simulacion.request';
import { Matriz } from './models/matriz.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  title = 'PracticaDrones';
  opcionSelecionada = '';

  idDron: number | null = null;
  nombreDron: string | null = null;
  modeloDron: string | null = null;
  x: number | null = null;
  y: number | null = null;
  cords: CordenadasDTO | null = null;

  opcionOrientacion: string = '';
  orientacion: ValorOrientacion | null = null;

  opcionOrdenes: string = '';
  ordenes: ValorOrden[] = [];

  maxX: number | null = null;
  maxY: number | null = null;

  seguridad: boolean = false;
  editable: Dron | null = null;
  dronRepuesto: Dron | null = null;
  dronesEntrada: Dron[] = [];
  simulacion_entrada: Simulacion | null = null;
  matriz: Matriz | null = null;

  dronEncontrado: Dron | null = null;
  simuEncontrado: Simulacion | null = null;
  dronesLista: Dron[] = [];
  error: string = '';
  constructor(private dronService: DronService, private cd: ChangeDetectorRef, private simuService: SimulacionService) { }

  agregar_orden() {
    if (this.opcionOrdenes !== '') {
      if (this.opcionOrdenes === 'MOVE') {
        this.ordenes.push(ValorOrden.MOVE_FORWARD);
      } else if (this.opcionOrdenes === 'LEFT') {
        this.ordenes.push(ValorOrden.TURN_LEFT);
      } else if (this.opcionOrdenes === 'RIGHT') {
        this.ordenes.push(ValorOrden.TURN_RIGHT);
      }
      this.cd.detectChanges();
    }

  }
  borrar_orden() {
    if (this.ordenes.length > 0) {
      this.ordenes.pop();
    }
    this.cd.detectChanges();
  }


  selectOrentacion(opcionOrientacion: string): ValorOrientacion {
    if (opcionOrientacion === 'N') return ValorOrientacion.N;
    if (opcionOrientacion === 'E') return ValorOrientacion.E;
    if (opcionOrientacion === 'O') return ValorOrientacion.O;
    if (opcionOrientacion === 'S') return ValorOrientacion.S;
    return ValorOrientacion.N;
  }

  agregar_dron() {
    this.orientacion = this.selectOrentacion(this.opcionOrientacion);
    this.editable = {
      id: this.idDron!,
      nombre: this.nombreDron!,
      modelo: this.modeloDron!,
      x: this.x!,
      y: this.y!,
      orientacion: this.orientacion,
      ordenes: [...this.ordenes]
    };
    this.dronesEntrada.push(this.editable);
    this.cd.detectChanges();
  }
  borrar_dron() {
    this.dronesEntrada.pop();
    this.cd.detectChanges();
  }

  ejecutar() {
    this.error = '';
    this.dronEncontrado = null;
    this.editable = null;
    this.dronesLista = [];
    this.ordenes = [];
    this.simuEncontrado=null;

    if (this.opcionSelecionada === '') {
      this.error = 'Seleciona una opcion'
    }

    if (this.opcionSelecionada === 'FIND_BY_ID') {
      if (!this.idDron) {
        this.error = 'Debes de escribir un ID';
        return;
      }
      this.dronService.findById(this.idDron).subscribe(
        (dron) => {
          this.dronEncontrado = dron;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se encontro el dron';
          this.cd.detectChanges();
        }
      );
    }

    if (this.opcionSelecionada === 'FIND_ALL') {
      this.dronService.listarTodos().subscribe(
        (drones) => {
          this.dronesLista = drones;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se pudo listar los drones';
          this.cd.detectChanges();
        }
      );
    }


    if (this.opcionSelecionada === 'FIND_BY_CORD') {
      if (this.x === null || this.y === null) {
        this.error = 'Debes escribir X e Y';
        return;
      }

      this.dronService.findByCord(this.x!, this.y!).subscribe(
        (dron) => {
          this.dronEncontrado = dron;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se encontro un dron en esas cordenadas';
          this.cd.detectChanges();
        }
      )
    }

    if (this.opcionSelecionada === 'EDIT_BY_ID') {
      if (this.idDron === null) {
        this.error = 'Debes escribir un id';
        return;
      }
      this.dronService.findById(this.idDron).subscribe(
        (dron) => {
          this.dronRepuesto = dron;
        }
      );

      if (this.nombreDron === '') {
        this.nombreDron = null;
      }
      if (this.modeloDron === '') {
        this.modeloDron = null;
      }

      this.orientacion = this.selectOrentacion(this.opcionOrientacion);
      this.editable = {
        id: this.idDron!,
        nombre: this.nombreDron ?? this.dronRepuesto!.nombre,
        modelo: this.modeloDron ?? this.dronRepuesto!.modelo,
        x: this.x ?? this.dronRepuesto!.x,
        y: this.y ?? this.dronRepuesto!.y,
        orientacion: this.orientacion ?? this.dronRepuesto!.orientacion,
        ordenes: this.ordenes ?? this.dronRepuesto!.ordenes
      };
      this.dronService.editAll(this.idDron, this.editable).subscribe(
        (dron) => {
          this.dronEncontrado = dron;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se ha encontrado ese dron';
          this.cd.detectChanges();
        }
      );
    }

    if (this.opcionSelecionada === 'EDIT_CORD') {
      if (this.idDron === null) {
        this.error = 'Debes escribir un id';
        return;
      }
      this.dronService.findById(this.idDron).subscribe(
        (dron) => {
          this.dronRepuesto = dron;
        }
      );
      this.cords = {
        x: this.x ?? this.dronRepuesto!.x,
        y: this.y ?? this.dronRepuesto!.y
      };
      this.dronService.editCord(this.idDron, this.cords).subscribe(
        (dron) => {
          this.dronEncontrado = dron;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se ha encontrado ese dron';
          this.cd.detectChanges();
        }
      )

    }

    if (this.opcionSelecionada === 'DELETE_BY_ID') {
      if (this.idDron === null) {
        this.error = 'Debes escribir un id';
        return;
      }
      this.dronService.deleteById(this.idDron).subscribe(
        (dron) => {
          this.dronEncontrado = dron;
          this.cd.detectChanges();
        },
        () => {
          this.error = 'No se ha encontrado ese dron';
          this.cd.detectChanges();
        }
      )
    }


    if (this.opcionSelecionada === 'DELETE_ALL') {
      if (this.seguridad === false) {
        this.error = 'Necesitas acreditar el paso';
        return;
      }
      this.dronService.deleteAll().subscribe(
        (drones) => {
          this.dronesLista = drones;
          this.seguridad = false;
          this.cd.detectChanges();
        }
      );
    }

    if (this.opcionSelecionada === 'EJECUCION') {
      if (this.maxX === null || this.maxY === null) {
        this.error = 'No puedes dejar la Matriz en blanco';
        return;
      }
      if (this.dronesEntrada.length === 0) {
        this.error = 'Debes de rellenar la lista de dornes';
        return;
      }

      this.matriz = {
        maxX: this.maxX,
        maxY: this.maxY
      };

      this.simulacion_entrada = {
        matriz: this.matriz,
        dronesEntrada: this.dronesEntrada,
        drones: []
      };

      this.simuService.ejecutar(this.simulacion_entrada).subscribe(
        (sim) => {
          this.simuEncontrado=sim;
          this.cd.detectChanges();
        },
        () => {
          this.error= 'Error en la ejcucion del la simulacion';
          this.cd.detectChanges();
        }
      )
    }


    if (this.error === '') {

      this.idDron = null;
      this.nombreDron = '';
      this.modeloDron = '';
      this.x = null;
      this.y = null;
      this.cords = null;

      this.opcionOrientacion = '';
      this.orientacion = null;

      this.opcionOrdenes = '';
      this.ordenes = [];

      this.maxX = null;
      this.maxY = null;

      this.dronesEntrada = [];
    }
  }

}