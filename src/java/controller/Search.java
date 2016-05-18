/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Common;
import model.ItemDataBeans;
import model.SearchLogic;

/**
 *
 * @author SHO
 */
public class Search extends HttpServlet {

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
            //遷移先保存用変数
            String destination = "";
            //finally条件用変数 検索フォーム未入力時にリダイレクトを行わせるため.
            boolean flag = true;
            
        try {
            
            //基本事項
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            //yahooへ接続するため検索用モデルをインスタンス化
            SearchLogic searchLogic = new SearchLogic();
            
            //各パラメータ取得
            String query = request.getParameter("query");
            String sort = request.getParameter("sort");
            String category = request.getParameter("category");
            
            //search.jsp画面表示用に値を取得
            Common con = (Common)session.getAttribute("con");
            String sortValue = searchLogic.getValueFromSort(con, sort);
            String categoryValue = searchLogic.getValueFromCategory(con, category);
            
            //search.jsp表示用にセッションスコープに保存
            session.setAttribute("query", query);
            session.setAttribute("sort", sortValue);
            session.setAttribute("category", categoryValue);
            
            //入力チェック, 未入力ならindexに遷移
            if("".equals(query.trim())){
                System.out.println("キーワードが未入力でした");
                flag = false;
                response.sendRedirect("/kagoyume/index.jsp?flag=error");
                return;
            }
            
            //商品が格納されたMapを取得
            Map<String, ItemDataBeans> itemSearchList = searchLogic.execute(query, category, sort);
            session.setAttribute("itemSearchList", itemSearchList);
            //ログイン後に遷移するための変数をセッションスコープに保存
            StringBuffer url = request.getRequestURL().append("?query="+query)
                                                      .append("&category="+category)
                                                      .append("&sort="+sort);
                                                      
            session.setAttribute("URL", url);
            
            destination = "/WEB-INF/jsp/search.jsp";
            
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
            //検索フォームに入力事項があれば実行
            if(flag){
                RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
                dispatcher.forward(request, response);
            }
        }
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
        
        String destination = "/WEB-INF/jsp/search.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
        dispatcher.forward(request, response);
        //processRequest(request, response);
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
