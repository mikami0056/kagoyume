/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ModelHelper;
import model.RegistrationLogic;
import model.UserData;
import model.UserDataDAO;

/**
 *
 * @author SHO
 */
public class Registration extends HttpServlet {

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
        HttpSession session = request.getSession();
        //登録用のIDを生成(一時的な乱数)
        double a = Math.random()*1000;
        double b = Math.random()*1000;
        
        session.setAttribute("idForRegist", ((int)a * (int)b));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
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
        
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        //遷移先
        String destination = "";
        
        try{
            
            //アクセス確認
            String chkId = request.getParameter("id");
            if(chkId == null || (Integer)session.getAttribute("idForRegist") != Integer.parseInt(chkId)){
                System.out.println("[WARNING At RegisterConfirm.java]:unauthorized access!! Please return to index.jsp.");
                throw new Exception("不正なアクセスです");
            }
            
            String operation = request.getParameter("operation");
            
            //registrationconfirm.jspからの画面遷移分岐
            if(operation != null){
            switch(operation){
                
                //登録情報変更の場合
                case "REINPUT":
                destination = "/WEB-INF/jsp/registration.jsp";
                return; 
                
                //登録する場合
                case "REGIST":
                UserData udb = (UserData)session.getAttribute("udb");
                
                //同じユーザー名とパスワードがあれば登録不可
                if(!RegistrationLogic.getInstance().checkUserDataInDB(udb)){
                    String warning = "登録できません. 別のユーザ名又はパスワードを入力してください";
                    System.out.println("WARNING!!!");
                    request.setAttribute("warning", warning);
                    destination = "/WEB-INF/jsp/registration.jsp";
                    return;
                }
                
                RegistrationLogic.getInstance().insertUserData2DB(udb);
                request.setAttribute("udb", udb);
                
                //セッション内に保存されていた一時インスタンスを削除
                session.removeAttribute("udb");
                destination = "/WEB-INF/jsp/registrationcomplete.jsp";
                return;
                
                default:
                break;
            }
            }
            
            //登録確認画面
            //registration.jspからパラメータを取得
            String userName = request.getParameter("userName").trim();
            String passWord = request.getParameter("password");
            String mail = request.getParameter("mail");
            String address = request.getParameter("address");
            
            //登録時の一時的なインスタンス
            UserData udb = new UserData(userName, passWord, mail, address);
            session.setAttribute("udb", udb);
            //フォーム入力確認
            List<String> caution = RegistrationLogic.getInstance().caution(userName, passWord, mail, address);
            //未入力事項がある場合, 入力フォームに戻る
            if(!caution.isEmpty()){
                request.setAttribute("caution", caution);
                destination = "/WEB-INF/jsp/registration.jsp";
                return;
            }
            
            System.out.println("[Notice]:USER's information had been iserted to UDB completely. At RegisterConfirm.java");
            System.out.println("[Notice]:UDB which has information is inputed in the session At RegisterConfirm.java");
            
            destination = "/WEB-INF/jsp/registrationconfirm.jsp";
            
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            destination = "/WEB-INF/jsp/error.jsp";
            
        } finally{
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
