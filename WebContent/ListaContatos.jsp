<%@ page import="up.jservlets.dao.PessoaDAO" %>
<%@ page import="up.jservlets.bean.Pessoa" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <body>
    <table>
      <%
      PessoaDAO dao = new PessoaDAO();
      dao.setPath("C:\\temp\\");
      List<Pessoa> pessoas = dao.getLista();
      
      for (Pessoa pessoa : pessoas ) {
      %>
        <tr>
          <td><%=pessoa.getNome() %></td> 
          <td><%=pessoa.getEmail() %></td>
          <td><%=pessoa.getEndereco() %></td>
          <td><%=pessoa.getDataNascimento() %></td>
        </tr>
      <%
      }
      %>
    </table>
  </body>
</html>