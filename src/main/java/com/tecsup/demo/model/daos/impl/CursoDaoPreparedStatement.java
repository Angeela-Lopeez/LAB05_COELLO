package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoPreparedStatement implements CursoDao {

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM curso WHERE intCurCreditos BETWEEN ? AND ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, min);
            pst.setInt(2, max);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso(
                            rs.getString("chrCurCodigo"),
                            rs.getString("vchCurNombre"),
                            rs.getInt("intCurCreditos")
                    );
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM curso WHERE vchCurNombre LIKE ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso(
                            rs.getString("chrCurCodigo"),
                            rs.getString("vchCurNombre"),
                            rs.getInt("intCurCreditos")
                    );
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM curso WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar curso: " + e.getMessage());
        }
    }

    @Override
    public void create(Curso curso) {
        String query = "INSERT INTO curso (chrCurCodigo, vchCurNombre, intCurCreditos) VALUES (?, ?, ?)";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, curso.getCodigo());
            pst.setString(2, curso.getNombre());
            pst.setInt(3, curso.getCreditos());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Curso find(String id) {
        Curso curso = null;
        String query = "SELECT * FROM curso WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso(
                            rs.getString("chrCurCodigo"),
                            rs.getString("vchCurNombre"),
                            rs.getInt("intCurCreditos")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        String query = "SELECT * FROM curso";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getString("chrCurCodigo"),
                        rs.getString("vchCurNombre"),
                        rs.getInt("intCurCreditos")
                );
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    // Implementación del método update que falta
    @Override
    public void update(Curso curso) {
        String query = "UPDATE curso SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, curso.getNombre());
            pst.setInt(2, curso.getCreditos());
            pst.setString(3, curso.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
