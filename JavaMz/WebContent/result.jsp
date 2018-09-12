<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="matrix" scope="request" class="org.test.webapp.Matrix"></jsp:useBean>
<html>
<head>
<meta charset="UTF-8">
<title>This is the response</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='<%= request.getAttribute("mainCSS") %>' />
</head>
<body>
  <div class="container">
	<div class="wrapper">
		<h2>El resultado de la operación<br></h2>
		<% String operation = matrix.getOperation(); %>
		<h3><%= operation %></h3>
		<%  String original = matrix.getData();
			String resultado = matrix.getResultado(); %>
		La Matriz original es:<br><%= matrix.printToHTML(original) %><br>
		El resultado al final es:<br><%= (operation.startsWith("Consultar")) ?
				resultado : matrix.printToHTML(resultado) %><br>
		
	</div>
  </div>
<!-- Include all JavaScripts files -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>