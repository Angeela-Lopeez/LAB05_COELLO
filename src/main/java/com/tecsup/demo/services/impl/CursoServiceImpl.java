package com.tecsup.demo.services.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.daos.impl.DaoFactory;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.services.CursoService;
import com.tecsup.demo.util.Tipo;
import java.util.List;

public class CursoServiceImpl implements CursoService {

    private CursoDao cursoDao;

    public CursoServiceImpl() {
        this.cursoDao = DaoFactory.getCursoDao(Tipo.PREPARED_STATEMENT);
    }

    @Override
    public void create(Curso curso) {
        cursoDao.create(curso);
    }

    @Override
    public Curso find(Integer id) {
        return cursoDao.find(id);
    }

    @Override
    public List<Curso> findAll() {
        return cursoDao.findAll();
    }

    @Override
    public void update(Curso curso) {
        cursoDao.update(curso);
    }

    @Override
    public void delete(Integer id) {
        cursoDao.delete(id);
    }
}