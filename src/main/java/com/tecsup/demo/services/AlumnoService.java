package com.tecsup.demo.services;

import com.tecsup.demo.model.entities.Alumno;
import java.util.List;

public interface AlumnoService {
    public void grabar(Alumno alumno);
    public Alumno buscar(String id);
    public List<Alumno> listar();
    public void actualizar(Alumno alumno);
    public void borrar(String id);
    public List<Alumno> buscarPorNombre(String nombre);
    public List<Alumno> buscarPorApellido(String apellido);
    public Alumno buscarPorDni(String dni);
}