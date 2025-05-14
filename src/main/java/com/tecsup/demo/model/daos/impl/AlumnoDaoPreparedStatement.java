package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AlumnoDao;
import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.util.DBConn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDaoPreparedStatement implements AlumnoDao {

    private Connection con;
    private ResultSet rs;
    private PreparedStatement pst;

    @Override
    public void create(Alumno alumno) {
        try (Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("INSERT INTO alumno (chrAluCodigo, vchAluNombres, vchAluApellidos, dtmAluFechaNac, chrAluSexo)" +
                    " VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, alumno.getCodigo());
            pst.setString(2, alumno.getNombres());
            pst.setString(3, alumno.getApellidos());
            pst.setString(4, alumno.getFechaNacimiento());
            pst.setString(5, alumno.getSexo());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en creación de alumno: " + e);
        }
    }

    @Override
    public Alumno find(String id) {
        Alumno alumno = null;
        try (Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("SELECT * FROM alumno WHERE chrAluCodigo = ?");
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setCodigo(rs.getString(1));
                alumno.setNombres(rs.getString(2));
                alumno.setApellidos(rs.getString(3));
                alumno.setFechaNacimiento(rs.getString(4));
                alumno.setSexo(rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar alumno: " + e);
        }
        return alumno;
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> alumnos = new ArrayList<>();
        try (Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("SELECT * FROM alumno");
            rs = pst.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setCodigo(rs.getString(1));
                alumno.setNombres(rs.getString(2));
                alumno.setApellidos(rs.getString(3));
                alumno.setFechaNacimiento(rs.getString(4));
                alumno.setSexo(rs.getString(5));
                alumnos.add(alumno);
            }
        } catch (Exception e) {
            System.out.println("Error en listar alumnos: " + e);
        }
        return alumnos;
    }

    @Override
    public void update(Alumno alumno) {
        try (Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("UPDATE alumno SET vchAluNombres=?, vchAluApellidos=?, dtmAluFechaNac=?, chrAluSexo=? WHERE chrAluCodigo=?");
            pst.setString(1, alumno.getNombres());
            pst.setString(2, alumno.getApellidos());
            pst.setString(3, alumno.getFechaNacimiento());
            pst.setString(4, alumno.getSexo());
            pst.setString(5, alumno.getCodigo());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar alumno: " + e);
        }
    }

    @Override
    public void delete(String id) {
        try (Connection con = DBConn.getConnection()) {
            pst = con.prepareStatement("DELETE FROM alumno WHERE chrAluCodigo=?");
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar alumno: " + e);
        }
    }

    @Override
    public void save(Alumno alumno) throws SQLException {

    }
}
