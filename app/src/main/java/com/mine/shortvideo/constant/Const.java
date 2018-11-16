package com.mine.shortvideo.constant;

public class Const {
    public static boolean isRefreshUserInfo = false;

    public static String tokenRongIM = "IUfl58RT07nV+bevakr37kK8wVquZAqi8oaweHed+/nK2Ah2StAaMCfjuv4Qyp/j1Y95GSFPW/0N2XqjryPQ1nEodYYPvvCy";
//    public static final String tokenRongIM1 = "GNqSNjh7A5K1/IkDOuQVdNv7nwl/U+JNwTLU1jKihiEFPAIjYKXZSW15ZOFqGWEkaIV2w9euQj57DaZ9PDwe2w==";
//    public static final String baseUrl = "http://www.uaes.site:8088/d86";
    public static final String baseUrl = "http://www.51pepe.com";
    public static final String base = "http://121.196.197.77:8060";
    //获取token接口
    public static final String getTokenUrl = baseUrl + "/rest/session/token?_format=json";
    //上传头像图片接口
    public static final String uploadUrl = baseUrl + "/file/upload/user/user/user_picture?_format=json";
    //上传个人展示图片接口
    public static final String upUserShowPicUrl = baseUrl + "/file/upload/user/user/field_personalpicshow?_format=json";
    //获取用户信息接口
    public static final String getUserInfoUrl = baseUrl + "/api/v1/user/mobile/";
    //修改用户信息接口
    public static final String changeUserInfoUrl = baseUrl + "/user/";
    //创建用户
    public static final String createUser = baseUrl + "/entity/user?_format=json";
    //保留
    public static final String getStatus = baseUrl + "/node/12?_format=json";
    //上传视频第一步
    public static final String uploadVideoUrl = baseUrl + "/file/upload/media/video/field_media_video_file?_format=json";
    //上传视频第二步
    public static final String CreateMediaVideoUrl = baseUrl + "/entity/media?_format=json";
    //上传视频第三步
    public static final String CreateUserVideoUrl = baseUrl + "/node?_format=json";
    //获取视频
    public static final String getVideoList = baseUrl + "/api/v1/uservideo?_format=json";
    //获取视频评论
    public static final String getVideoComment = baseUrl + "/api/v2/comments/node/9?_format=json";
    //获取发布的任务
    public static final String getTaskList = baseUrl + "/api/v3/playtogether?_format=json";
    //关联上传的文件到服务器
    public static final String linkFile = baseUrl + "/user/";
    //获取用户视频
    public static final String getUserVideoList = baseUrl + "/api/v1/myvideo/";
    //获取发布的任务
    public static final String getTaskListV2 = baseUrl + "/api/v1/gettask?_format=json";
    //删除用户视频
    public static final String deleteUserVideo = baseUrl + "/node/";
    //发布完美脱单
    public static final String publishTask = baseUrl + "/node?_format=json";
    //获取融云Token
    public static final String getRongToken = base + "/peipei/getUserToken";
    //更新融云用户信息
    public static final String updateRongUserInfo = base + "/peipei/refreshUserInfo";
    //视频点赞
    public static final String likeVideo = baseUrl + "/api/v1/flagrest?_format=json";


    public final static int TASKSCORE = 1;
    public final static int TASKGOLD = 2;
    public final static int TASKTUTORIAL = 3;
    public final static int TASKFREE = 4;
    public final static int STARNUM = 5;
    public final static int GOLD = 6;
    public final static String TASKSCORESTR = "pointup";
    public final static String TASKGOLDSTR = "taskgold";
    public final static String TASKTUTORIALSTR = "tutorial";
    public final static String TASKFREESTR = "playtogether";
}
