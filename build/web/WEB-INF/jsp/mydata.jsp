<%-- 
    Document   : mydata
    Created on : 2016/05/15, 23:58:56
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=loginsuccess"/><%-- ログ出力用 --%>
<%
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>ユーザー情報</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <p>ユーザーID:<%= loginAccount.getUserID()%></p>
        <p>名前:<%= loginAccount.getName()%></p>
        <p>住所:<%= loginAccount.getAddress()%></p>
        <p>メールアドレス:<%= loginAccount.getMail()%></p>
        <p>購入金額合計:<%= loginAccount.getSum()%></p>
        <form action="/kagoyume/MyData" method="GET">
            <input type="submit" name="update" value="登録情報更新">
            <input type="hidden" name="operation" value="update">
        </form>   
        <form action="/kagoyume/MyData" method="GET">
            <input type="submit" name="delete" value="登録情報削除">
            <input type="hidden" name="operation" value="delete">
        </form>
    </body>
    <%= mh.indexJumper()%>
</html>
