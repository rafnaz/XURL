package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class XUrlImpl implements XUrl{

    private static final String URL_TEMPLATE="http://short.url/";
    private static final int countCharacters=9;
    private Map<String,String> longUrlMap;
    private Map<String,String> shortUrlMap;
    private Map<String,Integer> hitCountMap;
    
    
    public XUrlImpl() {
        longUrlMap =new HashMap<>();
        shortUrlMap = new HashMap<>();
        hitCountMap=new HashMap<>();
    }

    @Override
    
    public String registerNewUrl(String longUrl, String shortUrl) {
        // TODO Auto-generated method stub
        if(shortUrlMap.containsKey(shortUrl)){
            return null;
        }
        // Else, register the specified shortUrl for the given longUrl
  // Note: You don't need to validate if longUrl is already present, 
  //       assume it is always new i.e. it hasn't been seen before 
        shortUrlMap.put(shortUrl, longUrl);
        longUrlMap.put(longUrl,shortUrl);
        hitCountMap.put(longUrl,0);
                
        return shortUrl;
    }

    // If shortUrl doesn't have a corresponding longUrl, return null
  // Else, return the corresponding longUrl
    @Override
    public String getUrl(String shortUrl) {
        // TODO Auto-generated method stub
        String longUrl=shortUrlMap.get(shortUrl);
        if(longUrl!=null){
            hitCountMap.put(longUrl,hitCountMap.getOrDefault(longUrl,0)+1);
        }
        return longUrl;
    }

    // Return the number of times the longUrl has been looked up using getUrl()
    @Override
    public Integer getHitCount(String longUrl) {
        // TODO Auto-generated method stub
        return hitCountMap.getOrDefault(longUrl,0);
    }

    // Delete the mapping between this longUrl and its corresponding shortUrl
    // Do not zero the Hit Count for this longUrl
    @Override
    public String delete(String longUrl) {
        // TODO Auto-generated method stub
        String shortUrl=longUrlMap.remove(longUrl);
        if(shortUrl!=null){
            shortUrlMap.remove(shortUrl);
        }
        return shortUrl;
    }

    // If longUrl already has a corresponding shortUrl, return that shortUrl
  // If longUrl is new, create a new shortUrl for the longUrl and return it
    @Override
    public String registerNewUrl(String longUrl) {
        // TODO Auto-generated method stub
        if(longUrlMap.containsKey(longUrl)){
            return longUrlMap.get(longUrl);
        }
        String shortUrl=generateShortUrl();
        longUrlMap.put(longUrl, shortUrl);
        shortUrlMap.put(shortUrl,longUrl);
        return shortUrl;
    }

    private String generateShortUrl() {
        String characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<countCharacters;i++){
            int index=random.nextInt(characters.length());
            shortUrl.append(characters.charAt(index));
        }
        return URL_TEMPLATE+shortUrl.toString();
    }
}