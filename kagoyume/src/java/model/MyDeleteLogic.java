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
public class MyDeleteLogic {
    //コンストラクタ
    public MyDeleteLogic(){}
    
    public static MyDeleteLogic getInstance(){
        return new MyDeleteLogic();
    }
    
    public void deleteUserData (UserData loginAccount){
        UserDataDTO dtoD = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dtoD);
        try {
            UserDataDAO.getInstance().deleteUserData(dtoD);
        } catch (SQLException ex) {
            Logger.getLogger(MyData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
