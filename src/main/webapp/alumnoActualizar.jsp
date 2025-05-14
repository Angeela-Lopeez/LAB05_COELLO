<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@ page import="com.tecsup.demo.model.entities.Alumno" %>
<%@ page import="com.tecsup.demo.services.AlumnoService" %>
<%@ page import="com.tecsup.demo.services.impl.AlumnoServiceImpl" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    HttpSession misesion = request.getSession();
    if(misesion.getAttribute("eladministrador") == null){
        response.sendRedirect("error.jsp");
    } else {
        Administrador adm = (Administrador) misesion.getAttribute("eladministrador");
        String nombre = adm.getNombres() + " " + adm.getApellidos();
        String codigo = request.getParameter("id");
        AlumnoService servicio = new AlumnoServiceImpl();
        Alumno alumno = servicio.buscar(codigo);
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actualizar Alumno - <%=nombre%></title>
</head>
<body>
<jsp:include page="master.jsp" />

<div class="container p-4" style="margin-top:70px;">
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="card text-center">
                <div class="card-header">
                    <h3 class="text-uppercase">ACTUALIZAR ALUMNO</h3>
                </div>
                <div class="card-body">
                    <form action="AlumnoController" method="post">
                        <div class="input-group mt-2">
                            <label class="input-group-text">Código:</label>
                            <input class="form-control" type="text" name="txtcodigo"
                                   value="<%=alumno.getCodigo()%>" required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Nombres:</label>
                            <input class="form-control" type="text" name="txtNombres"
                                   value="<%=alumno.getNombres()%>" required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Apellidos:</label>
                            <input class="form-control" type="text" name="txtApellidos"
                                   value="<%=alumno.getApellidos()%>" required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Fecha de Nacimiento:</label>
                            <input class="form-control" type="date" name="txtFechaNacimiento"
                                   value="<%=alumno.getFechaNacimiento()%>" required>
                        </div>
                        <div class="input-group mt-2">
                            <label class="input-group-text">Sexo:</label>
                            <select class="form-control" name="txtSexo" required>
                                <option value="" <%= alumno.getSexo().isEmpty() ? "selected" : "" %>>Seleccione</option>
                                <option value="M" <%= "M".equals(alumno.getSexo()) ? "selected" : "" %>>Masculino</option>
                                <option value="F" <%= "F".equals(alumno.getSexo()) ? "selected" : "" %>>Femenino</option>
                            </select>
                        </div>
                        <div class="form-group mt-4 d-grid gap-2">
                            <input name="accion" type="hidden" value="actualizar"/>
                            <input class="btn btn-success" type="submit" value="Actualizar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<% } %>
</body>
</html>
