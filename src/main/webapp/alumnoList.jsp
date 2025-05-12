<%@page import="java.util.*"%>
<%@page import="com.tecsup.demo.model.entities.Alumno"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Lista de Alumnos</title>
  <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<h2>Lista de Alumnos</h2>
<a href="alumno?action=new" class="button">Nuevo Alumno</a>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>DNI</th>
    <th>Correo</th>
    <th>Acciones</th>
  </tr>
  <c:forEach var="alumno" items="${alumnos}">
    <tr>
      <td>${alumno.id}</td>
      <td>${alumno.nombre}</td>
      <td>${alumno.apellido}</td>
      <td>${alumno.dni}</td>
      <td>${alumno.correo}</td>
      <td>
        <a href="alumno?action=edit&id=${alumno.id}" class="button">Editar</a>
        <a href="alumno?action=delete&id=${alumno.id}" class="button"
           onclick="return confirm('¿Está seguro de eliminar este alumno?')">Eliminar</a>
      </td>
    </tr>
  </c:forEach>
</table>
<a href="principal.jsp" class="button">Volver al Menú Principal</a>
</body>
</html>