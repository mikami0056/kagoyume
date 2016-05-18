<%-- 
    Document   : mydeleteresult
    Created on : 2016/05/16, 0:54:18
    Author     : SHO
--%>

<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=mydeleteresult"/><%-- ログ出力用 --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3; URL=http://localhost:8084/kagoyume/index.jsp">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>ユーザー削除</h1>
        ユーザーを削除しました<br>
    </body>
    <%= mh.indexJumper()%>
</html>
