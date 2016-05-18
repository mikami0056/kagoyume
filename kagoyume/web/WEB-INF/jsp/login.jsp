<%-- 
    Document   : login
    Created on : 2016/05/15, 18:19:51
    Author     : SHO
--%>
<%@page import="model.UserData"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=login"/><%-- ログ出力用 --%>
<%
    boolean logout = false;
    if((String)request.getAttribute("logout") != null){
        logout = true;
        mh = new ModelHelper();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%if(logout){out.print("<meta http-equiv=\"refresh\" content=\"3;URL=http://localhost:8084/kagoyume/index.jsp\">");}%>
        <title>かごゆめ</title>
    </head>
    <body>
        <%if(logout){%>
        <h2>ログアウト</h2>
        <p>ログアウトしました</p>
        <p>3秒後にトップページへジャンプします</p>
        <%} else {%>
        <h2>ログイン</h2>
        <hr>
        <form action="/kagoyume/Login" method="POST">
            ユーザー名:<input type="text" name="userName" value=""><br>
            パスワード:<input type="password" name="passWord" value=""><br>
            <%
                if("notExist".equals(request.getParameter("flag"))){
                    out.print("ログインできません. ユーザー名又はパスワードを再入力してください.<br>");
                }
            %>
            <input type="submit" name="/kagoyume/Login" value="ログイン">
        </form>
        <form action="/kagoyume/Registration" method="GET">
            <input type="submit" value="新規登録">←ユーザー新規登録の方はこちら<br>
        </form>
        <%}%>
        <%= mh.indexJumper()%>
    </body>
</html>
