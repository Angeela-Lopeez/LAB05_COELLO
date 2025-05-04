package com.tecsup.demo.model.daos.impl;

import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.entities.Administrador;
import com.tecsup.demo.util.DBConn;
import java.sql.*;

public class AdministradorDaoCallableStatement implements AdministradorDao {

    private Connection conn;

    public AdministradorDaoCallableStatement() {
        this.conn = DBConn.getConnection();
    }

    @Override
    public Administrador validar(String user, String password) {
        String sql = "{call sp_validar_administrador(?, ?)}";
        Administrador admin = null;

        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, user);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    admin = new Administrador();
                    admin.setCodigo(rs.getString("id_administrador"));
                    admin.setLogin(rs.getString("usuario"));
                    admin.setPassword(rs.getString("password"));
                    admin.setNombres(rs.getString("nombre"));
                    admin.setApellidos(rs.getString("apellido"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}