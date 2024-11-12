import { Prueba } from "./prueba";

export class Estudiante {
	id!: number;
	nov!: number;
	nombre!: string;  // Usa 'string' en lugar de 'String'
	cui?: string;
	correo?: string;
	car!: number;
	pruebas!: any[];  // Ajusta según el tipo correcto
}
