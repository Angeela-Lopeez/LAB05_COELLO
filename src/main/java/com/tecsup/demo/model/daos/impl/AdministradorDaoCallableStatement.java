
package com.tecsup.demo.model.daos.impl;
import com.tecsup.demo.model.daos.AdministradorDao;
import com.tecsup.demo.model.entities.Administrador;
import com.tecsup.demo.services.AdministradorService;
import com.tecsup.demo.services.impl.AdministradorServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdministradorController", urlPatterns = {"/sValidador", "/admin"})
public class AdministradorDaoCallableStatement extends HttpServlet implements AdministradorDao {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sUsuario = request.getParameter("txtUsuario");
        String sPassword = request.getParameter("txtPassword");

        System.out.println("Controlador recibió: usuario=" + sUsuario + ", password=" + sPassword);

        AdministradorService servicio = new AdministradorServiceImpl();

        Administrador adm = servicio.validar(sUsuario, sPassword);

        System.out.println("Resultado de validación: " + (adm != null ? "Exitoso" : "Fallido"));

        if (adm != null) {
            request.getSession().setAttribute("eladministrador", adm);
            System.out.println("Redirigiendo a principal.jsp");
            response.sendRedirect("principal.jsp");
        } else {
            System.out.println("Redirigiendo a error.jsp");
            response.sendRedirect("error.jsp");
        }
    }
    @Override
    public Administrador validar(String user, String password) {
        AdministradorService servicio = new AdministradorServiceImpl();
        return servicio.validar(user, password);
    }

}