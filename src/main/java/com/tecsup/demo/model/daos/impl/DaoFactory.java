package com.tecsup.demo.model.daos.impl;
import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.util.Tipo;
import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.daos.impl.AlumnoDaoPreparedStatement;


public class DaoFactory {

    public static AdministradorDao getAdministradorDao(Tipo tipo){
        switch (tipo){
            case MEM:
                return new AdministradorDaoMemory();
            case PST:
                return new AdministradorDaoPreparedStatement();
            case CST:
                return new AdministradorDaoCallableStatement();
            default:
                return null;
        }
    }

    public static CursoDao getCursoDao(Tipo tipo){
        switch (tipo){
            case MEM:
                return new CursoDaoMemory();
            case PST:
                return new CursoDaoPreparedStatement();
            case CST:
                return new CursoDaoCallableStateMent();
            default:
                return null;
        }
    }

    public static AlumnoDao getAlumnoDao() {
        return new AlumnoDaoPreparedStatement();
    }

}



