<%-- 
    Document   : registrationcomplete
    Created on : 2016/05/15, 22:02:53
    Author     : SHO
--%>
<%@page import="model.UserData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=registrationcomplete"/><%-- ログ出力用 --%>
<%
    UserData udb = (UserData)request.getAttribute("udb");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="3; URL=http://localhost:8084/kagoyume/index.jsp">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>以下の情報でユーザ登録が完了しました</h1>
        名前:<%= udb.getName()%>
        <p>パスワード:<%for(int i = 0; i < udb.getPassWord().length(); i++){
            out.print("●");
        }%></p>
        <p>メール:<%= udb.getMail()%></p>
        <p>住所:<%= udb.getAddress()%></p>
    </body>
    
    <%= mh.indexJumper()%>
</html>
