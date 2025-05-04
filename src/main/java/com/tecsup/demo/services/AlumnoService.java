package com.tecsup.demo.services;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoService {
    void create(Alumno alumno);
    Alumno find(Integer id);
    List<Alumno> findAll();
    void update(Alumno alumno);
    void delete(Integer id);
    List<Alumno> buscarPorNombre(String nombre);
}