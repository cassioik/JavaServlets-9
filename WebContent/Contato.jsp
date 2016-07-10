<!DOCTYPE html>
<html>
<body>
<%@ include file="AuthHeader.jsp" %> 
<!--  <form action="GravaContato.jsp"  method="post"> -->
<form action="ResultPesquisa.jsp"  method="post">
Nome: <input type="text" name="nome" /><br />
E-mail: <input type="text" name="email" /><input type="submit" name="action" value="Pesquisar" /><br />
Endereço: <input type="text" name="endereco" /><br />
Data Nascimento: <input type="text" name="dataNascimento" /><br />

<input type="submit" name="action" value="Gravar" />
<input type="submit" name="action" value="Excluir" />  
<input type="submit" name="action" value="Listar" />    
</form>
</body>
</html>