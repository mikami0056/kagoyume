<%-- 
    Document   : loginsuccess
    Created on : 2016/05/15, 19:22:23
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=loginsuccess"/><%-- ログ出力用 --%>
<%
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
    StringBuffer url = (StringBuffer)hs.getAttribute("URL");
    String login = (String)request.getAttribute("done");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if(url != null && login == null){out.print("<meta http-equiv=\"refresh\" content=\"3;URL=" + url + "\">");}%>
        <title>かごゆめ</title>
    </head>
    <body>
        <%if(login != null){%>
            <p>ログインしています</p>
        <%} else {%>
        <p>ログインが成功しました</p>
        <%if(url != null){out.println("3秒後に元のページへジャンプします<br>");}%>
        <%}%>
        <p>こんにちは, <%= loginAccount.getName()%>さん</p>
        <form action="/kagoyume/Login" method="POST">
            <input type="submit" value="ログアウト">
            <input type="hidden" name="logout" value="logout">
        </form>
    </body>
    <%= mh.indexJumper()%>
</html>
