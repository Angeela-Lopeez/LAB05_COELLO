package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import com.tecsup.demo.services.impl.AlumnoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AlumnoController")
public class AlumnoController extends HttpServlet {

    private AlumnoService service = new AlumnoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Alumno> alumnos = service.listar();
        request.setAttribute("alumnos", alumnos);
        request.getRequestDispatcher("alumnoList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Alumno alumno = new Alumno();
        alumno.setCodigo(request.getParameter("codigo"));
        alumno.setNombres(request.getParameter("nombres"));
        alumno.setApellidos(request.getParameter("apellidos"));
        alumno.setFechaNacimiento(request.getParameter("fechaNacimiento"));
        alumno.setSexo(request.getParameter("sexo"));
        service.registrar(alumno);
        response.sendRedirect("AlumnoController");
    }
}

