package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoPreparedStatement implements AlumnoDao {

    @Override
    public void create(Alumno alumno) {
        String query = "INSERT INTO alumno (chrAluCodigo, vchAluNombres, vchAluApellidos, chrAluDni, vchAluDireccion, chrAluTelefono, vchAluEmail) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, alumno.getCodigo());
            pst.setString(2, alumno.getNombres());
            pst.setString(3, alumno.getApellidos());
            pst.setString(4, alumno.getDni());
            pst.setString(5, alumno.getDireccion());
            pst.setString(6, alumno.getTelefono());
            pst.setString(7, alumno.getEmail());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Alumno find(String id) {
        Alumno alumno = null;
        String query = "SELECT * FROM alumno WHERE chrAluCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            try (ResultSet rs = pst.executeQuery()) {
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
        String query = "SELECT * FROM alumno";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
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
        String query = "UPDATE alumno SET vchAluNombres = ?, vchAluApellidos = ?, chrAluDni = ?, " +
                "vchAluDireccion = ?, chrAluTelefono = ?, vchAluEmail = ? WHERE chrAluCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, alumno.getNombres());
            pst.setString(2, alumno.getApellidos());
            pst.setString(3, alumno.getDni());
            pst.setString(4, alumno.getDireccion());
            pst.setString(5, alumno.getTelefono());
            pst.setString(6, alumno.getEmail());
            pst.setString(7, alumno.getCodigo());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM alumno WHERE chrAluCodigo = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Alumno> findByNombre(String nombre) {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno WHERE vchAluNombres LIKE ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + nombre + "%");
            try (ResultSet rs = pst.executeQuery()) {
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
        String query = "SELECT * FROM alumno WHERE vchAluApellidos LIKE ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + apellido + "%");
            try (ResultSet rs = pst.executeQuery()) {
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
        String query = "SELECT * FROM alumno WHERE chrAluDni = ?";
        try (Connection con = DBConn.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, dni);
            try (ResultSet rs = pst.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error en búsqueda por DNI: " + e.getMessage());
            e.printStackTrace();
        }
        return alumno;
    }
}
