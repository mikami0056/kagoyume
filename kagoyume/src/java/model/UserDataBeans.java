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
public class UserDataBeans implements Serializable{
    private Integer userID;
    private String name;
    private String passWord;
    private String mail;
    private String address;
    private Integer sum;
    
    public UserDataBeans(){
        this.userID = 0;
        this.name = "";
        this.passWord = "";
        this.mail = "";
        this.address= "";
        this.sum = 0;
    }
    
    public UserDataBeans(String name, String passWord, String mail, String address){
        this.userID = 0;
        this.name = name;
        this.passWord = passWord;
        this.mail = mail;
        this.address= address;
        this.sum = 0;
    }
    
    public int getUserID(){
        return this.userID;
    }
    public void setUserID(int userID){
        this.userID = userID;
    }
    
    public String getName(){
        return this.name;
    }
    public void setName(String userName){
        this.name = userName;
    }
    
    public String getPassWord(){
        return this.passWord;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
    
    public String getMail(){
        return this.mail;
    }
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    
    public Integer getSum(){
        return this.sum;
    }
    public void setSum(int sum){
        this.sum += sum;
    }
    
    public void UDB2DTOMapping(UserDataDTO dto){
        dto.setUserID(this.userID);
        dto.setName(this.name);
        dto.setPassWord(this.passWord);
        dto.setMail(this.mail);
        dto.setAddress(this.address);
        dto.setSum(this.sum);
    }
    
    public void DTO2UDBMapping(UserDataDTO dto){
        this.userID = dto.getUserID();
        this.name = dto.getName();
        this.passWord = dto.getPassWord();
        this.mail = dto.getMail();
        this.address = dto.getAddress();
        this.sum = dto.getSum();
    }
    
    public void updateInformations(String name, String passWord, String mail, String address){
        this.name = name;
        this.passWord = passWord;
        this.mail = mail;
        this.address = address;
    }
    
}
