<%--
  Created by IntelliJ IDEA.
  com.hz.classes.User: 23-rd
  Date: 18.04.13
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>
        Бла бла бла
    </title>
  </head>
  <body>
      <p>Покажи мне свет </p>
      <form name="Form1"
            method="GET"
            action="http://localhost:5050/${pageContext.request.contextPath}/SignIn"/>
      <div>
      <table align="center">
          <tr>
              <td>
                  <input name="login" type="text" placeholder="login"/>
              </td>
          </tr>
          <tr>
              <td>
                  <input name="password" type="text" placeholder="password"/>
              </td>
          </tr>
          <tr>
              <td>
                  <input name="submit" type="submit" value="sign_in"/>
              </td>
          </tr>
      </table>
  </div>
  </body>
</html>