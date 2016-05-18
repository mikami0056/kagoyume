<%-- 
    Document   : registrationconfirm
    Created on : 2016/05/15, 21:06:18
    Author     : SHO
--%>
<%@page import="model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=registrationconfirm"/><%-- ログ出力用 --%>
<%
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
            <input type="hidden" name="operation" value="REGIST">
            <input type="hidden" name="id" value="<%= hs.getAttribute("idForRegist")%>">
        </form>
        <form action="/kagoyume/Registration" method="POST">
            <input type="submit" value="登録内容を変更">
            <input type="hidden" name="operation" value="REINPUT">
            <input type="hidden" name="id" value="<%= hs.getAttribute("idForRegist")%>">
        </form>
        <%= mh.indexJumper()%>
    </body>
</html>
