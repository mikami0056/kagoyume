/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemDataBeans;

/**
 *
 * @author SHO
 */
public class Item extends HttpServlet {

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
        //遷移先用変数
        String destination = "";
        
        try{
            System.out.println(1);
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            //セッションから商品一覧を取得
            Map<String, ItemDataBeans> itemSearchList = (Map<String, ItemDataBeans>)session.getAttribute("itemSearchList");
            System.out.println(2);
            //クエリストリングからindexを取得し, 商品一覧から特定の商品を取得
            String index = request.getParameter("index");
            System.out.println(3);
            ItemDataBeans item = itemSearchList.get(index);
            System.out.println(4);
            //item.jspに商品IDを渡す
            request.setAttribute("productID",item.getProductID());
            System.out.println(5);
            //商品IDと商品を紐付けてセッションスコープに保存(ない場合)
            if((ItemDataBeans)session.getAttribute(item.getProductID()) == null){
                System.out.println(6);
                System.out.println("商品をセッションに追加:[商品名]["+item.getName() + "]");
                session.setAttribute(item.getProductID(), item);
                System.out.println(7);
            } else {
                System.out.println("商品名["+item.getName() + "]はもうセッション内にあります");
            }
            
            //ログイン後遷移用URLをセッション内に保存
            StringBuffer url = request.getRequestURL().append("?index=" + index);
            //item.jspの[一覧に戻る]ボタン用
            request.setAttribute("URL", (StringBuffer)session.getAttribute("URL"));
            session.setAttribute("URL", url);
            
            destination = "/WEB-INF/jsp/item.jsp";
            
        } catch (Exception e){
            request.setAttribute("error", e.getMessage());
            destination = "/error.jsp";
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
        processRequest(request, response);
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
