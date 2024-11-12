package com.api.apirest.dto;
//import java.time.LocalDate; // Importar LocalDate

public class EstudiantePruebasDTO {
    private int nov;
    private String nombre;
    private int car;// antes era String
    //private LocalDate fecha; // Cambiar a LocalDate
    private String computacion;
    private String matematica;

    // Constructor
    public EstudiantePruebasDTO(int nov, String nombre, int car, String computacion, String matematica) {
        this.nov = nov;
        this.nombre = nombre;
        this.car = car;
        //this.fecha = fecha;
        this.computacion = computacion;
        this.matematica = matematica;
    }

    // Getters y Setters
    public int getNov() { return nov; }
    public void setNov(int nov) { this.nov = nov; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCar() { return car; }
    public void setCar(int car) { this.car = car; }

    // public LocalDate getFecha() { return fecha; }
    // public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getComputacion() { return computacion; }
    public void setComputacion(String computacion) { this.computacion = computacion; }

    public String getMatematica() { return matematica; }
    public void setMatematica(String matematica) { this.matematica = matematica; }
}

