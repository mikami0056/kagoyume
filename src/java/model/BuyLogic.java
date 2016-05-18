/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author SHO
 */
public class BuyLogic {
    
    //コンストラクタ
    public BuyLogic(){}
    
    //インスタンス取得メソッド
    public static BuyLogic getInstance(){
        return new BuyLogic();
    }
    /*
    public void searchAndAdd(Set<ItemDataBeans> items, Set<ItemDataBeans> itemsBoughtByUser, UserData loginAccount, String productID){
        for(ItemDataBeans item : items){
            if(item.getProductID().equals(productID)){
                itemsBoughtByUser.add(item);
                loginAccount.setSum(item.getNumber() * item.getPrice());
                items.remove(item);
                break;
            }
        }
    }
    */
    public ItemDataBeans removeItemFromCart(UserData loginAccount, Set<ItemDataBeans> items, String productID){
        int total = 0;
        ItemDataBeans it = null;
        for(ItemDataBeans item : items){
            if(item.getProductID().equals(productID)){
                total = (item.getNumber() * item.getPrice());
                loginAccount.setSum(total);
                it = item;
                it.setTotal(total);
                items.remove(item);
                break;
            }
            }
        return it;
    }
    
    public void updateTotal2User(UserData loginAccount) throws SQLException{
        UserDataDTO dto = new UserDataDTO();
        loginAccount.UDB2DTOMapping(dto);
        UserDataDAO.getInstance().updateTotal2User(dto); 
    }
    
    public void insertTotal2Buy(ItemDataBeans item) throws SQLException{
        ItemDataDTO idd = new ItemDataDTO();
        item.IDB2IDDMapping(idd);
        UserDataDAO.getInstance().insertTotal2Buy(idd);
    }
}
