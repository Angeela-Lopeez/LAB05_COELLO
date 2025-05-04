package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoDao {
    void create(Alumno alumno) throws Exception;
    void update(Alumno alumno) throws Exception;
    void delete(String codigo) throws Exception;
    Alumno find(String codigo) throws Exception;
    List<Alumno> findAll() throws Exception;
}
