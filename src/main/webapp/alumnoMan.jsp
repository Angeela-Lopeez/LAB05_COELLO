<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@ page import="com.tecsup.demo.services.AlumnoService" %>
<%@ page import="com.tecsup.demo.services.impl.AlumnoServiceImpl" %>
<%@ page import="com.tecsup.demo.model.entities.Alumno" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
    <meta charset="UTF-8">
    <title>Mantenimiento de Alumnos - <%=nombre %></title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container" style="margin-top:70px;">
    <h1>Mantenimiento de Alumnos</h1>
    <div style="padding: 10px;">
        <a href="alumnoInsertar.jsp" class="btn btn-danger">Nuevo Alumno</a>
    </div>
    <table class="table table-dark table-hover">
        <tr align="center">
            <th>CODIGO</th>
            <th>NOMBRES</th>
            <th>APELLIDOS</th>
            <th>FECHA NACIMIENTO</th>
            <th>SEXO</th>
            <th>ACCIONES</th>
        </tr>
        <% for (Alumno a : servicio.listar()) { %>
        <tr>
            <td><%=a.getCodigo() %></td>
            <td><%=a.getNombres() %></td>
            <td><%=a.getApellidos() %></td>
            <td><%=a.getFechaNacimiento() %></td>
            <td><%=a.getSexo() %></td>
            <td>
                <a class="btn btn-warning" href="alumnoEliminar.jsp?id=<%=a.getCodigo()%>">Eliminar</a>
                <a class="btn btn-danger" href="alumnoActualizar.jsp?id=<%=a.getCodigo()%>">Actualizar</a>
            </td>
        </tr>
        <% } %>
    </table>
</div>
</body>
<% } %>
</html>
