package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Alumno;
import com.tecsup.demo.services.AlumnoService;
import com.tecsup.demo.services.impl.AlumnoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AlumnoController", urlPatterns = {"/alumno"})
public class AlumnoController extends HttpServlet {

    private AlumnoService alumnoService;

    public AlumnoController() {
        this.alumnoService = new AlumnoServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            // Listar todos los alumnos
            List<Alumno> alumnos = alumnoService.findAll();
            request.setAttribute("alumnos", alumnos);
            request.getRequestDispatcher("alumnoMain.jsp").forward(request, response);
        } else switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("alumnoInsertar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Alumno alumno = alumnoService.find(id);
                request.setAttribute("alumno", alumno);
                request.getRequestDispatcher("alumnoActualizar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                alumnoService.delete(idEliminar);
                response.sendRedirect("alumno");
                break;
            default:
                response.sendRedirect("alumno");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) switch (accion) {
            case "insertar":
                Alumno nuevoAlumno = new Alumno();
                nuevoAlumno.setNombre(request.getParameter("nombre"));
                nuevoAlumno.setApellido(request.getParameter("apellido"));
                nuevoAlumno.setEdad(Integer.parseInt(request.getParameter("edad")));
                nuevoAlumno.setCorreo(request.getParameter("correo"));
                alumnoService.create(nuevoAlumno);
                break;
            case "actualizar":
                Alumno alumnoActualizar = new Alumno();
                alumnoActualizar.setIdAlumno(Integer.parseInt(request.getParameter("id")));
                alumnoActualizar.setNombre(request.getParameter("nombre"));
                alumnoActualizar.setApellido(request.getParameter("apellido"));
                alumnoActualizar.setEdad(Integer.parseInt(request.getParameter("edad")));
                alumnoActualizar.setCorreo(request.getParameter("correo"));
                alumnoService.update(alumnoActualizar);
                break;
        }

        response.sendRedirect("alumno");
    }
}