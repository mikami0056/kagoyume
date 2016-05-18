<%-- 
    Document   : loginheader
    Created on : 2016/05/15, 23:22:46
    Author     : SHO
--%>

<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="model.UserData"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/jsphelper.jsp" %>
<%
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
    boolean exist = mh.existAccount(loginAccount);
%>
<p align="right"><%= mh.loginJumper("")%>
        <%if(exist){
            out.print(mh.userPageJumper(loginAccount.getName()));
            out.print(mh.cartJumper() + " ・ " + mh.loginJumper("ログアウト"));
        } else {
            out.print(mh.loginJumper());
}%></p>
<hr>