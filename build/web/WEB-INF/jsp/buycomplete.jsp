<%-- 
    Document   : buycomplete
    Created on : 2016/05/16, 12:06:52
    Author     : SHO
--%>

<%@page import="java.util.Set"
        import="java.util.Map"
        import="java.util.List"
        import="java.util.LinkedHashMap"
        import="java.util.LinkedHashSet"
        import="model.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=buyconplete"/><%-- ログ出力用 --%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>購入完了</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <p>購入が完了しました</p>
    </body>
    <%= mh.indexJumper()%>
</html>
