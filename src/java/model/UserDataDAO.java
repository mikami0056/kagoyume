/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbcontroller.DBManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author SHO
 */
public class UserDataDAO {
    
    public UserDataDAO(){}
    
    public static UserDataDAO getInstance(){
        return new UserDataDAO();
    }
    /*
    @登録可否をチェックするためのメソッド
    @新規登録時に使用
    @ユーザ入力フォ−ムより受け取ったユーザIDとパスワードが同一のものがいないか確認する
    */
    public boolean selectUserData(UserDataDTO dto)throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String checkSql = "SELECT * FROM user_t WHERE name = ? and password = ?";
        System.out.println("[Notice in UserDataDAO.java]\"checkUserData\" has been started[at checkUserData]");
        boolean exist = false;
        
        try{
            con = DBManager.getConnection();
            pst =  con.prepareStatement(checkSql);
            pst.setString(1, dto.getName());
            pst.setString(2, dto.getPassWord());
            ResultSet rs = pst.executeQuery();
            //同じユーザ名とパスワードが登録されていれば失敗falseを返す
            exist = !rs.next();
            
            if(!rs.next()){
                exist = !rs.next();
                System.out.println("[Notice]name and password has been existing. Please fill out the form again.");
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
        return exist;
    }
    
    /*
    @ユーザ名とパスワードを使ってユーザ情報を取得するためのメソッド
    @ログイン時に使用
    */
    public UserDataDTO selectNameAndPass(UserDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        UserDataDTO accountDTO = null;
        String sql = "SELECT userID, name, password, mail, address, total FROM user_t WHERE name = ? AND password = ?";
        System.out.println("[Notice]searchNameAndPass start");
        
        try{
            
            con = DBManager.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, dto.getName());
            pst.setString(2, dto.getPassWord());
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                int userID = rs.getInt("userID");
                String userName = rs.getString("name");
                String passWord = rs.getString("password");
                String mail = rs.getString("mail");
                String address = rs.getString("address");
                int total = rs.getInt("total");
                accountDTO = new UserDataDTO(userID, userName, passWord, mail, address, total);
            }
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        System.out.println("[Notice]searchNameAndPass finished");
        return accountDTO;
    }
    
    /*
    @ユーザを新しく登録するためのメソッド
    @新規登録時に使用
    */
    public void insertUserData(UserDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String insertSql = "INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)";
        System.out.println("inserUserData start");
        
        try{
            con = DBManager.getConnection();
            pst =  con.prepareStatement(insertSql);
            pst.setString(1, dto.getName());
            pst.setString(2, dto.getPassWord());
            pst.setString(3, dto.getMail());
            pst.setString(4, dto.getAddress());
            pst.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            System.out.println("inserInformation start completed");
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /*
    @商品購入情報を購入テーブル(buy_t)に追加するためのメソッド
    @商品購入時に使用
    */
    public void insertTotal2Buy(ItemDataDTO idd) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "INSERT INTO buy_t(userID, total, type, buyDate) VALUES(?,?,?,?)";
        System.out.println("[Notice]insertTotal2Buy start");
        
        try{
            
            con = DBManager.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, idd.getUserID());
            pst.setInt(2, idd.getTotal());
            pst.setInt(3, idd.getType());
            pst.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        System.out.println("[Notice]insertTotal2Buy finished");
    }
    
    /*
    @購入金額合計をユーザテーブル(user_t)に追加するためのメソッド
    @商品購入時に使用
    */
    public void updateTotal2User(UserDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "UPDATE user_t SET total = ? WHERE userID = ?;";
        System.out.println("[Notice]updateTotal2User start");
        
        try{
            
            con = DBManager.getConnection();
            pst = con.prepareStatement(sql);
            pst.setInt(1, dto.getTotal());
            pst.setInt(2, dto.getUserID());
            pst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        System.out.println("[Notice]updateTotal2User finished");
    }
    
    /*
    @ユーザ情報を更新するためのメソッド
    */
    public void updateUserData(UserDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String sql = "UPDATE user_t SET name = ?, password = ?, mail = ?, address = ? WHERE userID = ?;";
        System.out.println("[Notice]updateUserInformations start");
        
        try{
            
            con = DBManager.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, dto.getName());
            pst.setString(2, dto.getPassWord());
            pst.setString(3, dto.getMail());
            pst.setString(4, dto.getAddress());
            pst.setInt(5, dto.getUserID());
            pst.executeUpdate();
            
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
            
        }finally{
            if(con != null){
                con.close();
            }
        }
        System.out.println("[Notice]updateUserInformations finished");
    }
    
    /*
    @ユーザ情報を削除するためのメソッド
    @外部キーと主キーの関係上, 購入テーブル(buy_t), ユーザテーブル(user_t)の順でデータを削除している
    */
    public void deleteUserData(UserDataDTO dto) throws SQLException{
        
        Connection con = null;
        PreparedStatement pst = null;
        String sql4Buy = "DELETE FROM buy_t WHERE userID = ?";
        System.out.println("[Notice]deleteUserData start");
        
        try{
            con = DBManager.getConnection();
            pst = con.prepareStatement(sql4Buy);
            pst.setInt(1, dto.getUserID());
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        
        String sql4User = "DELETE FROM user_t WHERE userID = ?";
        
        try{
            pst = con.prepareStatement(sql4User);
            pst.setInt(1, dto.getUserID());
            pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new SQLException(e);
        }
        finally{
            if(con != null){
                con.close();
            }
        }
        System.out.println("[Notice]deleteUserData finished");
    }
}
