package com.tecsup.demo.model.daos.impl;

import java.sql.PreparedStatement;
import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.util.DBConn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoCallableStatement implements CursoDao {

    private Connection con;
    private ResultSet rs;
    private CallableStatement cst;

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_findAll_curso()}")) {
            rs = cst.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getString("chrCurCodigo"),
                        rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos")));
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
        return cursos;
    }

    @Override
    public void create(Curso curso) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_ins_curso(?, ?, ?)}")) {
            cst.setString(1, curso.getCodigo());
            cst.setString(2, curso.getNombre());
            cst.setInt(3, curso.getCreditos());
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }
    }

    @Override
    public Curso find(String id) {
        Curso curso = null;
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_curso(?)}")) {
            cst.setString(1, id);
            rs = cst.executeQuery();
            if (rs.next()) {
                curso = new Curso(
                        rs.getString("chrCurCodigo"),
                        rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos"));
            }
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda: " + e.getMessage());
        }
        return curso;
    }

    @Override
    public void update(Curso curso) {
        try (Connection con = DBConn.getConnection()) {
            PreparedStatement pst = con.prepareStatement(
                    "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?"
            );
            pst.setString(1, curso.getNombre());
            pst.setInt(2, curso.getCreditos());
            pst.setString(3, curso.getCodigo());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(String id) {
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_del_curso(?)}")) {
            cst.setString(1, id);
            cst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
    }

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_curso_range_creditos(?, ?)}")) {
            cst.setInt(1, min);
            cst.setInt(2, max);
            rs = cst.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getString("chrCurCodigo"),
                        rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos")));
            }
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda por créditos: " + e.getMessage());
        }
        return cursos;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> cursos = new ArrayList<>();
        try (Connection con = DBConn.getConnection();
             CallableStatement cst = con.prepareCall("{call sp_find_curso_nombre(?)}")) {
            cst.setString(1, "%" + nombre + "%");
            rs = cst.executeQuery();
            while (rs.next()) {
                cursos.add(new Curso(
                        rs.getString("chrCurCodigo"),
                        rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos")));
            }
        } catch (SQLException e) {
            System.out.println("Error en la búsqueda por nombre: " + e.getMessage());
        }
        return cursos;
    }

}
