/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
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
public class Cart extends HttpServlet {

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
        
        if(request.getParameter("delete") != null){
            System.out.println("deleteから来ました");
            session.removeAttribute("delete");
        }
        
        StringBuffer url = request.getRequestURL();
        session.setAttribute("URL", url);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
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
        
        System.out.println("[Notice]Cart.java start");
        HttpSession session = request.getSession();
        
        //add.jspから追加した商品IDを取得
        String productID = request.getParameter("productID");
        //セッションスコープから商品IDと紐付いている商品インスタンスを取得
        ItemDataBeans item = (ItemDataBeans)session.getAttribute(productID);
        //ログイン状態を確認
        //ログイン確認用変数
        boolean loginStatus = false;
        UserData loginAccount = null;
        if(session.getAttribute("loginAccount") != null){
        loginAccount = (UserData)session.getAttribute("loginAccount");
        loginStatus = true;
        }
        //セッションスコープからカート情報を取得
        Map<String, Set> Cart = (LinkedHashMap<String, Set>)session.getAttribute("Cart");
        //商品保存用配列, この中に購入した商品が追加される
        Set<ItemDataBeans> items = null;
        
        if(Cart == null){
            System.out.println("カートと商品用リストがないので作成します");
            //カート用Map. これにはユーザーIDと商品保存用コレクションが保存される.
            Cart = new LinkedHashMap<>();
            //商品保存用コレクション
            items = new LinkedHashSet<>();
      
        }else {
            System.out.println("カートがありました");
            if(loginStatus){
                System.out.println("ログインしている");
                items = Cart.get(loginAccount.getName());
            } else {
                System.out.println("ログインしていない");
                items = Cart.get("defaultID");
            }
        }
        //商品を保存
        items.add(item);
        
        if(loginStatus){
            //ログインしている場合, ユーザー名と商品カートを紐付ける
            Cart.put(loginAccount.getName(), items);
        } else {
            //ログインされていない場合, デフォルトIDと商品カートを紐付ける
            Cart.put("defaultID", items);
        }
        //カート情報をセッションに保存
        session.setAttribute("Cart", Cart);
        session.setAttribute("URL", request.getRequestURL());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
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
