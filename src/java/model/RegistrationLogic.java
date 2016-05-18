/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SHO
 */
public class RegistrationLogic {
    public RegistrationLogic(){}
    
    public static RegistrationLogic getInstance(){
        return new RegistrationLogic();
    }
    //trueの場合登録可能, faliseの場合登録不可
    public boolean checkUserDataInDB (UserData udb) throws SQLException{
        UserDataDTO dto = new UserDataDTO();
        udb.UDB2DTOMapping(dto);
        return UserDataDAO.getInstance().selectUserData(dto);
    }
    
    public void insertUserData2DB (UserData udb) throws SQLException{
        UserDataDTO dto = new UserDataDTO();
        udb.UDB2DTOMapping(dto);
        UserDataDAO.getInstance().insertUserData(dto);
    }
    
    public List<String> caution(String userName, String passWord, String mail, String address){
        List<String> caution = new ArrayList<>();
        if("".equals(userName)){
            caution.add("userName");
        }
        if("".equals(passWord)){
            caution.add("passWord");
        }
        if("".equals(mail)){
            caution.add("mail");
        }
        if("".equals(address)){
            caution.add("address");
        }
        return caution;
    }
    
}
