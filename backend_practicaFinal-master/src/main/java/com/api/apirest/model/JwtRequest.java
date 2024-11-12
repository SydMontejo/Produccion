package com.api.apirest.model;

public class JwtRequest {

	private String username;
    private String password;
    private String nombre;
    private String correo;

    public JwtRequest(){

    }

	public JwtRequest(String username, String password, String nombre, String correo) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.correo = correo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
    
}
