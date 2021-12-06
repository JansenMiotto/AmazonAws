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


<div class="container">	

	<h4>Listagem Fotos:</h4>
	<table class="table table-striped">
	<thead>
	<tr>
		<th>#</th>
		<th>Nome</th>
		<th>Foto</th>
		<th></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="f" items="${fotografavelList}">
		<tr>
		<td>${f.id}</td>
		<td>${f.nome}</td>
		<td><a href="<%=request.getContextPath()%>/download/${f.nomeImagem}">${f.nomeImagem}</a></td>

 		<td><a href="/fotos/${f.id}/excluir">Excluir</a></td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
	
	</div>

	<h3>O Valor total em dolar: U$ ${valorDolar}</h3>

<!-- 	<a href="/exportProd">Exportar os Produtos CSV</a> -->
<!-- 	<br><br> -->
<!-- 	<a href="/exportCot">Exportar as Cotacoes CSV</a> -->
</body>
</html>