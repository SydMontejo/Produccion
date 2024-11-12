package com.api.apirest.model;

import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name = "estudiantes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Estudiante implements UserDetails{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nov", length = 20, nullable = false)
	private Integer nov;
	@Column(name = "cui", length = 13, nullable = false)
	private String cui;
	@Column(name = "nombre", length = 200, nullable = false)
	private String nombre;
	@Column(name = "correo", length = 30, nullable = false)
	private String correo;
	@Column(name = "ua", length = 2, nullable = false)
	private Integer ua;
	@Column(name = "ext", length = 2, nullable = false)
	private Integer ext;
    @Column(name = "car", length = 2, nullable = false)
    private Integer car; //CODIGO CARRERA
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "estudiante")
	@JsonManagedReference
    private List<Prueba> pruebas;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Administrador administrador;

    
	public Estudiante() {
		super();
	}

	public Estudiante(Long id, Integer nov, String cui, String nombre, String correo, Integer ua, Integer ext,
			Integer car, List<Prueba> pruebas, Administrador administrador) {
		this.id = id;
		this.nov = nov;
		this.cui = cui;
		this.nombre = nombre;
		this.correo = correo;
		this.ua = ua;
		this.ext = ext;
		this.car = car;
		this.pruebas = pruebas;
		this.administrador = administrador;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNov() {
		return nov;
	}

	public void setNov(Integer nov) {
		this.nov = nov;
	}

	public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getUa() {
		return ua;
	}

	public void setUa(Integer ua) {
		this.ua = ua;
	}

	public Integer getExt() {
		return ext;
	}

	public void setExt(Integer ext) {
		this.ext = ext;
	}

	public Integer getCar() {
		return car;
	}

	public void setCar(Integer car) {
		this.car = car;
	}

	public List<Prueba> getPruebas() {
		return pruebas;
	}

	public void setPruebas(List<Prueba> pruebas) {
		this.pruebas = pruebas;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}
	
	@Override
	public String getUsername() { //Seleccionar NOV
		return nombre; //nov
	}

	@Override
	public String getPassword() { //Seleccionar carrera
		return correo; //car
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
