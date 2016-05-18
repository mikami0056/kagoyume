<%-- 
    Document   : search
    Created on : 2016/05/16, 10:33:27
    Author     : SHO
--%>

<%@page import="model.LogWriter"%>
<%@page import="model.UserData"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URL"%>
<%@page import="model.SearchLogic"%>
<%@page import="model.ItemDataBeans"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=search"/><%-- ログ出力用 --%>
<%
    Map<String, ItemDataBeans> itemDetailsList = (LinkedHashMap<String, ItemDataBeans>)hs.getAttribute("itemSearchList");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        検索条件<br>
        キーワード：<b><%= (String)hs.getAttribute("query")%></b>・分類：<b><%= (String)hs.getAttribute("sort")%></b>・並び順：<b><%= (String)hs.getAttribute("category")%></b>
        での検索結果を表示しています        
        <hr>
        <%for(String index : itemDetailsList.keySet()){%>
        <div>
        <%out.print(index + ":<br>");%>
        <a href="Item?index=<%= index%>"><img src="<%= itemDetailsList.get(index).getImgUrl()%>"></a><br>
        <a href="Item?index=<%= index%>"><%= itemDetailsList.get(index).getName()%></a><br>
        <%= itemDetailsList.get(index).getPrice() + "円<br>"%>
        <hr>
        </div>
        <%}%>
    </body>
    <%= mh.indexJumper()%>
</html>
