<%-- 
    Document   : logwriter
    Created on : 2016/05/17, 15:01:13
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page import="model.LogWriter"%>
<%@page import="model.Common"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession hs = request.getSession();
    ModelHelper mh = (ModelHelper)hs.getAttribute("mh");
    UserData loginAccount = (UserData)hs.getAttribute("loginAccount");
    String userName = mh.existAccountName(loginAccount);
    LogWriter.logFileWriter(userName, request.getParameter("where"));
%>

