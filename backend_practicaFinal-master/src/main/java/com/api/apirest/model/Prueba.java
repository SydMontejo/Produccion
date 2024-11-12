package com.api.apirest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import javax.persistence.*;


@Entity
@Table(name = "prueba")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Prueba {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_prueba", length = 30, nullable = false)
    private String nombrePrueba;
	@Column(name = "resultado", length = 30, nullable = false)
	private String resultado;
	@Column(name = "fecha", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "estudiante_id")
	@JsonBackReference
	private Estudiante estudiante;


	public Prueba() {

	}

	public Prueba(Long id, String nombrePrueba, String resultado, LocalDate fecha, Estudiante estudiante) {
		this.id = id;
		this.nombrePrueba = nombrePrueba;
		this.resultado = resultado;
		this.fecha = fecha;
		this.estudiante = estudiante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePrueba() {
		return nombrePrueba;
	}

	public void setNombrePrueba(String nombrePrueba) {
		this.nombrePrueba = nombrePrueba;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
}
