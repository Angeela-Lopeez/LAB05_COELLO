package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;

import java.sql.*;
import java.util.*;

public class AlumnoDaoImpl implements AlumnoDao {

    @Override
    public void create(Alumno a) throws Exception {
        Connection cn = DBConn.getConnection();
        CallableStatement cs = cn.prepareCall("{call sp_ins_alumno(?,?,?,?,?)}");
        cs.setString(1, a.getCodigo());
        cs.setString(2, a.getNombres());
        cs.setString(3, a.getApellidos());
        cs.setDate(4, new java.sql.Date(a.getFechaNac().getTime()));
        cs.setString(5, a.getSexo());
        cs.execute();
        cn.close();
    }

    @Override
    public void update(Alumno a) throws Exception {
        Connection cn = DBConn.getConnection();
        CallableStatement cs = cn.prepareCall("{call sp_upd_alumno(?,?,?,?,?)}");
        cs.setString(1, a.getCodigo());
        cs.setString(2, a.getNombres());
        cs.setString(3, a.getApellidos());
        cs.setDate(4, new java.sql.Date(a.getFechaNac().getTime()));
        cs.setString(5, a.getSexo());
        cs.execute();
        cn.close();
    }

    @Override
    public void delete(String codigo) throws Exception {
        Connection cn = DBConn.getConnection();
        CallableStatement cs = cn.prepareCall("{call sp_del_alumno(?)}");
        cs.setString(1, codigo);
        cs.execute();
        cn.close();
    }

    @Override
    public Alumno find(String codigo) throws Exception {
        Connection cn = DBConn.getConnection();
        CallableStatement cs = cn.prepareCall("{call sp_find_alumno(?)}");
        cs.setString(1, codigo);
        ResultSet rs = cs.executeQuery();
        Alumno a = null;
        if (rs.next()) {
            a = new Alumno();
            a.setCodigo(rs.getString(1));
            a.setNombres(rs.getString(2));
            a.setApellidos(rs.getString(3));
            a.setFechaNac(rs.getDate(4));
            a.setSexo(rs.getString(5));
        }
        cn.close();
        return a;
    }

    @Override
    public List<Alumno> findAll() throws Exception {
        Connection cn = DBConn.getConnection();
        CallableStatement cs = cn.prepareCall("{call sp_findAll_alumno()}");
        ResultSet rs = cs.executeQuery();
        List<Alumno> lista = new ArrayList<>();
        while (rs.next()) {
            Alumno a = new Alumno();
            a.setCodigo(rs.getString(1));
            a.setNombres(rs.getString(2));
            a.setApellidos(rs.getString(3));
            a.setFechaNac(rs.getDate(4));
            a.setSexo(rs.getString(5));
            lista.add(a);
        }
        cn.close();
        return lista;
    }
}

