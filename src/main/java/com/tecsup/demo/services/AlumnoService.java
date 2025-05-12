package com.tecsup.demo.services;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoService {

    void registrar(Alumno alumno);
    Alumno buscar(String codigo);
    List<Alumno> listar();
    void actualizar(Alumno alumno);
    void eliminar(String codigo);
}
