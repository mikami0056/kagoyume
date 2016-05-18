/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemDataBeans;
import model.UserData;

/**
 *
 * @author SHO
 */
public class Delete extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
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
        processRequest(request, response);
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
        System.out.println("[Notice]Delete.java start");
        //基本事項
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        
        //ログイン状態を確認
        //ログイン確認用変数
        boolean loginStatus = false;
        UserData loginAccount = null;
        if(session.getAttribute("loginAccount") != null){
        loginAccount = (UserData)session.getAttribute("loginAccount");
        loginStatus = true;
        }
        //セッションスコープから商品カートを取得
        Map<String, Set> Cart = (LinkedHashMap<String, Set>)session.getAttribute("Cart");

        //cart.jspから取得した削除する商品IDを取得
        String productID = request.getParameter("productID");
        //商品リストから商品IDと一致する商品を削除する
        Set<ItemDataBeans> items = null;
        if(loginStatus){
            items = Cart.get(loginAccount.getName());
        } else {
            items = Cart.get("defaultID");
        }
        
        for(ItemDataBeans item : items){
            if(item.getProductID().equals(productID)){
                item.setNumber(0);
                items.remove(item);
                break;
            }
        }
       
        System.out.println("削除処理終了");
        session.setAttribute("delete", "delete");
        response.sendRedirect("/kagoyume/Cart");
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
