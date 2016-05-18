/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author SHO
 */
public class SearchLogic {
    
    private URL url;
    private String query;
    private String sort;
    private Integer categoryID;
    
    private final static String appID = "dj0zaiZpPXplbVJNd3J4UXR4WCZzPWNvbnN1bWVyc2VjcmV0Jng9ZjI-";
    private final static String baseURL = "http://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch";
    
    private Common con = null;
    
    public SearchLogic(){
        this.query = "";
        this.sort = "";
        this.categoryID = 0;
        this.con = new Common();
    }
    
    public static SearchLogic getInstance(){
        return new SearchLogic();
    }
    
    /*
    @実際に商品検索を行うメソッド.
    @複数のメソッドを組み合わせて検索を行う.
    */
    public Map<String, ItemDataBeans> execute (String query, String category, String sort) throws MalformedURLException, IOException, SAXException, Exception{
        System.out.println("[Notice]ItemSearch.java \"execute\" start");
        //入力された各項目の確認
        checkKeyWords(query, category, sort);
        //フォームから取得した情報を文字列型URLに埋め込み, URL型に変換
        this.url = new URL(convertURL());
        //URLで接続
        HttpURLConnection urlCon = connection2Yahoo();
        //取得した商品を格納するMap
        Map<String, ItemDataBeans> itemDetailList = new LinkedHashMap<>();
        
        try {
            //YahooからXMLドキュメントを取得
            Document doc = getDocumentFromYahoo(urlCon);
            //検索できずドキュメントがnullだった場合
            if(doc == null){
                itemDetailList = null;
            }
            
            if(doc.hasChildNodes()){
                //XMLドキュメント内のHit要素を取得
                List<Element> Hits = getElementsNamedHit(doc);
                //Hit要素内の各子要素を取得
                for(Element el : Hits){
                    
                    String index = el.getAttribute("index");
                    ItemDataBeans itemDetails = new ItemDataBeans();
                    //ProductIDからCodeに変更
                    itemDetails.setIndex(index);
                    itemDetails.setPropaty("ProductID",getElementByName(el, "Code"));
                    itemDetails.setPropaty("Name",getElementByName(el, "Name"));
                    Element image = getElementByName(el, "Image");
                    itemDetails.setPropaty("Medium",getElementByName(image, "Medium"));
                    itemDetails.setPropaty("Price",getElementByName(el, "Price"));
                    itemDetails.setPropaty("Stock",getElementByName(el, "Availability"));
                    
                    //取得した商品を格納するMapにindexと商品を紐付けて保存
                    itemDetailList.put(index, itemDetails);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SearchLogic.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //接続は必ず切る
            urlCon.disconnect();
        }
        System.out.println("[Notice]ItemSearch.java \"execute\" finished");
        //商品を格納したMapを返す
        return itemDetailList;
    }
    
    /*
    @フォームから入力された分類(category), 並び順(sort)のチェックを行うメソッド.
    @キーワード(query)はSearch.javaで確認処理をしているため, 代入のみ.
    */
    public void checkKeyWords(String query, String category, String sort){
        System.out.println("入力フォームの確認を始めます");
        Map<String, String> sortList = this.con.getSortOrder();
        Map<String, String> categoryList = this.con.getCategories();
        
        //キーワードを代入
        this.query = query;
        System.out.println("キーワードOK");
        
        //並び順の確認
        if(!sort.isEmpty() && sortList.containsKey(sort)){
            this.sort = sort;
        }else{
            this.sort = "-score";
        }
        System.out.println("並び順OK");
        
        //カテゴリーの確認 ^[0-9]{1,5}$は, 1〜9の数字で1桁以上5桁未満の意味
        if(category.matches("^[0-9]{1,5}$") && categoryList.containsKey(category)){
            this.categoryID = Integer.parseInt(category);
        } else {
            this.categoryID = 1;
        }
        System.out.println("カテゴリーOK");
        System.out.println("入力フォームの確認が終了しました");
    }
    
    /*
    @フォーム入力値を文字列型のURLに埋め込み, 返る値としてそれを返すメソッド.
    @基礎URL(baseURL)とアプリケーションID(appID)はフィールドとして設定済み.
    */
    private String convertURL() throws MalformedURLException, UnsupportedEncodingException{
        System.out.println("URLの文字列からの変換を始めます");
        this.sort = URLEncoder.encode(this.sort,"utf-8");
        String strURL = this.baseURL + "?appid=" + this.appID + "&query=" + this.query + "&category_id=" + this.categoryID + "&sort=" + this.sort;
        System.out.println("URLテスト1:"+strURL);
        System.out.println("URLの文字列からの変換が終了しました");
        return strURL;
    }
    
    /*
    @URLを使ってYahooへ接続を行うメソッド.
    @
    */
    public HttpURLConnection connection2Yahoo() throws IOException{
        HttpURLConnection urlCon = null;
        
        try{
        urlCon = (HttpURLConnection)this.url.openConnection();
        urlCon.setRequestMethod("GET");
        urlCon.setInstanceFollowRedirects(false);
        urlCon.connect();
        
        } catch (IOException ioe){
            System.out.println("[error in ItemSearch.java]" + ioe.getMessage() + "[at connection2Yahoo]");
        } catch (IllegalArgumentException iae){
            System.out.println("[error in ItemSearch.java]" + iae.getMessage() + "[at connection2Yahoo]");
        }
        
        System.out.println("XMLドキュメントを取得しました");
        return urlCon;
    }
    
    /*
    @XML用APIであるDOMを使用して, XMLファイルを読み込むメソッド.
    @
    */
    public Document getDocumentFromYahoo(HttpURLConnection urlCon) throws SAXException, IOException{
        Document doc = null;
        
        try{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(urlCon.getInputStream());
        
        }catch(FactoryConfigurationError fce){
            System.out.println("[error in ItemSearch.java]" + fce.getMessage() + "[at getDocumentFromYahoo]");
        }catch(ParserConfigurationException pce){
            System.out.println("[error in ItemSearch.java]" + pce.getMessage() + "[at getDocumentFromYahoo]");
        }catch (IllegalArgumentException iae){
            System.out.println("[error in ItemSearch.java]" + iae.getMessage() + "[at getDocumentFromYahoo]");
        }
        
        return doc;
    }
    
    /*
    @XMLドキュメントからHit要素のみを取り出すメソッド.
    @
    */
    private ArrayList<Element> getElementsNamedHit (Document doc) throws Exception{
        Element ResultSet = doc.getDocumentElement();
        Element Result = getElementByName(ResultSet, "Result");
        ArrayList<Element> Hits = getElementsListByName(Result, "Hit");
        return Hits;
    }
    
    /*
    @複数の要素を取り出し, 配列として返すメソッド.
    @
    */
    private static ArrayList<Element> getElementsListByName(Element el, String name) throws Exception{
        NodeList children =el.getChildNodes();
        ArrayList<Element> Hits = new ArrayList<>();
        for(int i = 0; i < children.getLength(); i++){
            if(children.item(i) instanceof Element){
                Element e = (Element)children.item(i);
                if(e.getTagName().equals(name)){
                    Hits.add(e);
                }
            }
        }
        return Hits;
    }
    
    /*
    @要素を一つ取り出し, それを返すメソッド.
    @
    */
    private static Element getElementByName(Element el, String name) throws Exception{
        NodeList children =el.getChildNodes();
        for(int i = 0; i < children.getLength(); i++){
            if(children.item(i) instanceof Element){
                Element e = (Element)children.item(i);
                if(e.getTagName().equals(name)){
                    return e;
                }
            }
        }
        
        return null;
    }
    
    /*
    @カテゴリーマップからキー名に対応する値を取得するメソッド
    @search.jsp表示の際に条件を表示するのに使用
    */
    public String getValueFromCategory(Common con, String keyName){
        Map<String, String> categories = con.getCategories();
        String reValue="指定なし";
            for(String key : categories.keySet()){
                if(keyName.equals(key)){
                    reValue = categories.get(key);
                }
            }
        return reValue;    
    }
    
    /*
    @ソートマップからキー名に対応する値を取得するメソッド
    @search.jsp表示の際に条件を表示するのに使用
    */
    public String getValueFromSort(Common con, String keyName){
        Map<String, String> sortOrder = con.getSortOrder();
        String reValue="指定なし";
            for(String key : sortOrder.keySet()){
                if(keyName.equals(key)){
                    reValue = sortOrder.get(key);
                }
            }
        return reValue;    
    }
}
