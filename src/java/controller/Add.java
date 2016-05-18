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
public class Add extends HttpServlet {

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
        //processRequest(request, response);
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
        //item.jspからhiddenで送られた商品コードを取得
        String productID = request.getParameter("productID");
        
        //商品コードを元にセッションスコープから商品を取得
        ItemDataBeans item = (ItemDataBeans)session.getAttribute(productID);
        
        //セッションスコープからカート情報を取得
        Map<String, Set> Cart = (LinkedHashMap<String, Set>)session.getAttribute("Cart");
        
        //商品保存用配列, この中に追加した商品が追加される
        Set<ItemDataBeans> items = null;
        
        //ログイン状態を確認
        //ログイン確認用変数
        boolean loginStatus = false;
        UserData loginAccount = null;
        if(session.getAttribute("loginAccount") != null){
        loginAccount = (UserData)session.getAttribute("loginAccount");
        loginStatus = true;
        }
        
        //カートに関する処理
        if(Cart == null){
            System.out.println("カートと商品用リストがないので作成します");
            //カート用Map. これにはユーザーIDと商品保存用コレクションが保存される.
            Cart = new LinkedHashMap<>();
            //商品保存用コレクション
            items = new LinkedHashSet<>();
      
        }else {
            System.out.println("カートがありました");
            if(loginStatus){
                items = Cart.get(loginAccount.getName());
            } else {
                items = Cart.get("defaultID");
            }
        }
        
        //購入個数を商品情報に代入
        Integer number = Integer.parseInt(request.getParameter("buyNumber"));
        item.setNumber(number);
        //商品を保存
        items.add(item);
        
        //カートを保存
        if(loginStatus){
            //ログインしている場合, ユーザー名と商品カートを紐付ける
            Cart.put(loginAccount.getName(), items);
        } else {
            //ログインされていない場合, デフォルトIDと商品カートを紐付ける
            Cart.put("defaultID", items);
        }
        //カート情報をセッションに保存
        session.setAttribute("Cart", Cart);
        
        //add.jsp表示用の一時的なインスタンス(購入数, 名前, 商品ID)
        request.setAttribute("buyNumber", number);
        request.setAttribute("name", item.getName());
        request.setAttribute("productID", item.getProductID());
        session.setAttribute(item.getProductID(), item);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/add.jsp");
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
