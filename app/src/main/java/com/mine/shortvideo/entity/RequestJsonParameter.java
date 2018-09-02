package com.mine.shortvideo.entity;

/**
 * 作者：created by lun.zhang on 8/13/2018 15:19
 * 邮箱：zhanglun_study@163.com
 */
public class RequestJsonParameter {
    //创建用户json string
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
    //关联图片json String
    public static String linkFile(int targetId){
        String postJsonData = "{\"user_picture\": [{\"target_id\": " +
                targetId +
                ",\"description\": \"The most fascinating image ever!\"}]}";
        return postJsonData;
    }
    //关联视频 json string
    public static String linkVideoFile(int targetId){
        String postJsonData = " {\"field_user_video\":[\"target_id\":" +
                targetId +
                ",\"target_type\":\"media\"}]}";
        return postJsonData;
    }
    //发布任务json string
    public static String publishTask(String title,String gameLevel,String gamePlatform,String gameName,String bookTime){
        String postJsonData = "{\"type\":[{\"target_id\":\"playtogether\",\"target_type\":\"node_type\"}],\"title\":[{\"value\":\"" +
                title +
                "\"}],\"field_playtogether_anytime\":[{\"value\":false}],\"field_playtogether_game_level\":[{\"value\":\"" +
                gameLevel +
                "\"}],\"field_playtogether_game_platform\":[{\"value\":\"" +
                gamePlatform +
                "\"}],\"field_playtogether_gamename\":[{\"value\":\"" +
                gameName +
                "\"}],\"field_pplaytogether_booktime\":[{\"value\":\"" +
                bookTime +
                "\"}]}";
        return postJsonData;
    }
    //上传视频第二步json参数
    public static String CreateMediaJsonStr(int uid,int targerId){
        String postJsonData = "{\"bundle\": [{\"target_id\": \"video\",\"target_type\": \"media_type\"}],\"name\": [{\"value\": \"sample2\"}],\"uid\": [{\"target_id\": " +
                uid +
                ",\"target_type\": \"user\"}],\"field_media_video_file\": [{\"target_type\": \"file\",\"target_id\": " +
                targerId +
                "}]}";

        return postJsonData;
    }
    //上传视频第三步获得Mid
    public static String CreateUserVideoJsonStr(int targerId){
        String postJsonData = "{\"type\": [{\"target_id\": \"user_video\",\"target_type\": \"node_type\"}],\"title\": [{\"value\": \"sample video test\"}],\"field_user_video\": [{\"target_id\": " +
                targerId + ",\"target_type\": \"media\"}]}";

        return postJsonData;
    }


}
