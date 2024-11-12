package com.api.apirest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.apirest.iservice.IAdministradorService;
import com.api.apirest.model.Administrador;
import com.api.apirest.repository.AdministradorRepository;

@Service
public class AdministradorService implements IAdministradorService{

    @Autowired
    AdministradorRepository rep;

    @Override
    public Administrador obtenerPorUsername(String username) {
        return rep.findByUsername(username);
    }

	@Override
	public List<Administrador> listar() {
		return rep.findAll();
	}

	@Override
	public Administrador registrar(Administrador administrador) throws Exception{
		Administrador adminLocal = rep.findByUsername(administrador.getUsername());
		if(adminLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }else{
            adminLocal = rep.save(administrador);
        }
        return adminLocal;
	}

	@Override
	public Administrador actualizar(Administrador administrador) {
		return rep.save(administrador);
	}

	@Override
	public void eliminar(Integer id) {
		rep.deleteById(id);
	}

	@Override
	public Administrador listarPorId(Integer id) {
		return rep.findById(id).orElse(null);
	}

}

