package com.mine.shortvideo.constant;

public class Const {

    public static final String tokenRongIM = "IUfl58RT07nV+bevakr37kK8wVquZAqi8oaweHed+/nK2Ah2StAaMCfjuv4Qyp/j1Y95GSFPW/0N2XqjryPQ1nEodYYPvvCy";
    public static final String tokenRongIM1 = "GNqSNjh7A5K1/IkDOuQVdNv7nwl/U+JNwTLU1jKihiEFPAIjYKXZSW15ZOFqGWEkaIV2w9euQj57DaZ9PDwe2w==";
    public static final String baseUrl = "http://www.uaes.site:8088/d86";
    //获取token接口
    public static final String getTokenUrl = baseUrl + "/rest/session/token?_format=json";
    //上传文件接口
    public static final String uploadUrl = baseUrl + "/file/upload/node/article/field_hero_image?_format=json";
    //获取用户信息接口
    public static final String getUserInfoUrl = baseUrl + "/api/v1/user/mobile/13812340001?_format=json";
    //创建用户
    public static final String createUser = baseUrl + "/entity/user?_format=json";
}
