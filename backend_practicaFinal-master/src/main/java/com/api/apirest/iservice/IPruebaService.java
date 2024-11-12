package com.api.apirest.iservice;

import com.api.apirest.model.Prueba;
import java.util.List;

public interface IPruebaService {

    List<Prueba> listar();
    Prueba registrar(Prueba prueba);
    Prueba actualizar(Prueba prueba);
    void eliminar(Integer id);
    Prueba listarPorId(Integer id);

}
