package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.impl.AlumnoDaoImpl;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoDao dao = new AlumnoDaoImpl();

    @Override
    public void create(Alumno alumno) throws Exception {
        dao.create(alumno);
    }

    @Override
    public void update(Alumno alumno) throws Exception {
        dao.update(alumno);
    }

    @Override
    public void delete(String codigo) throws Exception {
        dao.delete(codigo);
    }

    @Override
    public Alumno find(String codigo) throws Exception {
        return dao.find(codigo);
    }

    @Override
    public List<Alumno> findAll() throws Exception {
        return dao.findAll();
    }
}
