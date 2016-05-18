/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import org.w3c.dom.Element;

/**
 *
 * @author SHO
 */
public class ItemDataBeans implements Serializable{
    
    private String index;
    private String productID;
    private String name;
    private Integer price;
    private String imgUrl;
    private String stock;//在庫有無
    private Integer number;//購入数
    //DTOと連携させるための変数
    private int userID;
    private int type;
    private int total;
    
    public ItemDataBeans(){
        this.number = 0;
        this.type = 0;
    }
    
    public static ItemDataBeans getInstance(){
        return new ItemDataBeans();
    }
    
    public void setIndex(String index){
        this.index = index;
    }
    public String getIndex(){
        return this.index;
    }
    
    public void setProductID(String productID){
        this.productID = productID;
    }
    public String getProductID(){
        return this.productID;
    }
    
    public void setNumber(Integer n){
        if(n == 0){
            this.number = 0;
        }else{
            this.number += n;
        }        
    }
    public Integer getNumber(){
        return this.number;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    
    public void setPrice(Integer price){
        this.price = price;
    }
    public Integer getPrice(){
        return this.price;
    }
    
    public void setImgUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }
    public String getImgUrl(){
        return this.imgUrl;
    }
    
    public void setStock(String stock){
        this.stock = stock;
    }
    public String getStock(){
        return this.stock;
    }

    public void setPropaty(String elName, Element el){
        switch(elName){
                
            case "ProductID":
            this.productID = el.getTextContent();
            break;
                
            case "Name":
            this.name = el.getTextContent();
            break;
                
            case "Price":
            this.price = Integer.parseInt(el.getTextContent());
            break;
                
            case "Medium":
            this.imgUrl = el.getTextContent();
            break;
                
            case "Stock":
            this.stock = el.getTextContent();
            break;
        }
    }
    
    //以下DTO連携用
    public void setUserID(int userID){
        this.userID = userID;
    }
    public int getUserID(){
        return this.userID;
    }
    
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    
    public void setTotal(int total){
        this.total = total;
    }
    public int getTotal(){
        return this.total;
    }
    
    public void IDB2IDDMapping(ItemDataDTO idd){
        idd.setUserID(this.userID);
        idd.setType(this.type);
        idd.setTotal(this.total);
    }
    
    public void propatyInit(){
        this.userID = 0;
        this.type = 0;
        this.total = 0;
    }
}
