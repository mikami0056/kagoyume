<%-- 
    Document   : add
    Created on : 2016/05/16, 10:51:45
    Author     : SHO
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="model.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=add"/><%-- ログ出力用 --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごいっぱいのゆめ</title>
    </head>
    <body>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <%= request.getAttribute("name")%>を<%= request.getAttribute("buyNumber")%>個カートに追加しました。<br>
        <form action="/kagoyume/Cart" method="GET">
            <input type="submit" value="カートを確認">
        </form>
        <%= mh.indexJumper()%>
    </body>
</html>
