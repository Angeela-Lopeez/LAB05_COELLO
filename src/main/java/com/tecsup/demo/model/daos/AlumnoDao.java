package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoDao extends EntidadDao<Alumno, Integer> {
    List<Alumno> buscarPorNombre(String nombre);
}