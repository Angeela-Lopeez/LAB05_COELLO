package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.util.Tipo;

public class DaoFactory {

    public static AlumnoDao getAlumnoDao(Tipo tipo) {
        switch (tipo) {
            case MEMORY:
                return new AlumnoDaoMemory();
            case PREPARED_STATEMENT:
                return new AlumnoDaoPreparedStatement();
            case CALLABLE_STATEMENT:
                return new AlumnoDaoCallableStatement();
            default:
                return new AlumnoDaoPreparedStatement();
        }
    }

    public static AdministradorDao getAdministradorDao(Tipo tipo) {
        switch (tipo) {
            case MEMORY:
                return new AdministradorDaoMemory();
            case PREPARED_STATEMENT:
                return new AdministradorDaoPreparedStatement();
            case CALLABLE_STATEMENT:
                return new AdministradorDaoCallableStatement();
            default:
                return new AdministradorDaoPreparedStatement();
        }
    }

    public static CursoDao getCursoDao(Tipo tipo) {
        switch (tipo) {
            case MEMORY:
                return new CursoDaoMemory();
            case PREPARED_STATEMENT:
                return new CursoDaoPreparedStatement();
            case CALLABLE_STATEMENT:
                return new CursoDaoCallableStatement();
            default:
                return new CursoDaoPreparedStatement();
        }
    }
}