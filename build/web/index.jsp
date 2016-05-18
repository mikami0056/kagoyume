<%-- 
    Document   : index
    Created on : 2016/04/27, 15:26:04
    Author     : SHO
--%>
<%@page 
import="java.util.Set"
import="java.util.Map"
import="java.util.LinkedHashMap"
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %><%-- セッションの取得や, ModelHelperの取得を行う --%>
<jsp:include page="/WEB-INF/jsp/logwriter.jsp?where=index"/><%-- ログ出力用 --%>
<%
    System.out.println("======ECサイトスタート=======");
    Map<String, String> categories = con.getCategories();
    Map<String, String> sortOrder = con.getSortOrder();
    hs.setAttribute("URL", request.getRequestURL());
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
    </head>
    <body>
        <h1>検索フォーム</h1>
        <%-- ログイン用ヘッダーは動的インクルードさせる --%>
        <jsp:include page="/WEB-INF/jsp/loginheader.jsp"/>
        <form action="Search" method="GET">
            <p>キーワード:
            <input type="text" name="query" placeholder="キーワードを入力" required>
            <%
                if("error".equals(request.getParameter("flag"))){
                    out.print("検索フォームに何も入力されていません");
                }
            %>
            </p>
            
            <p>分類:
            <select name="category">
                <option value="指定なし">----</option>
                <%for(String key : categories.keySet()){%>
                <option value="<%= key%>"><%= categories.get(key)%></option>
                <%}%>
            </select></p>
            
            <p>並び順:
            <select name="sort">
                <option value="指定なし">----</option>
                <%for(String key : sortOrder.keySet()){%>
                <option value="<%=key%>"><%= sortOrder.get(key)%></option>
                <%}%>
            </select></p>
            <input type="submit" value="検索">
            <br><br>            
        </form>
    </body>
</html>
