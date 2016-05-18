/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author SHO
 */
public class ItemDataDTO implements Serializable{
    
    private Integer userID;
    private Integer type;
    private Integer total;
    
    public ItemDataDTO(){
        this.userID = 0;
        this.type = 0;
        this.total = 0;
    }
    
    public static ItemDataBeans getInstance(){
        return new ItemDataBeans();
    }
    
    public void setUserID(Integer userID){
        this.userID = userID;
    }
    public Integer getUserID(){
        return this.userID;
    }
    
    public void setType(int type){
        this.type = type;
    }
    public Integer getType(){
        return this.type;
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    public Integer getTotal(){
        return this.total;
    }
    
}
