package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.impl.AlumnoDaoPreparedStatement;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;

import java.sql.SQLException;
import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoDao dao = new AlumnoDaoPreparedStatement();

    @Override
    public void registrar(Alumno alumno) {
        dao.create(alumno);
    }

    @Override
    public Alumno buscar(String codigo) {
        return dao.find(codigo);
    }

    @Override
    public List<Alumno> listar() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            System.out.println("Error al listar alumnos: " + e.getMessage());
            return List.of();
        }
    }

    @Override
    public void actualizar(Alumno alumno) {
        dao.update(alumno);
    }

    @Override
    public void eliminar(String codigo) {
        dao.delete(codigo);
    }
}
