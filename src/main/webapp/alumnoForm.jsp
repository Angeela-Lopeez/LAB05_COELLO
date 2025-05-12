<%@page import="com.tecsup.demo.model.entities.Alumno"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>${alumno == null ? 'Nuevo Alumno' : 'Editar Alumno'}</title>
  <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
<h2>${alumno == null ? 'Nuevo Alumno' : 'Editar Alumno'}</h2>
<form action="alumno" method="post">
  <input type="hidden" name="action" value="${alumno == null ? 'create' : 'update'}">
  <c:if test="${alumno != null}">
    <input type="hidden" name="id" value="${alumno.id}">
  </c:if>

  <div class="form-group">
    <label>Nombre:</label>
    <input type="text" name="nombre" value="${alumno.nombre}" required>
  </div>

  <div class="form-group">
    <label>Apellido:</label>
    <input type="text" name="apellido" value="${alumno.apellido}" required>
  </div>

  <div class="form-group">
    <label>DNI:</label>
    <input type="text" name="dni" value="${alumno.dni}" required>
  </div>

  <div class="form-group">
    <label>Correo:</label>
    <input type="email" name="correo" value="${alumno.correo}" required>
  </div>

  <div class="form-group">
    <button type="submit" class="button">Guardar</button>
    <a href="alumno?action=list" class="button">Cancelar</a>
  </div>
</form>
</body>
</html>
