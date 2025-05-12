package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoPreparedStatement implements CursoDao {

    @Override
    public void create(Curso curso) {
        String sql = "INSERT INTO cursos (chrCurCodigo, vchCurNombre, intCurCreditos) VALUES (?, ?, ?)";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, curso.getCodigo());
            pst.setString(2, curso.getNombre());
            pst.setInt(3, curso.getCreditos());
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Curso find(String id) {
        Curso curso = null;
        String sql = "SELECT * FROM cursos WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso();
                    curso.setCodigo(rs.getString("chrCurCodigo"));
                    curso.setNombre(rs.getString("vchCurNombre"));
                    curso.setCreditos(rs.getInt("intCurCreditos"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getString("chrCurCodigo"));
                curso.setNombre(rs.getString("vchCurNombre"));
                curso.setCreditos(rs.getInt("intCurCreditos"));
                lista.add(curso);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void update(Curso curso) {
        String sql = "UPDATE cursos SET vchCurNombre = ?, intCurCreditos = ? WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

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
        String sql = "DELETE FROM cursos WHERE chrCurCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, id);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Curso> findByRangeCreditos(int min, int max) {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE intCurCreditos BETWEEN ? AND ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, min);
            pst.setInt(2, max);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setCodigo(rs.getString("chrCurCodigo"));
                    curso.setNombre(rs.getString("vchCurNombre"));
                    curso.setCreditos(rs.getInt("intCurCreditos"));
                    lista.add(curso);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Curso> findByNombre(String nombre) {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM cursos WHERE vchCurNombre LIKE ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setCodigo(rs.getString("chrCurCodigo"));
                    curso.setNombre(rs.getString("vchCurNombre"));
                    curso.setCreditos(rs.getInt("intCurCreditos"));
                    lista.add(curso);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
