/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author SHO
 */
public class Common {
    private Map<String, String> categories = new LinkedHashMap<>();
    private Map<String, String> sortOrder = new LinkedHashMap<>();
    
    public static model.Common getInstance(){
        return new model.Common();
    }
 
    public Common(){
        
        this.categories.put("1", "全てのカテゴリから");
        this.categories.put("2497", "ベビー・キッズ・マタニティ");
        this.categories.put("2498", "食品");
        this.categories.put("2500", "ダイエット・健康");
        this.categories.put("2501", "コスメ・香水");
        this.categories.put("2502", "パソコン・パソコン周辺機器");
        this.categories.put("2503", "DIY・工具・文具");
        this.categories.put("2504", "AV機器・カメラ");
        this.categories.put("2505", "家電");
        this.categories.put("2506", "家具・インテリア");
        this.categories.put("2507", "花・ガーデニング");
        this.categories.put("2508", "キッチン・生活雑貨・日用品");
        this.categories.put("2509", "ペット用品・いきもの");
        this.categories.put("2510", "楽器・趣味・学習");
        this.categories.put("2511", "ゲーム・おもちゃ");
        this.categories.put("2512", "スポーツ");
        this.categories.put("2513", "レジャー・アウトドア");
        this.categories.put("2514", "自転車・車・バイク用品");
        this.categories.put("2516", "CD・音楽ソフト");
        this.categories.put("2517", "DVD・映像ソフト");
        this.categories.put("10002", "本・雑誌・コミック");
        this.categories.put("13457", "ファッション");
        
        this.sortOrder.put("-score", "おすすめ順");
        this.sortOrder.put("+price", "商品価格が安い順");
        this.sortOrder.put("-price", "商品価格が高い順");
        this.sortOrder.put("+name", "ストア名昇順");
        this.sortOrder.put("-name", "ストア名降順");
        this.sortOrder.put("-sold", "売れ筋順");
        
    }
    
    public Map<String, String> getCategories(){
        return this.categories;
    }
    
    public Map<String, String> getSortOrder(){
        return this.sortOrder;
    }
    
}
