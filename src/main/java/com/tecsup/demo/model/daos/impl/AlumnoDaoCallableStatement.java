package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoCallableStatement implements AlumnoDao {

    @Override
    public void create(Alumno alumno) {
        try (Connection cn = DBConn.getConnection()) {
            CallableStatement cs = cn.prepareCall("{call usp_Alumno_insertar(?, ?, ?, ?, ?)}");
            cs.setString(1, alumno.getCodigo());
            cs.setString(2, alumno.getNombres());
            cs.setString(3, alumno.getApellidos());
            cs.setString(4, alumno.getFechaNacimiento());
            cs.setString(5, alumno.getSexo());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en create(): " + e.getMessage());
        }
    }

    @Override
    public Alumno find(String id) {
        Alumno alumno = null;
        try (Connection cn = DBConn.getConnection()) {
            CallableStatement cs = cn.prepareCall("{call usp_Alumno_buscar(?)}");
            cs.setString(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setCodigo(rs.getString("chrAluCodigo"));
                alumno.setNombres(rs.getString("vchAluNombres"));
                alumno.setApellidos(rs.getString("vchAluApellidos"));
                alumno.setFechaNacimiento(rs.getString("dtmAluFechaNac"));
                alumno.setSexo(rs.getString("chrAluSexo"));
            }
        } catch (SQLException e) {
            System.out.println("Error en find(): " + e.getMessage());
        }
        return alumno;
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> list = new ArrayList<>();
        try (Connection cn = DBConn.getConnection()) {
            CallableStatement cs = cn.prepareCall("{call usp_Alumno_listar()}");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setCodigo(rs.getString("chrAluCodigo"));
                a.setNombres(rs.getString("vchAluNombres"));
                a.setApellidos(rs.getString("vchAluApellidos"));
                a.setFechaNacimiento(rs.getString("dtmAluFechaNac"));
                a.setSexo(rs.getString("chrAluSexo"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Error en findAll(): " + e.getMessage());
        }
        return list;
    }

    @Override
    public void update(Alumno alumno) {
        try (Connection cn = DBConn.getConnection()) {
            CallableStatement cs = cn.prepareCall("{call usp_Alumno_actualizar(?, ?, ?, ?, ?)}");
            cs.setString(1, alumno.getCodigo());
            cs.setString(2, alumno.getNombres());
            cs.setString(3, alumno.getApellidos());
            cs.setString(4, alumno.getFechaNacimiento());
            cs.setString(5, alumno.getSexo());
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en update(): " + e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try (Connection cn = DBConn.getConnection()) {
            CallableStatement cs = cn.prepareCall("{call usp_Alumno_eliminar(?)}");
            cs.setString(1, id);
            cs.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en delete(): " + e.getMessage());
        }
    }

    @Override
    public void save(Alumno alumno) {
        this.create(alumno);
    }
}
