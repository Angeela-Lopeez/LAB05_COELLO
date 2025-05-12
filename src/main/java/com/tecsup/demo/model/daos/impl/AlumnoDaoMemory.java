package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoMemory implements AlumnoDao {

    // Declaración de la lista
    private List<Alumno> lista = new ArrayList<>();

    @Override
    public void create(Alumno alumno) {
        lista.add(alumno);
    }

    @Override
    public Alumno find(String id) {
        for (Alumno a : lista) {
            if (a.getCodigo().equals(id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public List<Alumno> findAll() {
        return new ArrayList<>(lista);
    }

    @Override
    public void update(Alumno alumno) {
        delete(alumno.getCodigo());
        lista.add(alumno);
    }

    @Override
    public void delete(String id) {
        lista.removeIf(a -> a.getCodigo().equals(id));
    }

    @Override
    public void save(Alumno alumno) {
        create(alumno);
    }
}
