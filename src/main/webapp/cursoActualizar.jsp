<%@ page import="com.tecsup.demo.model.entities.Administrador" %>
<%@ page import="com.tecsup.demo.model.entities.Curso" %>
<%@ page import="com.tecsup.demo.services.CursoService" %>
<%@ page import="com.tecsup.demo.services.impl.CursoServiceImpl" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actualizar Curso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header">
                    <h4>Actualizar Curso</h4>
                </div>
                <div class="card-body">
                    <form action="curso" method="POST">
                        <input type="hidden" name="accion" value="actualizar">
                        <input type="hidden" name="id" value="${curso.id}">

                        <div class="mb-3">
                            <label for="nombre" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="${curso.nombre}" required>
                        </div>

                        <div class="mb-3">
                            <label for="creditos" class="form-label">Créditos:</label>
                            <input type="number" class="form-control" id="creditos" name="creditos" value="${curso.creditos}" required>
                        </div>

                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary">Actualizar</button>
                            <a href="curso" class="btn btn-secondary">Cancelar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>