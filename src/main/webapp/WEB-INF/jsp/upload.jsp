<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Upload</title>
</head>
<body>
	<form action="#" th:action="@{/amazon/upload}" method="post" enctype="multipart/form-data">
	Upload Arquivo:
	<input type="file" name="file">
	<input type="submit" value="Upload">
	
	
	</form>
</body>
</html>