package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.impl.AlumnoServiceImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/AlumnoController")
public class AlumnoController extends HttpServlet {
    AlumnoServiceImpl service = new AlumnoServiceImpl();

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        try {
            switch (op) {
                case "listar":
                    req.setAttribute("data", service.findAll());
                    req.getRequestDispatcher("alumnoList.jsp").forward(req, resp);
                    break;
                case "nuevo":
                    req.getRequestDispatcher("alumnoForm.jsp").forward(req, resp);
                    break;
                case "editar":
                    String cod = req.getParameter("id");
                    req.setAttribute("bean", service.find(cod));
                    req.getRequestDispatcher("alumnoForm.jsp").forward(req, resp);
                    break;
                case "guardar":
                    Alumno a = new Alumno();
                    a.setCodigo(req.getParameter("codigo"));
                    a.setNombres(req.getParameter("nombres"));
                    a.setApellidos(req.getParameter("apellidos"));
                    a.setFechaNac(new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fechaNac")));
                    a.setSexo(req.getParameter("sexo"));

                    if (req.getParameter("editar") != null) {
                        service.update(a);
                    } else {
                        service.create(a);
                    }
                    resp.sendRedirect("AlumnoController?op=listar");
                    break;
                case "eliminar":
                    service.delete(req.getParameter("id"));
                    resp.sendRedirect("AlumnoController?op=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
