package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoCallableStatement implements AlumnoDao {

    private ResultSet rs;

    @Override
    public void create(Alumno alumno) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_ins_alumno(?, ?, ?, ?, ?, ?, ?)}")) {
            cst.setString(1, alumno.getCodigo());
            cst.setString(2, alumno.getNombres());
            cst.setString(3, alumno.getApellidos());
            cst.setString(4, alumno.getDni());
            cst.setString(5, alumno.getDireccion());
            cst.setString(6, alumno.getTelefono());
            cst.setString(7, alumno.getEmail());
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Alumno find(String id) {
        Alumno alumno = null;
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_alumno(?)}")) {
            cst.setString(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                alumno = new Alumno(
                        rs.getString("chrAluCodigo"),
                        rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"),
                        rs.getString("chrAluDni"),
                        rs.getString("vchAluDireccion"),
                        rs.getString("chrAluTelefono"),
                        rs.getString("vchAluEmail")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar alumno: " + e.getMessage());
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_findAll_alumno()}")) {
            rs = cst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("chrAluCodigo"),
                        rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"),
                        rs.getString("chrAluDni"),
                        rs.getString("vchAluDireccion"),
                        rs.getString("chrAluTelefono"),
                        rs.getString("vchAluEmail")
                );
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar alumnos: " + e.getMessage());
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void update(Alumno alumno) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_upd_alumno(?, ?, ?, ?, ?, ?, ?)}")) {
            cst.setString(1, alumno.getCodigo());
            cst.setString(2, alumno.getNombres());
            cst.setString(3, alumno.getApellidos());
            cst.setString(4, alumno.getDni());
            cst.setString(5, alumno.getDireccion());
            cst.setString(6, alumno.getTelefono());
            cst.setString(7, alumno.getEmail());
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_del_alumno(?)}")) {
            cst.setString(1, id);
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_alumno_nombre(?)}")) {
            cst.setString(1, "%" + nombre + "%");
            rs = cst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("chrAluCodigo"),
                        rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"),
                        rs.getString("chrAluDni"),
                        rs.getString("vchAluDireccion"),
                        rs.getString("chrAluTelefono"),
                        rs.getString("vchAluEmail")
                );
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error en búsqueda por nombre: " + e.getMessage());
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public List<Alumno> findByApellido(String apellido) {
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_alumno_apellido(?)}")) {
            cst.setString(1, "%" + apellido + "%");
            rs = cst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("chrAluCodigo"),
                        rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"),
                        rs.getString("chrAluDni"),
                        rs.getString("vchAluDireccion"),
                        rs.getString("chrAluTelefono"),
                        rs.getString("vchAluEmail")
                );
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error en búsqueda por apellido: " + e.getMessage());
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public Alumno findByDni(String dni) {
        Alumno alumno = null;
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_alumno_dni(?)}")) {
            cst.setString(1, dni);
            rs = cst.executeQuery();
            if (rs.next()) {
                alumno = new Alumno(
                        rs.getString("chrAluCodigo"),
                        rs.getString("vchAluNombres"),
                        rs.getString("vchAluApellidos"),
                        rs.getString("chrAluDni"),
                        rs.getString("vchAluDireccion"),
                        rs.getString("chrAluTelefono"),
                        rs.getString("vchAluEmail")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error en búsqueda por DNI: " + e.getMessage());
            e.printStackTrace();
        }
        return alumno;
    }
}