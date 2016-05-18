/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.MyDeleteLogic;
import model.MyUpdateLogic;
import model.RegistrationLogic;
import model.UserData;
import model.UserDataDAO;
import model.UserDataDTO;

/**
 *
 * @author SHO
 */
public class MyData extends HttpServlet {

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
        
        System.out.println("[Notice]MyData.java by doGet start");
        String destination = "";
        String operation = request.getParameter("operation");
        if(operation == null){
            operation = "";
        }
        switch(operation){
            case "update":
            destination = "/WEB-INF/jsp/myupdate.jsp";
            break;
            
            case "delete":
            destination = "/WEB-INF/jsp/mydelete.jsp";    
            break;
            
            default:
            destination = "/WEB-INF/jsp/mydata.jsp";
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
        
        System.out.println("[Notice]MyData.java by doPost start");
        HttpSession session = request.getSession();
        
        String destination = "";
        String operation = request.getParameter("operation");
        UserData loginAccount = (UserData)session.getAttribute("loginAccount");
        switch(operation){
            
            case "update":
            //mydata.jspからフォームを取得
            String userName = request.getParameter("userName");
            String passWord = request.getParameter("password");
            String mail = request.getParameter("mail");
            String address = request.getParameter("address");
            //フォーム入力確認, 登録処理時のロジックを流用
            List<String> caution = RegistrationLogic.getInstance().caution(userName, passWord, mail, address);
            if(!caution.isEmpty()){
                request.setAttribute("caution", caution);
                destination = "/WEB-INF/jsp/myupdate.jsp";
                break;
            }
            //アップデート処理を実行
            loginAccount = MyUpdateLogic.getInstance().updateUserData(loginAccount, userName, passWord, mail, address);
            session.setAttribute("loginAccount", loginAccount);
            destination = "/WEB-INF/jsp/myupdateresult.jsp";
            break;
            
            case "delete":
            System.out.println("DELETE");
            MyDeleteLogic.getInstance().deleteUserData(loginAccount);
            session.removeAttribute("loginAccount");
            session.invalidate();
            destination = "/WEB-INF/jsp/mydeleteresult.jsp";    
            break;
            
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
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
