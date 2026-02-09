import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DronService } from './service/dron.service';
import { Dron } from './models/dron.model';

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
  x: number | null = null;
  y: number | null = null;

  dronEncontrado: Dron | null = null;
  dronesLista: Dron[] = [];
  error: string = '';
  constructor(private dronService: DronService, private cd: ChangeDetectorRef) { }


  ejecutar() {
    this.error = '';
    this.dronEncontrado = null;
    this.dronesLista = [];

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
  }

}