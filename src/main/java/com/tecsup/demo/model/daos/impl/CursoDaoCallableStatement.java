package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.CursoDao;
import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.util.DBConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDaoCallableStatement implements CursoDao {
    private Connection conn;

    public CursoDaoCallableStatement() {
        this.conn = DBConn.getConnection();
    }

    @Override
    public void create(Curso curso) {
        String sql = "{call sp_insertar_curso(?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setInt(2, curso.getCreditos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Curso find(Integer id) {
        String sql = "{call sp_obtener_curso_por_id(?)}";
        Curso curso = null;
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNombre(rs.getString("nombre"));
                    curso.setCreditos(rs.getInt("creditos"));
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
        String sql = "{call sp_listar_cursos()}";
        try (CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCreditos(rs.getInt("creditos"));
                cursos.add(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    @Override
    public void update(Curso curso) {
        String sql = "{call sp_actualizar_curso(?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, curso.getId());
            stmt.setString(2, curso.getNombre());
            stmt.setInt(3, curso.getCreditos());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "{call sp_eliminar_curso(?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}