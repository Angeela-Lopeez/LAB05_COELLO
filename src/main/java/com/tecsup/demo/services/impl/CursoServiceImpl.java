package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.daos.impl.DaoFactory;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.services.CursoService;
import com.tecsup.demo.util.Util;

import java.util.List;

public class CursoServiceImpl implements CursoService {

    private CursoDao dao;
    public CursoServiceImpl() {
        dao = DaoFactory.getCursoDao(Util.opc);
    }

    @Override
    public void grabar(Curso curso) {
        dao.create(curso);
    }

    @Override
    public Curso buscar(String id) {
        return dao.find(id);
    }

    @Override
    public List<Curso> listar() {
        return dao.findAll();
    }

    @Override
    public void actualizar(Curso curso) {
        dao.update(curso);
    }

    @Override
    public void borrar(String id) {
        dao.delete(id);
    }

    @Override
    public List<Curso> filterByCreditos(int min, int max) {
        return dao.findByRangeCreditos(min, max);
    }
}
