export class EstudiantePruebasDTO {
    id!: number;
  nov!: number;
  nombre!: string;
  car!: number;
  pruebas!: {
    nombrePrueba: string;
    fecha: Date;
    resultado: string;
  }[];
  computacion?: string;
  matematica?: string;
}