package com.mine.shortvideo.entity;

/**
 * 作者：created by lun.zhang on 8/13/2018 15:19
 * 邮箱：zhanglun_study@163.com
 */
public class RequestJsonParameter {
    public static String loadCreateUserJsonParameter(String numPhone,String password,String nickName){
        String postJsonData = "{ \"name\":{\"value\":\"" +
                numPhone +
                "\"}, \"pass\":{\"value\":\"" +
                password +
                "\"}, \"field_user_mobile\":[{\"value\":\"" +
                numPhone +
                "\"}], \"field_user_nickname\":[{\"value\":\"" +
                nickName +
                "\"}], \"roles\": [ { \"target_id\": \"authenticated\" } ], \"status\": [ { \"value\": true } ] }";
        return postJsonData;
    }
    public static String linkFile(int targetId){
        String postJsonData = "{\"user_picture\": [{\"target_id\": " +
                targetId +
                ",\"description\": \"The most fascinating image ever!\"}]}";
        return postJsonData;
    }
}
