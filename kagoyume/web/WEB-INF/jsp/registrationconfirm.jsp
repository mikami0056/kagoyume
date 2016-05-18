<%-- 
    Document   : registrationconfirm
    Created on : 2016/05/15, 21:06:18
    Author     : SHO
--%>
<%@page import="model.ModelHelper"%>
<%@page import="model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession hs = request.getSession();
    ModelHelper mh = (ModelHelper)hs.getAttribute("mh");
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
    boolean exist = mh.existAccount(loginAccount);
    UserData udb = (UserData)hs.getAttribute("udb");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>新規登録画面</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <p>名前:<%= udb.getName()%></p>
        <p>パスワード:<%for(int i = 0; i < udb.getPassWord().length(); i++){
            out.print("●");
        }%></p>
        <p>メール:<%= udb.getMail()%></p>
        <p>住所:<%= udb.getAddress()%></p>
        以上の内容で登録します。よろしいですか?<br>
        <form action="/kagoyume/Registration" method="POST">
            <input type="submit"  value="登録">
            <input type="hidden" name="regist" value="REGIST">
            <input type="hidden" name="id" value="<%= hs.getAttribute("idForRegist")%>">
        </form>
        <form action="/kagoyume/Registration" method="POST">
            <input type="submit" value="登録内容を変更">
            <input type="hidden" name="reInput" value="REINPUT">
            <input type="hidden" name="id" value="<%= hs.getAttribute("idForRegist")%>">
        </form>
        <%= mh.indexJumper()%>
    </body>
</html>
