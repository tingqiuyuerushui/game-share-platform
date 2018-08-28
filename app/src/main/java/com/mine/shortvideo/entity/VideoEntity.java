package com.mine.shortvideo.entity;

import com.mine.shortvideo.constant.Const;
import com.mine.shortvideo.utils.Utils;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/28/2018 11:01
 * 邮箱：zhanglun_study@163.com
 */
public class VideoEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : <a href="/d86/node/8" hreflang="en">王者荣耀30M</a>
         * field_media_video_file :
         <span class="file file--mime-video-mp4 file--video"> <a href="http://uaes.site:8088/d86/sites/default/files/2018-08/%E7%8E%8B%E8%80%85%E8%8D%A3%E8%80%8030M.mp4" type="video/mp4; length=30145728">王者荣耀30M.mp4</a></span>

         * field_media_oembed_video :
         * comment_count : 11
         * field_user_statement :
         * field_user_nickname : 7号昵称
         * user_picture :
         * like_count : 1
         * collect_count : 0
         * body : <p>王者荣耀30M</p>
         * nid : 8
         */

        private String title;
        private String field_media_video_file;
        private String field_media_oembed_video;
        private String comment_count;
        private String field_user_statement;
        private String field_user_nickname;
        private String user_picture;
        private String like_count;
        private String collect_count;
        private String body;
        private String nid;

        public String getTitle() {
            title = Utils.getTitle(title);
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField_media_video_file() {
            field_media_video_file = Utils.getLink(field_media_video_file);
            return field_media_video_file;
        }

        public void setField_media_video_file(String field_media_video_file) {

            this.field_media_video_file = field_media_video_file;
        }

        public String getField_media_oembed_video() {
            return field_media_oembed_video;
        }

        public void setField_media_oembed_video(String field_media_oembed_video) {
            this.field_media_oembed_video = field_media_oembed_video;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getField_user_statement() {
            return field_user_statement;
        }

        public void setField_user_statement(String field_user_statement) {
            this.field_user_statement = field_user_statement;
        }

        public String getField_user_nickname() {
            return field_user_nickname;
        }

        public void setField_user_nickname(String field_user_nickname) {
            this.field_user_nickname = field_user_nickname;
        }

        public String getUser_picture() {
            user_picture =  Utils.getImgUrl(user_picture);
            return user_picture;
        }

        public void setUser_picture(String user_picture) {

            this.user_picture = user_picture;
        }

        public String getLike_count() {
            return like_count;
        }

        public void setLike_count(String like_count) {
            this.like_count = like_count;
        }

        public String getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(String collect_count) {
            this.collect_count = collect_count;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }
    }
}
