/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.MyData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHO
 */
public class MyUpdateLogic {
    //コンストラクタ
    public MyUpdateLogic(){}
    //
    public static MyUpdateLogic getInstance(){
        return new MyUpdateLogic();
    }
    
    //既存のアカウントに新しい情報を挿入, その後DBにアップデート処理をするメソッド/
    public UserData updateUserData(UserData loginAccount, String userName, String passWord, String mail, String address){
        //ユーザーID以外の情報を更新.
        loginAccount.updateInformations(userName, passWord, mail, address);
        //DTOにマッピング後, DBにアップデート処理をする.
        UserDataDTO dtoU = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dtoU);
        try {
            UserDataDAO.getInstance().updateUserData(dtoU);
        } catch (SQLException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loginAccount;
    }
    
}
