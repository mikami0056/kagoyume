/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.BuyLogic;
import model.ItemDataBeans;
import model.ItemDataDTO;
import model.UserData;
import model.UserDataDAO;
import model.UserDataDTO;

/**
 *
 * @author SHO
 */
public class Buy extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        System.out.println("[Notice]Buy.java start");
        //セッションスコープを取得
        HttpSession session = request.getSession();
        //遷移先用変数
        String destination= "";
        //ログイン状態判別用
        boolean loginStatus = false;
        
        try{
            UserData loginAccount = null;
            //ログイン状態を確認
            if(session.getAttribute("loginAccount") != null){
                loginAccount = (UserData)session.getAttribute("loginAccount");
                loginStatus = true;
            } else {
                //ログインしていない状態ならばログイン画面へ移動
                System.out.println("[Notice in Buy.java] Be forwarded to Login.java because of Not logged in.");
                response.sendRedirect("/kagoyume/Login");
                return;
            }
            
            System.out.println("[Notice in Buy.java]logged in");

            //商品カートを取得
            Map<String, Set> Cart = (LinkedHashMap<String, Set>)session.getAttribute("Cart");
            //商品カート内の商品リストを取得
            Set<ItemDataBeans> items = Cart.get(loginAccount.getName());
        
            //購入確認画面へ遷移する場合
            if(request.getParameter("confirm") != null){
                System.out.println("[Notice in Buy.java] Forward to buyconfitm.jsp to confirm to buy the item.");
                //cart.jspから購入する商品IDを取得
                String productID = request.getParameter("productID");
                //商品リストから購入する商品を探し, それをリクエストスコープに保存
                for(ItemDataBeans item : items){
                    if(productID.equals(item.getProductID())){
                    request.setAttribute("buyItem", item);
                    break;
                    }
                }
                //購入確認画面へ遷移
                destination = "/WEB-INF/jsp/buyconfirm.jsp";
                return;
            }
        
            //以下, 購入確定用ロジック
            System.out.println("[Notice in Buy.java] logic to buy the item has Started ");
            
            //セッションスコープから商品IDと紐付いている商品インスタンスを取得
            String productID = request.getParameter("productID");
            //商品リストから購入する商品を削除し, 購入金額(購入数 * 価格)をユーザーアカウントに保存し, 商品を返す
            //引数の順番(ログインアカウント, 商品リスト, 商品ID)
            BuyLogic buyLogic = new BuyLogic();
            ItemDataBeans item = buyLogic.removeItemFromCart(loginAccount, items, productID);
            
            System.out.println("[Notice in Buy.java]DBConnection Start");
            //購入情報をDBに保存
            buyLogic.updateTotal2User(loginAccount);
            //商品にユーザIDと発送方法を保存し, DBに保存
            item.setUserID(loginAccount.getUserID());
            item.setType(Integer.parseInt(request.getParameter("type")));
            buyLogic.insertTotal2Buy(item);
            //ユーザIDや個数などの情報の初期化
            item.propatyInit();
            
            /*
            Integer a = 0;
            for(ItemDataBeans item : items){
            if(item.getProductID().equals(productID)){
                a = (item.getNumber() * item.getPrice());
                loginAccount.setSum(a);
                items.remove(item);
                break;
            }
            }
            */
            
            
        /*    
        UserDataDTO dto = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dto);
        ItemDataDTO idd = new ItemDataDTO();
        item.IDB2IDDMapping(idd);
        /*
        idd.setUserID(loginAccount.getUserID());
        idd.setType(Integer.parseInt(request.getParameter("type")));
        idd.setTotal(subTotal);
        */
        /*
        try {
            UserDataDAO.getInstance().updateTotal2User(dto);
            UserDataDAO.getInstance().insertTotalMoneyByParchase(idd);
        } catch (SQLException ex) {
            Logger.getLogger(Buy.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
            destination = "/WEB-INF/jsp/buycomplete.jsp";
            
        }catch(Exception e){
            
        }finally{
            if(loginStatus){
            System.out.println("[Notice in Buy.java]Forward");
            RequestDispatcher dispatcher = request.getRequestDispatcher(destination);
            dispatcher.forward(request, response);
            }
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
