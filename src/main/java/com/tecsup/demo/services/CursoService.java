package com.tecsup.demo.services;

import com.tecsup.demo.model.entities.Curso;
import java.util.List;

public interface CursoService {
    void create(Curso curso);
    Curso find(Integer id);
    List<Curso> findAll();
    void update(Curso curso);
    void delete(Integer id);
}