<%@page import="java.util.*"%>
<%@page import="com.tecsup.demo.model.entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Lista de Alumnos</title></head>
<body>
<h2>Alumnos</h2>
<a href="AlumnoController?op=nuevo">Nuevo Alumno</a>
<table border="1">
  <tr><th>Código</th><th>Nombre</th><th>Apellidos</th><th>Fecha Nac.</th><th>Sexo</th><th>Acciones</th></tr>
  <%
    List<Alumno> lista = (List<Alumno>) request.getAttribute("data");
    for (Alumno a : lista) {
  %>
  <tr>
    <td><%= a.getCodigo() %></td>
    <td><%= a.getNombres() %></td>
    <td><%= a.getApellidos() %></td>
    <td><%= a.getFechaNac() %></td>
    <td><%= a.getSexo() %></td>
    <td>
      <a href="AlumnoController?op=editar&id=<%= a.getCodigo() %>">Editar</a>
      <a href="AlumnoController?op=eliminar&id=<%= a.getCodigo() %>">Eliminar</a>
    </td>
  </tr>
  <% } %>
</table>
</body>
</html>
