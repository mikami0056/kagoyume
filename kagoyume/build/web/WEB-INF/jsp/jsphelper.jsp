<%-- 
    Document   : jsphelper
    Created on : 2016/05/16, 17:20:42
    Author     : SHO
--%>

<%@page import="model.UserData"%>
<%@page import="model.Common"%>
<%@page import="model.ModelHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- セッションの取得や, ModelHelperの取得を行うJSPファイル --%>
<%
    HttpSession hs = request.getSession();
    ModelHelper mh = (ModelHelper)hs.getAttribute("mh");
    if(mh == null){
        mh = new ModelHelper();
        hs.setAttribute("mh", mh);
    }
    Common con = (Common)hs.getAttribute("con");
    if(con == null){
        con = new Common();
        hs.setAttribute("con", con);
    }
%>

