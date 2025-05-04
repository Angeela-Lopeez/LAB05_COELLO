package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlumnoDaoMemory implements AlumnoDao {
    private static final Map<Integer, Alumno> alumnos = new HashMap<>();
    private static int secuencia = 1;

    @Override
    public void create(Alumno alumno) {
        alumno.setIdAlumno(secuencia++);
        alumnos.put(alumno.getIdAlumno(), alumno);
    }

    @Override
    public Alumno find(Integer id) {
        return alumnos.get(id);
    }

    @Override
    public List<Alumno> findAll() {
        return new ArrayList<>(alumnos.values());
    }

    @Override
    public void update(Alumno alumno) {
        alumnos.put(alumno.getIdAlumno(), alumno);
    }

    @Override
    public void delete(Integer id) {
        alumnos.remove(id);
    }

    @Override
    public List<Alumno> buscarPorNombre(String nombre) {
        List<Alumno> resultado = new ArrayList<>();
        for (Alumno alumno : alumnos.values()) {
            if (alumno.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(alumno);
            }
        }
        return resultado;
    }
}