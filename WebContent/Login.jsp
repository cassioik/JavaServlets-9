<!DOCTYPE html>
<html>
  <body>
  <%@ include file="Header.jsp" %>  
    <form action="Login"  method="post">
      Userid: <input type="text" name="userid"/><br/>
      Password: <input type="password" name="password" /><br/>
      <input type="submit" value="Login" />
    </form>
  <%@ include file="Footer.jsp" %> 
  </body>
</html>