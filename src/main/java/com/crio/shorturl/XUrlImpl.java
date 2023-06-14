package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map;

public class XUrlImpl implements XUrl {

    HashMap<String, String> longToShortMapping = new HashMap<>();
    HashMap<String, Integer> CountMapping = new HashMap<>();
    int count = 1111;

    @Override
    public String registerNewUrl(String longUrl) {
        if (longToShortMapping.containsKey(longUrl)) {
            return longToShortMapping.get(longUrl);
        } else {
            String str = "abcde" + count;
            count += 1;
            String shortUrl = "http://short.url/" + str;
            longToShortMapping.put(longUrl, shortUrl);
            return shortUrl;
        }
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if (longToShortMapping.containsValue(shortUrl)) {
            return null;
        } else {
            longToShortMapping.put(longUrl, shortUrl);
            return shortUrl;
        }
    }

    @Override
    public String getUrl(String shortUrl) {
        if (longToShortMapping.containsValue(shortUrl)) {
            for (Map.Entry<String, String> entry : longToShortMapping.entrySet()) {
                if (entry.getValue().equals(shortUrl)) {
                    CountMapping.put(entry.getKey(), CountMapping.getOrDefault(entry.getKey(), 0) + 1);
                    return entry.getKey();
                }
            }
        } 
        return null;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        if(!CountMapping.containsKey(longUrl)) return 0;
        return CountMapping.get(longUrl);
    }

    @Override
    public String delete(String longUrl) {
        longToShortMapping.remove(longUrl);
        return "Url deleted successfully!";
    }



}
