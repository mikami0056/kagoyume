<%-- 
    Document   : buyconfirm
    Created on : 2016/05/16, 11:31:57
    Author     : SHO
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="model.ItemDataBeans"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=buyconfirm"/><%-- ログ出力用 --%>
<%
    ItemDataBeans item = (ItemDataBeans)request.getAttribute("buyItem");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>購入確認</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <h2>以下の商品を購入します</h2>
        <p>名前：<%= item.getName()%></p>
        <p>個数：<%= item.getNumber()%></p>
        <p>価格：<%= item.getPrice()%></p>
        <p>合計：<%= item.getNumber()*item.getPrice()%></p>
        <form action="/kagoyume/Buy" method="POST">
            <label for="1">
            <input type="radio" name="type" value="1" id="1" checked>通常発送
            </label>
            <label for="2">
            <input type="radio" name="type" value="2" id="2">お急ぎ発送
            </label>
            <label for="3">
            <input type="radio" name="type" value="3" id="3">特急発送
            </label>
            <input type="submit" value="購入">
            <input type="hidden" name="productID" value="<%= item.getProductID()%>">
        </form>
    </body>
    <%= mh.indexJumper()%>
</html>
