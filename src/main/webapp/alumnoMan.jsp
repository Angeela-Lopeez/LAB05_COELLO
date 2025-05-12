<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@ page import="com.tecsup.demo.services.AlumnoService" %>
<%@ page import="com.tecsup.demo.services.impl.AlumnoServiceImpl" %>
<%@ page import="com.tecsup.demo.model.entities.Alumno" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if(misesion.getAttribute("eladministrador")==null){
        response.sendRedirect("error.jsp");
    } else {
        Administrador adm = (Administrador)misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
        AlumnoService servicio = new AlumnoServiceImpl();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mantenimiento de Alumnos - <%=nombre%></title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container" style="margin-top:70px;">
    <h1>Mantenimiento de Alumnos</h1>
    <div style="padding: 10px;">
        <button class="btn btn-primary">
            <a class="nav-link link-light" href="alumnoInsertar.jsp">Nuevo Alumno</a>
        </button>
    </div>

    <table class="table table-dark table-hover">
        <tr align="center">
            <th>CODIGO</th>
            <th>NOMBRES</th>
            <th>APELLIDOS</th>
            <th>ACCIONES</th>
        </tr>
        <% for (Alumno alumno : servicio.listar()) { %>
        <tr>
            <td><%=alumno.getCodigo()%></td>
            <td><%=alumno.getNombres()%></td>
            <td><%=alumno.getApellidos()%></td>
            <td>
                <a class="btn btn-warning" href="alumnoEliminar.jsp?id=<%=alumno.getCodigo()%>">Eliminar</a>
                <a class="btn btn-danger" href="alumnoActualizar.jsp?id=<%=alumno.getCodigo()%>">Actualizar</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<% } %>
</body>
</html>
