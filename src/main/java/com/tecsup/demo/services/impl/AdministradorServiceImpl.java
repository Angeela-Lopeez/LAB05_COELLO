package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.daos.impl.DaoFactory;
import com.tecsup.demo.model.entities.Administrador;
import com.tecsup.demo.services.AdministradorService;
import com.tecsup.demo.util.Util;

public class AdministradorServiceImpl implements AdministradorService {

    private AdministradorDao dao;

    public AdministradorServiceImpl() {
        dao = DaoFactory.getAdministradorDao(Util.opc);
    }

    @Override
    public Administrador validar(String u, String p) {
        return dao.validar(u, p);
    }
}
