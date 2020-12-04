package com.bjtu.redis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Counter {
    private String cName;
    private String cIndex;
    private ArrayList<String> key;
    private String type;
    private int value;
    private String Freq;

    public String getName() {

        return cName;
    }

    public String getIndex() {

        return cIndex;
    }

    public ArrayList<String> getKey() {

        return key;
    }

    public String getType() {

        return type;
    }

    public int getValue() {

        return value;
    }

    public String getFREQ() {

        return Freq;
    }

    public Counter(JSONObject obj){
        cName=obj.getString("counterName");
        cIndex=obj.getString("counterIndex");
        JSONArray array = obj.getJSONArray("keyFields");
        key=new ArrayList<>();
        for (Object s: array) {
            key.add((String)s);
        }
        type=obj.getString("type");
        value=0;

        if(obj.containsKey("valueFields"))
        {
            value=obj.getInteger("valueFields");
        }
        if(obj.containsKey("FREQFields"))
        {
            Freq=obj.getString("FREQFields");
        }
    }
}
