package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CursoDaoMemory implements CursoDao {
    private static final Map<Integer, Curso> cursos = new HashMap<>();
    private static int secuencia = 1;

    @Override
    public void create(Curso curso) {
        curso.setId(secuencia++);
        cursos.put(curso.getId(), curso);
    }

    @Override
    public Curso find(Integer id) {
        return cursos.get(id);
    }

    @Override
    public List<Curso> findAll() {
        return new ArrayList<>(cursos.values());
    }

    @Override
    public void update(Curso curso) {
        cursos.put(curso.getId(), curso);
    }

    @Override
    public void delete(Integer id) {
        cursos.remove(id);
    }
}