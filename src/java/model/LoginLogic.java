/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;

/**
 *
 * @author SHO
 */
public class LoginLogic {
    
    public LoginLogic(){}
    
    public static LoginLogic getInstance(){
        return new LoginLogic();
    }
    /*
    @DBからユーザー情報を取得してログインの可否をけ
    */
    public UserData loginExecute(String userName, String passWord) throws SQLException{
        System.out.println("[Notice in LoginLogin]loginExecute start");
        
        //検索用インスタンス
        UserData loginAccount = new UserData();
        loginAccount.setName(userName);
        loginAccount.setPassWord(passWord);
        
        UserDataDTO dto = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dto);
        UserDataDTO accountDTO = UserDataDAO.getInstance().selectNameAndPass(dto);
            
        if(accountDTO != null){        
            loginAccount.DTO2UDBMapping(accountDTO);
            System.out.println("[Notice in LoginLogin]loginExecute finished ");
            System.out.println("[Status in LoginLogin]accountDTO is EXIST");
            
        } else {
            loginAccount = null;
            System.out.println("[Notice in LoginLogin]loginExecute finished ");
            System.out.println("[Status in LoginLogin]accountDTO is NULL");
            
        }
        return loginAccount;
    }
    
}
