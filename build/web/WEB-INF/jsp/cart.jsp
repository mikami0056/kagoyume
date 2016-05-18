<%-- 
    Document   : cart
    Created on : 2016/05/15, 23:45:40
    Author     : SHO
--%>
<%@page import="java.util.Map"
        import="java.util.Set"
        import="java.util.List"
        import="java.util.LinkedHashMap"
        import="java.util.LinkedHashSet"
        import="model.UserData"
        import="model.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=cart"/><%-- ログ出力用 --%>
<%
    Map<String, Set> Cart = (LinkedHashMap<String, Set>)hs.getAttribute("Cart");    
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
    //ログイン状態であればそのユーザーIDを, していなければdefaultIDを設定
    String userID = mh.getUserID(loginAccount);
    boolean exist = mh.existAccount(loginAccount);
    //合計用変数
    int allSum = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート確認</title>
    </head>
    <body>
        <h1>カートの中身</h1>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <%-- CartWithUserIDが存在し, 商品が最低でも一つ入っている--%>
        <%if(Cart != null && Cart.get(userID).size() != 0){
            Integer sum = 0;
            Set<ItemDataBeans> items = Cart.get(userID);
            for(ItemDataBeans item : items){%>
            <img src="<%= item.getImgUrl()%>"><br>
            <a href ="Item?index=<%= item.getIndex()%>">商品名:<%= item.getName()%></a><br>
            個数:<%= item.getNumber()%><br>
            値段:<%= item.getPrice()%><br>
            小計:<%= item.getPrice() * item.getNumber()%><br>
            <%allSum += item.getPrice() * item.getNumber();%>
            <form action="/kagoyume/Buy" method="POST">
                <input type="submit" name="buy" value="購入確認"><%if(!exist){out.println("未ログインのため, ログイン画面にジャンプします");}%>
                <input type="hidden" name="productID" value="<%= item.getProductID()%>">
                <input type="hidden" name="confirm" value="confirm">
            </form>
            <form action="/kagoyume/Delete" method="POST">
                <input type="submit" name="delete" value="削除">
                <input type="hidden" name="productID" value="<%= item.getProductID()%>">
            </form>
            <hr>
            <%}%>
            合計:<%= allSum%>円<br>
            <hr>
            <%= mh.indexJumper()%>
        <%}else{%>
            カートは空です<br>
            <%= mh.indexJumper()%>
        <%}%>
    </body>
</html>
