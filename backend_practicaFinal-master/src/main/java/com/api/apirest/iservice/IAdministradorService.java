package com.api.apirest.iservice;

import java.util.List;
import com.api.apirest.model.Administrador;

public interface IAdministradorService {
	
	List<Administrador> listar();
	Administrador registrar(Administrador administrador) throws Exception;
	Administrador actualizar(Administrador administrador);
	void eliminar(Integer id);
	Administrador listarPorId(Integer id);
	Administrador obtenerPorUsername(String username);

}
