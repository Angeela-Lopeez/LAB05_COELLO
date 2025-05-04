package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.impl.DaoFactory;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import com.tecsup.demo.util.Tipo;
import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoDao alumnoDao;

    public AlumnoServiceImpl() {
        this.alumnoDao = DaoFactory.getAlumnoDao(Tipo.PREPARED_STATEMENT);
    }

    @Override
    public void create(Alumno alumno) {
        alumnoDao.create(alumno);
    }

    @Override
    public Alumno find(Integer id) {
        return alumnoDao.find(id);
    }

    @Override
    public List<Alumno> findAll() {
        return alumnoDao.findAll();
    }

    @Override
    public void update(Alumno alumno) {
        alumnoDao.update(alumno);
    }

    @Override
    public void delete(Integer id) {
        alumnoDao.delete(id);
    }

    @Override
    public List<Alumno> buscarPorNombre(String nombre) {
        return alumnoDao.buscarPorNombre(nombre);
    }
}