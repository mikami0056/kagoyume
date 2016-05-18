/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author SHO
 */
public class UserDataDTO {
    private int userID;
    private String name;
    private String passWord;
    private String mail;
    private String address;
    private Integer sum;
    
    public static UserDataDTO getInstance(){
        return new UserDataDTO();
    }
    
    public UserDataDTO(){
        this.userID = 0;
        this.name = "";
        this.passWord = "";
        this.mail = "";
        this.address= "";
        this.sum = 0;
    }
    public UserDataDTO(int userID, String userName, String passWord, String mail, String address, int total){
        this.userID = userID;
        this.name = userName;
        this.passWord = passWord;
        this.mail = mail;
        this.address = address;
        this.sum = total;
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
        this.sum = sum;
    }
    
}
