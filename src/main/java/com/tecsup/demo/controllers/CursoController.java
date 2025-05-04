package com.tecsup.demo.controllers;

import com.tecsup.demo.model.entities.Curso;
import com.tecsup.demo.services.CursoService;
import com.tecsup.demo.services.impl.CursoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CursoController", urlPatterns = {"/curso"})
public class CursoController extends HttpServlet {

    private CursoService cursoService;

    public CursoController() {
        this.cursoService = new CursoServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null) {
            // Listar todos los cursos
            List<Curso> cursos = cursoService.findAll();
            request.setAttribute("cursos", cursos);
            request.getRequestDispatcher("cursoMain.jsp").forward(request, response);
        } else switch (accion) {
            case "nuevo":
                request.getRequestDispatcher("cursoInsertar.jsp").forward(request, response);
                break;
            case "editar":
                int id = Integer.parseInt(request.getParameter("id"));
                Curso curso = cursoService.find(id);
                request.setAttribute("curso", curso);
                request.getRequestDispatcher("cursoActualizar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                cursoService.delete(idEliminar);
                response.sendRedirect("curso");
                break;
            default:
                response.sendRedirect("curso");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) switch (accion) {
            case "insertar":
                Curso nuevoCurso = new Curso();
                nuevoCurso.setNombre(request.getParameter("nombre"));
                nuevoCurso.setCreditos(Integer.parseInt(request.getParameter("creditos")));
                cursoService.create(nuevoCurso);
                break;
            case "actualizar":
                Curso cursoActualizar = new Curso();
                cursoActualizar.setId(Integer.parseInt(request.getParameter("id")));
                cursoActualizar.setNombre(request.getParameter("nombre"));
                cursoActualizar.setCreditos(Integer.parseInt(request.getParameter("creditos")));
                cursoService.update(cursoActualizar);
                break;
        }

        response.sendRedirect("curso");
    }
}