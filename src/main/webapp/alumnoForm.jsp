<%@page import="com.tecsup.demo.model.entities.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  Alumno a = (Alumno) request.getAttribute("bean");
  boolean editando = (a != null);
%>
<html>
<head><title>Formulario Alumno</title></head>
<body>
<h2><%= editando ? "Editar Alumno" : "Nuevo Alumno" %></h2>
<form action="AlumnoController" method="post">
  <input type="hidden" name="op" value="guardar"/>
  <% if (editando) { %>
  <input type="hidden" name="editar" value="true"/>
  <% } %>
  Código: <input type="text" name="codigo" value="<%= editando ? a.getCodigo() : "" %>" <%= editando ? "readonly" : "" %> /><br/>
  Nombres: <input type="text" name="nombres" value="<%= editando ? a.getNombres() : "" %>" /><br/>
  Apellidos: <input type="text" name="apellidos" value="<%= editando ? a.getApellidos() : "" %>" /><br/>
  Fecha Nacimiento: <input type="date" name="fechaNac" value="<%= editando ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(a.getFechaNac()) : "" %>" /><br/>
  Sexo:
  <select name="sexo">
    <option value="M" <%= editando && "M".equals(a.getSexo()) ? "selected" : "" %>>Masculino</option>
    <option value="F" <%= editando && "F".equals(a.getSexo()) ? "selected" : "" %>>Femenino</option>
  </select><br/>
  <input type="submit" value="Guardar"/>
</form>
</body>
</html>
