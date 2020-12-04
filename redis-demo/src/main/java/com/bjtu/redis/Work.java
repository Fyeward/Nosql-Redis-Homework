package com.bjtu.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

enum counterType {
    showPNUM, incrP, decrP, showPInFreq, showPOutFreq;
}
public class Work {
    public static void work(Counter counter) {
        switch (counterType.valueOf(counter.getName())) {

            case showPNUM:
                showPNUM(counter);
                break;
            case incrP:
                incrP(counter);
                break;
            case decrP:
                decrP(counter);
                break;
            case showPInFreq:
                showPInFreq(counter);
                break;
            case showPOutFreq:
                showPOutFreq(counter);
                break;
        }
    }

    private static void decrP(Counter decr) {
        String key = decr.getKey().get(0);
        String list=decr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.decr(key, decr.getValue());
            System.out.println("The value of " + key + " decreased by " + decr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    private static void showPNUM(Counter c) {
        String key = c.getKey().get(0);

        RedisUtil redisUtil = new RedisUtil();
        try {
            System.out.println("The value of " + key + " is " + redisUtil.get(key));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

    }

    private static void incrP(Counter incr) {
        String key = incr.getKey().get(0);
        String list=incr.getKey().get(1);
        RedisUtil redisUtil = new RedisUtil();
        try {
            redisUtil.incr(key, incr.getValue());
            System.out.println("The value of " + key + " increased by " + incr.getValue() + " and became " + redisUtil.get(key));
            SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = new Date();
            String string=time.format(date);
            redisUtil.lpush(list,string);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void showPInFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil=new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);

            for (int i = 0; i < redisUtil.llen(keyField); i++) {
                String t=redisUtil.lindex(keyField,i);
                //System.out.println(startTime);
                //System.out.println(endTime);
                if(t.compareTo(startTime)>=0 && t.compareTo(endTime)<=0){
                    System.out.println("有proletarian在"+t+"时刻进入");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showPOutFreq(Counter counter){
        String keyField=counter.getKey().get(0);
        RedisUtil redisUtil=new RedisUtil();
        try{
            String date=counter.getFREQ();
            String startTime=date.substring(0,12);
            String endTime=date.substring(13,25);
            for (int i = 0; i < redisUtil.llen(keyField); i++) {
//            System.out.println(jedis.lindex("UserOutList",i));
                if(redisUtil.lindex(keyField,i).compareTo(startTime)>=0 && redisUtil.lindex(keyField,i).compareTo(endTime)<=0){
                    System.out.println("有proletarian在"+redisUtil.lindex(keyField,i)+"时刻退出");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
