package com.tecsup.demo.model.daos;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoDao extends EntidadDao<Alumno, String> {
    public List<Alumno> findByNombre(String nombre);
    public List<Alumno> findByApellido(String apellido);
    public Alumno findByDni(String dni);
}