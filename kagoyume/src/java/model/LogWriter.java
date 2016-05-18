/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SHO
 */
public class LogWriter {
    public LogWriter(){}
    
    public static LogWriter getInstance(){
        return new LogWriter();
    }
    
    public static void logFileWriter(String userName, String pageName) throws IOException{
        //メソッド開始
        System.out.println("[Notice in LogWriter]\"logFileCreate\" has been started");
        
        String logPathName = "/Users/SHO/NetBeansProjects/kagoyume/web/WEB-INF/log/log.txt";
        File newFile = new File(logPathName);
        //現在時刻を取得し, 見やすい形に変形
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
        BufferedWriter bw = null;
        
        try{
            
            if(newFile.createNewFile()){
            //ログファイルがない場合
            System.out.println("[Notice in LogWriter] \"log.txt\" has been created");
            }else{
            //ログファイルがある場合
            System.out.println("[Notice in LogWriter] \"log.txt\" is existing");  
            }
            //最新のログは先頭に記述
            bw = new BufferedWriter(new FileWriter(logPathName,  true));
            
            bw.write("Access Time["+sdf.format(now) + "] / UserName[" + userName + "] / PageName[" + pageName + "]");
            bw.newLine();
            bw.flush();
        }catch(IOException e){
            System.out.println(e);
        }finally{
            bw.close();    
        }
    }
}
