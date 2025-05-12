package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Alumno;

import java.sql.SQLException;
import java.util.List;

public interface AlumnoDao {

    void create(Alumno alumno);
    Alumno find(String id);
    List<Alumno> findAll() throws SQLException;
    void update(Alumno alumno);
    void delete(String id);

    void save(Alumno alumno) throws SQLException;
}
