package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Administrador;

public interface AdministradorDao {

    public Administrador validar(String user, String password);
}

