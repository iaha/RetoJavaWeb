<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reto Java Web</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href='<%= request.getAttribute("mainCSS") %>' />
</head>
<body>
  <div class="container">
	<div class="wrapper">
	  <form class="validate-form" name="params"
	  	action="test" method="post" onSubmit="return validateForm()">
		<h2>Introduzca los parámetros</h2>
	  	<table>
	 	  <tr>
	 	  	<td>Dimensión de las Matrices: <span class="required">*</span></td>
	 	  	<td><input type="text" name="size" class="form-control" placeholder="3-3" autofocus
	 	  		onKeyPress="return check(0,event,value)" onInput="checkLength(0,this)" required></td>
	  	  </tr>
	  	  <tr>
	  	    <td>Contenido Matriz numérica: <span class="required">*</span></td>
	  	    <td><input type="text" name="matrix_a" class="form-control" placeholder="1234.." required
	  	    	disabled onKeyPress="return check(1,event,value)" onInput="checkLength(1,this)"></td>
	  	  </tr>
	  	  <tr>
	  	    <td>Contenido Matriz letras: <span class="required">*</span></td>
	  	    <td><input type="text" name="matrix_b" class="form-control" placeholder="ABCD.." required
	  	    	disabled onKeyPress="return check(2,event,value)" onInput="checkLength(2,this)"></td>
		  </tr>
		  <tr>
		  	<td>Selección Matriz: <span class="required">*</span></td>
		  	<td><select name="selection" class="form-control" disabled required>
		  		<option value="">Seleccione una opción..</option>
		  		<option value="numérica">Matriz Numérica</option>
		  		<option value="letras">Matriz Letras</option>
		  	</select></td>
		  </tr>
		  <tr>
		  	<td>Operación: <span class="required">*</span></td>
		  	<td><select name="operation" class="form-control" disabled required>
		  		<option value="">Seleccione una opción..</option>
		  		<option value="ocurrencias">Consultar ocurrencias</option>
		  		<option value="repetidos">Eliminar repetidos</option>
		  		<option value="ordenar">Ordenar ascendentemente</option>
		  	</select></td>
	  	  </tr>
	  	  <tr></tr>
	  	  <tr>
		  	<td>
		  		<span class="required">(*) Campos obligatorios</span>
	  		</td>
		  	<td>
		  		<input type="submit" value="Enviar" class="btn btn-lg btn-success pull-right" disabled>
		  		<input type="reset" value="Clear" class=" btn btn-lg btn-default pull-right">
		  	</td>
		  </tr>
		  <tr>
		  	<td colspan="2">
		  		<span id="message-box" class="required" role="alert">
		  			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  					<span class="sr-only">Error:</span>
					<span id="message"></span>
		  		</span>
	  		</td>
  		  </tr>
	  	</table>
	  </form>
	</div></div>
	<!-- Include all JavaScripts files -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src='<%= request.getAttribute("validationJS") %>'></script>
</body>
</html>