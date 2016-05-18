<%-- 
    Document   : myupdate
    Created on : 2016/05/16, 0:19:57
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ModelHelper mh = new ModelHelper();
    HttpSession hs = request.getSession();
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>ユーザー情報更新</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <form action="/kagoyume/MyData" method="POST">
        <p>ユーザー名:<input type="text" name="userName" value="<%= loginAccount.getName()%>" placeholder="姓">
        <p>パスワード:<input type="password" name="password" value="<%= loginAccount.getPassWord()%>"></p>
        <p>メールアドレス:<input type="text" name="mail" value="<%= loginAccount.getMail()%>"></p>
        <p>住所:<input type="text" name="address" value="<%= loginAccount.getAddress()%>" placeholder="住所"></p>
        <input type="submit" value="更新">
        <input type="hidden" name="operation" value="update">
        </form>
    </body>
    <%= mh.indexJumper()%>
</html>
