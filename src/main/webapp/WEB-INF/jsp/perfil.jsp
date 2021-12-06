<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Perfil Usuário</title>
</head>
<body>
<div>
<div th:text="${usuario.id}"></div><br>
<div th:text="${usuario.nome}"></div><br>
<div th:text="${usuario.endereco}"></div><br>
<img th:src="'data:image/png;base64,' + ${imagem}"/>
</div>
</body>
</html>