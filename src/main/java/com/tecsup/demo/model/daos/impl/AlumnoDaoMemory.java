package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoMemory implements AlumnoDao {

    private static List<Alumno> listaDeAlumnos;

    public AlumnoDaoMemory() {
        listaDeAlumnos = new ArrayList<>();
        // Se puede inicializar con algunos datos de prueba si se desea
        /*
        listaDeAlumnos = List.of(
            new Alumno("A001", "Juan", "Pérez", "12345678", "Calle 123", "987654321", "juan@example.com"),
            new Alumno("A002", "María", "García", "87654321", "Av. Principal 456", "123456789", "maria@example.com")
        );
        */
    }

    @Override
    public void create(Alumno alumno) {
        listaDeAlumnos.add(alumno);
    }

    @Override
    public Alumno find(String id) {
        for (Alumno alumno : listaDeAlumnos) {
            if (alumno.getCodigo().equals(id)) {
                return alumno;
            }
        }
        return null;
    }

    @Override
    public List<Alumno> findAll() {
        return listaDeAlumnos;
    }

    @Override
    public void update(Alumno alumno) {
        for (int i = 0; i < listaDeAlumnos.size(); i++) {
            if (listaDeAlumnos.get(i).getCodigo().equals(alumno.getCodigo())) {
                listaDeAlumnos.set(i, alumno);
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        listaDeAlumnos.removeIf(alumno -> alumno.getCodigo().equals(id));
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno alumno : listaDeAlumnos) {
            if (alumno.getNombres().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(alumno);
            }
        }
        return resultado;
    }

    @Override
    public List<Alumno> findByApellido(String apellido) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno alumno : listaDeAlumnos) {
            if (alumno.getApellidos().toLowerCase().contains(apellido.toLowerCase())) {
                resultado.add(alumno);
            }
        }
        return resultado;
    }

    @Override
    public Alumno findByDni(String dni) {
        for (Alumno alumno : listaDeAlumnos) {
            if (alumno.getDni().equals(dni)) {
                return alumno;
            }
        }
        return null;
    }
}