<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">	
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Assessment</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="/">Home</a></li>


<!--       <li class="active"><a href="/usuarios">Usuário</a></li> -->
<!--       <li class="active"><a href="/clientes">Cliente</a></li> -->
 	<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Servicos <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="/cadastro">Cadastro Produto</a></li>
      <li><a href="/exportProd">Exportar csv Produto</a></li>
          <li><a href="/exportCot">Exportar csv Cotacao</a></li>
        </ul>
      </li>
    
    </ul>
  </div>
</nav>

	<form action="/alterar" method="post" enctype="multipart/form-data">
	Nome do Produto:
	<input name="nome" type="text">
	<br>
	Foto do Produto:
	<input name="nomeImagem" type="file">
	<br>
	Fornecedor:
	<input name="fornecedor" type="text">
	<br>
	Valor:    R$<input name="valor" type="number">
	<br>



	<input type="submit" value="Enviar">	
	</form>

</body>
</html>