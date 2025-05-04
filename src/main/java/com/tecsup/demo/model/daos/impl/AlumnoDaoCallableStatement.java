package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoCallableStatement implements AlumnoDao {

    private Connection conn;

    public AlumnoDaoCallableStatement() {
        this.conn = DBConn.getConnection();
    }

    @Override
    public void create(Alumno alumno) {
        String sql = "{call sp_insertar_alumno(?, ?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApellido());
            stmt.setInt(3, alumno.getEdad());
            stmt.setString(4, alumno.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alumno find(Integer id) {
        String sql = "{call sp_obtener_alumno_por_id(?)}";
        Alumno alumno = null;
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("id_alumno"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setEdad(rs.getInt("edad"));
                    alumno.setCorreo(rs.getString("correo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "{call sp_listar_alumnos()}";
        try (CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setEdad(rs.getInt("edad"));
                alumno.setCorreo(rs.getString("correo"));
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    @Override
    public void update(Alumno alumno) {
        String sql = "{call sp_actualizar_alumno(?, ?, ?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, alumno.getIdAlumno());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getApellido());
            stmt.setInt(4, alumno.getEdad());
            stmt.setString(5, alumno.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "{call sp_eliminar_alumno(?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Implementación del método adicional
    public List<Alumno> buscarPorNombre(String nombre) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "{call sp_buscar_alumnos_por_nombre(?)}";
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("id_alumno"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setEdad(rs.getInt("edad"));
                    alumno.setCorreo(rs.getString("correo"));
                    alumnos.add(alumno);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }
}