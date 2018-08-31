package com.mine.shortvideo.entity;

import android.text.TextUtils;

import com.mine.shortvideo.utils.Utils;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/30/2018 14:54
 * 邮箱：zhanglun_study@163.com
 */
public class PublishTaskEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : <a href="/d86/node/16" hreflang="en">完美脱单2_by 13812340001 rest m</a>
         * field_playtogether_anytime : 即时
         * field_playtogether_gamename : 王者荣耀1 m
         * field_playtogether_game_platform : 安卓QQ1 m
         * field_playtogether_game_level : 青铜1星1 m
         * field_pplaytogether_booktime : Fri, 08/24/2018 - 22:28
         * uid : 2
         * field_user_isvip : No
         * field_user_statement : 爱笑的女孩运气都不会太差呦
         * field_user_tags :
         * delta : 0
         * field_user_location :
         * field_user_matches :
         * field_user_age :
         * field_user_gender : 男
         * field_user_mobile : 13812340001
         * field_user_starsign : 白羊座
         * field_user_stars :
         * field_user_nickname : 会飞的毛毛虫
         * field_user_gamename :
         * field_user_platform :
         * field_user_game_platform :
         * field_user_game_level :
         * user_picture :   <img src="/d86/sites/default/files/pictures/2018-08/cx_0.png" width="48" height="48" alt="" typeof="foaf:Image" />


         * field_user_point : 100
         * field_user_level :
         * field_user_voice : <a href="/d86/media/1" hreflang="en">voice</a>
         */

        private String title;
        private String field_playtogether_anytime;
        private String field_playtogether_gamename;
        private String field_playtogether_game_platform;
        private String field_playtogether_game_level;
        private String field_pplaytogether_booktime;
        private String uid;
        private String field_user_isvip;
        private String field_user_statement;
        private String field_user_tags;
        private String delta;
        private String field_user_location;
        private String field_user_matches;
        private String field_user_age;
        private String field_user_gender;
        private String field_user_mobile;
        private String field_user_starsign;
        private String field_user_stars;
        private String field_user_nickname;
        private String field_user_gamename;
        private String field_user_platform;
        private String field_user_game_platform;
        private String field_user_game_level;
        private String user_picture;
        private String field_user_point;
        private String field_user_level;
        private String field_user_voice;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField_playtogether_anytime() {
            return field_playtogether_anytime;
        }

        public void setField_playtogether_anytime(String field_playtogether_anytime) {
            this.field_playtogether_anytime = field_playtogether_anytime;
        }

        public String getField_playtogether_gamename() {
            return field_playtogether_gamename;
        }

        public void setField_playtogether_gamename(String field_playtogether_gamename) {
            this.field_playtogether_gamename = field_playtogether_gamename;
        }

        public String getField_playtogether_game_platform() {
            return field_playtogether_game_platform;
        }

        public void setField_playtogether_game_platform(String field_playtogether_game_platform) {
            this.field_playtogether_game_platform = field_playtogether_game_platform;
        }

        public String getField_playtogether_game_level() {
            return field_playtogether_game_level;
        }

        public void setField_playtogether_game_level(String field_playtogether_game_level) {
            this.field_playtogether_game_level = field_playtogether_game_level;
        }

        public String getField_pplaytogether_booktime() {
            field_pplaytogether_booktime = Utils.getBookTime(field_pplaytogether_booktime);
            return field_pplaytogether_booktime;
        }

        public void setField_pplaytogether_booktime(String field_pplaytogether_booktime) {
            this.field_pplaytogether_booktime = field_pplaytogether_booktime;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getField_user_isvip() {
            return field_user_isvip;
        }

        public void setField_user_isvip(String field_user_isvip) {
            this.field_user_isvip = field_user_isvip;
        }

        public String getField_user_statement() {
            return field_user_statement;
        }

        public void setField_user_statement(String field_user_statement) {
            this.field_user_statement = field_user_statement;
        }

        public String getField_user_tags() {
            field_user_tags = Utils.getUserLable(field_user_tags);
            return field_user_tags;
        }

        public void setField_user_tags(String field_user_tags) {
            this.field_user_tags = field_user_tags;
        }

        public String getDelta() {
            return delta;
        }

        public void setDelta(String delta) {
            this.delta = delta;
        }

        public String getField_user_location() {
            return field_user_location;
        }

        public void setField_user_location(String field_user_location) {
            this.field_user_location = field_user_location;
        }

        public String getField_user_matches() {
            return field_user_matches;
        }

        public void setField_user_matches(String field_user_matches) {
            this.field_user_matches = field_user_matches;
        }

        public String getField_user_age() {
            return field_user_age;
        }

        public void setField_user_age(String field_user_age) {
            this.field_user_age = field_user_age;
        }

        public String getField_user_gender() {
            return field_user_gender;
        }

        public void setField_user_gender(String field_user_gender) {
            this.field_user_gender = field_user_gender;
        }

        public String getField_user_mobile() {
            return field_user_mobile;
        }

        public void setField_user_mobile(String field_user_mobile) {
            this.field_user_mobile = field_user_mobile;
        }

        public String getField_user_starsign() {
            return field_user_starsign;
        }

        public void setField_user_starsign(String field_user_starsign) {
            this.field_user_starsign = field_user_starsign;
        }

        public String getField_user_stars() {
            return field_user_stars;
        }

        public void setField_user_stars(String field_user_stars) {
            this.field_user_stars = field_user_stars;
        }

        public String getField_user_nickname() {
            return field_user_nickname;
        }

        public void setField_user_nickname(String field_user_nickname) {
            this.field_user_nickname = field_user_nickname;
        }

        public String getField_user_gamename() {
            return field_user_gamename;
        }

        public void setField_user_gamename(String field_user_gamename) {
            this.field_user_gamename = field_user_gamename;
        }

        public String getField_user_platform() {
            return field_user_platform;
        }

        public void setField_user_platform(String field_user_platform) {
            this.field_user_platform = field_user_platform;
        }

        public String getField_user_game_platform() {
            return field_user_game_platform;
        }

        public void setField_user_game_platform(String field_user_game_platform) {
            this.field_user_game_platform = field_user_game_platform;
        }

        public String getField_user_game_level() {
            return field_user_game_level;
        }

        public void setField_user_game_level(String field_user_game_level) {
            this.field_user_game_level = field_user_game_level;
        }

        public String getUser_picture() {
            if(!TextUtils.isEmpty(user_picture))
                user_picture =  Utils.getImgUrl(user_picture);
            return user_picture;
        }

        public void setUser_picture(String user_picture) {
            this.user_picture = user_picture;
        }

        public String getField_user_point() {
            return field_user_point;
        }

        public void setField_user_point(String field_user_point) {
            this.field_user_point = field_user_point;
        }

        public String getField_user_level() {
            return field_user_level;
        }

        public void setField_user_level(String field_user_level) {
            this.field_user_level = field_user_level;
        }

        public String getField_user_voice() {
            return field_user_voice;
        }

        public void setField_user_voice(String field_user_voice) {
            this.field_user_voice = field_user_voice;
        }
    }
}
