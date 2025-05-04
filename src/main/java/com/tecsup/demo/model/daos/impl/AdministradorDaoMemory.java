package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.entities.Administrador;

public class AdministradorDaoMemory implements AdministradorDao { // no usages

    @Override
    public Administrador validar(String u, String p) {
        if (u.equals("admin") && p.equals("admin")) {
            Administrador administrador = new Administrador();
            administrador.setLogin(u);
            administrador.setPassword(p);
            administrador.setNombres("angela");
            administrador.setApellidos("lopez");
            return administrador;
        }
        return null;
    }

}