package com.mine.shortvideo.constant;

public class Const {

    public static final String tokenRongIM = "IUfl58RT07nV+bevakr37kK8wVquZAqi8oaweHed+/nK2Ah2StAaMCfjuv4Qyp/j1Y95GSFPW/0N2XqjryPQ1nEodYYPvvCy";
    public static final String tokenRongIM1 = "GNqSNjh7A5K1/IkDOuQVdNv7nwl/U+JNwTLU1jKihiEFPAIjYKXZSW15ZOFqGWEkaIV2w9euQj57DaZ9PDwe2w==";
    public static final String baseUrl = "http://www.uaes.site:8088/d86";
    //获取token接口
    public static final String getTokenUrl = baseUrl + "/rest/session/token?_format=json";
    //上传图片接口
    public static final String uploadUrl = baseUrl + "/file/upload/node/article/field_hero_image?_format=json";
    //获取用户信息接口
    public static final String getUserInfoUrl = baseUrl + "/api/v1/user/mobile/";
    //创建用户
    public static final String createUser = baseUrl + "/entity/user?_format=json";
    //保留
    public static final String getStatus = baseUrl + "/node/12?_format=json";
    //上传视频
    public static final String uploadVideoUrl = baseUrl + "/file/upload/node/user_video/field_user_video?_format=json";
    //获取视频
    public static final String getVideoList = baseUrl + "/api/v1/uservideo?_format=json";
    //获取视频评论
    public static final String getVideoComment = baseUrl + "/api/v2/comments/node/9?_format=json";
    //获取发布的任务
    public static final String getTaskList = baseUrl + "/api/v1/playtogether?_format=json";
    //关联上传的文件到服务器
    public static final String linkFile = baseUrl + "/user/";
    //获取用户视频
    public static final String getUserVideoList = baseUrl + "/api/v1/myvideo/";
}
