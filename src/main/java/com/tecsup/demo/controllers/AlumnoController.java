package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import com.tecsup.demo.services.impl.AlumnoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AlumnoController", urlPatterns = {"/AlumnoController", "/aController", "/sAlumno"})
public class AlumnoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        AlumnoService servicio = new AlumnoServiceImpl();

        if ("eliminar".equals(accion)) {
            String codigo = request.getParameter("txtcodigo");
            servicio.eliminar(codigo);
        }

        response.sendRedirect("alumnoMan.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        AlumnoService servicio = new AlumnoServiceImpl();

        Alumno alumno = new Alumno();
        alumno.setCodigo(request.getParameter("txtcodigo"));
        alumno.setNombres(request.getParameter("txtNombres"));
        alumno.setApellidos(request.getParameter("txtApellidos"));
        alumno.setFechaNacimiento(request.getParameter("txtFechaNacimiento"));
        alumno.setSexo(request.getParameter("txtSexo"));

        System.out.println("Alumno recibido: " + alumno);

        switch (accion) {
            case "insertar":
                servicio.registrar(alumno);
                break;
            case "actualizar":
                servicio.actualizar(alumno);
                break;
        }

        response.sendRedirect("alumnoMan.jsp");
    }
}
