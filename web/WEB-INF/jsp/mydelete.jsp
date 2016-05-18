<%-- 
    Document   : mydelete
    Created on : 2016/05/16, 0:36:12
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=mydata"/><%-- ログ出力用 --%>
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
        <h1>ユーザー情報削除</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <p>このユーザーをマジで削除しますか?</p>
        <form action="/kagoyume/MyData" method="POST">
        <p>ユーザーID:<%= loginAccount.getUserID()%></p>
        <p>ユーザー名:<%= loginAccount.getName()%></p>
        <p>パスワード:<%for(int i = 0; i < loginAccount.getPassWord().length(); i++){
            out.print("●");
        }%></p>
        <p>メールアドレス:<%= loginAccount.getMail()%></p>
        <p>住所:<%= loginAccount.getAddress()%></p>
        <p>総購入金額:<%= loginAccount.getSum()%></p>
        <input type="submit" name="delete" value="削除">
        <input type="hidden" name="operation" value="delete">
        </form>
    </body>
    <%= mh.indexJumper()%>
</html>
