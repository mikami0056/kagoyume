<%-- 
    Document   : registration
    Created on : 2016/05/15, 20:53:56
    Author     : SHO
--%>

<%@page import="java.util.ArrayList"
        import="java.util.List"
        import="java.util.HashMap"
        import="java.util.Map"
        import="model.UserData"
        import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=registration"/><%-- ログ出力用 --%>
<%
    UserData udb = (UserData)session.getAttribute("udb");
    List<String> caution = (ArrayList<String>)request.getAttribute("caution");
    String warning = (String)request.getAttribute("warning");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <form action="Registration" method="POST">
        <h1>ユーザ新規登録</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <p>ユーザー名:<input type="text" name="userName" value="<%if(udb != null){out.print(udb.getName());}%>" placeholder="姓">
        <% if(caution != null && caution.contains("userName")){out.print("*必須事項");}%></p>
            
        <p>パスワード:<input type="password" name="password" value="<%if(udb != null){out.print(udb.getPassWord());}%>">
        <% if(caution != null && caution.contains("passWord")){out.print("*必須事項");}%></p>
        
        <p>メールアドレス:<input type="text" name="mail" value="<%if(udb != null){out.print(udb.getMail());}%>">
        <% if(caution != null && caution.contains("mail")){out.print("*必須事項");}%></p>
        
        <p>住所:<input type="text" name="address" value="<%if(udb != null){out.print(udb.getAddress());}%>" placeholder="住所">
        <% if(caution != null && caution.contains("address")){out.print("*必須事項");}%></p>
        <% if(warning != null){out.print(warning);}%><br>   
        <input type="submit" value="確認画面へ">
        <input type="hidden" name="id" value="<%= hs.getAttribute("idForRegist")%>">
        </form>
        <%= mh.indexJumper()%>
    </body>
</html>
