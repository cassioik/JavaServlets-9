<%@ page import="up.jservlets.dao.PessoaDAO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userinfo" class="up.jservlets.bean.Pessoa"></jsp:useBean> 
<jsp:setProperty property="*" name="userinfo"/> 
Foram adicionados os seguinte detalhes:
<br> 
<jsp:getProperty property="nome" name="userinfo"/><br> 
<jsp:getProperty property="email" name="userinfo"/><br> 
<jsp:getProperty property="endereco" name="userinfo"/><br>
<jsp:getProperty property="dataNascimento" name="userinfo"/><br>

	<%PessoaDAO dao = new PessoaDAO();
   		dao.setPath("C:\\temp");
       	dao.setArchive(userinfo.getEmail());
       	dao.salvaPessoa(userinfo);
    %>