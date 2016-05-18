/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemDataBeans;
import model.LoginLogic;
import model.UserData;

/**
 *
 * @author SHO
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("[Notice]Login.java by doGet start"); 
        HttpSession session = request.getSession();
        //
        String destination = "";
        /*
        @セッションスコープからログイン情報を保有しているインスタンスを取得
        @ある場合はloginsuccess.jsp, ない場合はlogin.jspに飛ばす
        */
        if((UserData)session.getAttribute("loginAccount") != null){
            
            request.setAttribute("done", "done");
            destination = "/WEB-INF/jsp/loginsuccess.jsp";
            
        } else {
            
            destination = "/WEB-INF/jsp/login.jsp";
            
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("[Notice]Login.java by doPost start");
        //login.jspからユーザー名, パスワードが送られる
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        //遷移先用変数
        String destination = "";
        //ログアウトした場合        
        
        try{
            //ログアウト処理
            if(request.getParameter("logout") != null){
                Enumeration E = session.getAttributeNames();
                session.invalidate();
                System.out.println("session has been deleted");
                request.setAttribute("logout", "logout");
                destination = "/WEB-INF/jsp/login.jsp";
                return;
            }
            
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("passWord");
            
            //LoginLogicのインスタンスを取得後, ユーザー名とパスワードを使用してログインを実行
            //loginExecuteでは, UserData, UserDataDTO, UserDataDAOを使用してデータベースに接続している
            UserData loginAccount = LoginLogic.getInstance().loginExecute(userName, passWord);
            
            if(loginAccount != null){
                System.out.println("[Status in Login.java]loginAccount exists");
                session.setAttribute("loginAccount", loginAccount);
                
                Map<String, Set> Cart = (LinkedHashMap<String, Set>)session.getAttribute("Cart");
                //ログイン状態でカートにdefaultIDが設定されていた場合
                if(Cart != null && Cart.containsKey("defaultID")){
                    Set<ItemDataBeans> items = Cart.get("defaultID");
                    Cart.put(loginAccount.getName(), items);
                    Cart.remove("default");
                }
                //遷移先決定
                destination = "/WEB-INF/jsp/loginsuccess.jsp";
                
            } else {
                System.out.println("[Status]loginAccount is NULL");
                destination = "/WEB-INF/jsp/login.jsp?flag=notExist";
                
            }
            
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
            
        } finally {
            RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
            dispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
