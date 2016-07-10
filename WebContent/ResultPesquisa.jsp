<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado da Pesquisa</title>
</head>
<body>
	<%
		if (request.getAttribute("mensageErro") != "") {
	%>
	<p>
		<br> Mensagem de Erro : ${requestScope["mensageErro"]}
	</p>
	<%
		} else {
	%>
	Resultado da Pesquisa :
	<br>Nome : ${requestScope["nome"]}
	<br>Endereço : ${requestScope["endereco"]}
	<br>Email : ${requestScope["email"]}
	<br>Nascimento: ${requestScope["dataNascimento"]}
	<%
		}
	%>
</body>
</html>