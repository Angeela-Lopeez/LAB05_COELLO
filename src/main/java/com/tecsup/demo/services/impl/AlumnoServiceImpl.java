package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.impl.DaoFactory;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import com.tecsup.demo.util.Util;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoDao dao;

    public AlumnoServiceImpl() {
        dao = DaoFactory.getAlumnoDao(Util.opc);
    }

    @Override
    public void grabar(Alumno alumno) {
        dao.create(alumno);
    }

    @Override
    public Alumno buscar(String id) {
        return dao.find(id);
    }

    @Override
    public List<Alumno> listar() {
        return dao.findAll();
    }

    @Override
    public void actualizar(Alumno alumno) {
        dao.update(alumno);
    }

    @Override
    public void borrar(String id) {
        dao.delete(id);
    }

    @Override
    public List<Alumno> bus