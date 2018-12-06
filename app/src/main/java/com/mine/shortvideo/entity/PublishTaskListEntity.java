package com.mine.shortvideo.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.mine.shortvideo.utils.Utils;

import java.util.List;

/**
 * 作者：created by lun.zhang on 11/16/2018 11:36
 * 邮箱：zhanglun_study@163.com
 */
public class PublishTaskListEntity {
    private List<PublishTaskListEntity.DataBean> data;

    public List<PublishTaskListEntity.DataBean> getData() {
        return data;
    }

    public void setData(List<PublishTaskListEntity.DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * title : <a href="/node/62" hreflang="en">任务 by17839997702</a>
         * field_task_state : published
         * field_gotuser :
         * field_teach_game_numbers : 5
         * field_stars : 0
         * field_anytime : On
         * field_gamename : 王者荣耀
         * field_game_platform : 安卓QQ
         * field_game_level : 最强王者8星
         * field_target_division : null
         * field_type : tutorial
         * field_remuneration : 500
         * field_booktime : Fri, 11/16/2018 - 20:33
         * field_personalpicshow :
         * delta : 0
         * field_user_statement : 我最棒！！
         * field_user_tags :
         * delta_1 : 0
         * field_user_location :
         * field_user_matches :
         * field_user_age :
         * field_user_gender : 女
         * field_user_mobile : 17839997702
         * field_user_starsign :
         * field_user_stars :
         * field_user_nickname : 记芋跨奖
         * field_user_gamename : 王者荣耀
         * field_user_game_platform : 安卓QQ
         * field_user_platform :
         * field_user_game_level : 青铜1星
         * user_picture :   <img src="/sites/default/files/pictures/2018-09/frame_1s_0.jpg" width="1280" height="720" typeof="foaf:Image" />


         * field_user_point :
         * field_user_level :
         * field_user_voice :
         */

        private String title;
        private String field_task_state;
        private String field_gotuser;
        private String field_teach_game_numbers;
        private String field_stars;
        private String field_anytime;
        private String field_gamename;
        private String field_game_platform;
        private String field_game_level;
        private String field_target_division;
        private String field_type;
        private String field_remuneration;
        private String field_booktime;
        private String field_personalpicshow;
        private String delta;
        private String field_user_statement;
        private String field_user_tags;
        private String delta_1;
        private String field_user_location;
        private String field_user_matches;
        private String field_user_age;
        private String field_user_gender;
        private String field_user_mobile;
        private String field_user_starsign;
        private String field_user_stars;
        private String field_user_nickname;
        private String field_user_gamename;
        private String field_user_game_platform;
        private String field_user_platform;
        private String field_user_game_level;
        private String user_picture;
        private String field_user_point;
        private String field_user_level;
        private String field_user_voice;
        private String uid;
        private String nid;

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField_task_state() {
            return field_task_state;
        }

        public void setField_task_state(String field_task_state) {
            this.field_task_state = field_task_state;
        }

        public String getField_gotuser() {
            return field_gotuser;
        }

        public void setField_gotuser(String field_gotuser) {
            this.field_gotuser = field_gotuser;
        }

        public String getField_teach_game_numbers() {
            return field_teach_game_numbers;
        }

        public void setField_teach_game_numbers(String field_teach_game_numbers) {
            this.field_teach_game_numbers = field_teach_game_numbers;
        }

        public String getField_stars() {
            return field_stars;
        }

        public void setField_stars(String field_stars) {
            this.field_stars = field_stars;
        }

        public String getField_anytime() {
            return field_anytime;
        }

        public void setField_anytime(String field_anytime) {
            this.field_anytime = field_anytime;
        }

        public String getField_gamename() {
            return field_gamename;
        }

        public void setField_gamename(String field_gamename) {
            this.field_gamename = field_gamename;
        }

        public String getField_game_platform() {
            return field_game_platform;
        }

        public void setField_game_platform(String field_game_platform) {
            this.field_game_platform = field_game_platform;
        }

        public String getField_game_level() {
            return field_game_level;
        }

        public void setField_game_level(String field_game_level) {
            this.field_game_level = field_game_level;
        }

        public String getField_target_division() {
            return field_target_division;
        }

        public void setField_target_division(String field_target_division) {
            this.field_target_division = field_target_division;
        }

        public String getField_type() {
            return field_type;
        }

        public void setField_type(String field_type) {
            this.field_type = field_type;
        }

        public String getField_remuneration() {
            return field_remuneration;
        }

        public void setField_remuneration(String field_remuneration) {
            this.field_remuneration = field_remuneration;
        }

        public String getField_booktime() {
            field_booktime = Utils.getBookTime(field_booktime);
            return field_booktime;
        }

        public void setField_booktime(String field_booktime) {
            this.field_booktime = field_booktime;
        }

        public String getField_personalpicshow() {
            return field_personalpicshow;
        }

        public void setField_personalpicshow(String field_personalpicshow) {
            this.field_personalpicshow = field_personalpicshow;
        }

        public String getDelta() {
            return delta;
        }

        public void setDelta(String delta) {
            this.delta = delta;
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

        public String getDelta_1() {
            return delta_1;
        }

        public void setDelta_1(String delta_1) {
            this.delta_1 = delta_1;
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

        public String getField_user_game_platform() {
            return field_user_game_platform;
        }

        public void setField_user_game_platform(String field_user_game_platform) {
            this.field_user_game_platform = field_user_game_platform;
        }

        public String getField_user_platform() {
            return field_user_platform;
        }

        public void setField_user_platform(String field_user_platform) {
            this.field_user_platform = field_user_platform;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.field_task_state);
            dest.writeString(this.field_gotuser);
            dest.writeString(this.field_teach_game_numbers);
            dest.writeString(this.field_stars);
            dest.writeString(this.field_anytime);
            dest.writeString(this.field_gamename);
            dest.writeString(this.field_game_platform);
            dest.writeString(this.field_game_level);
            dest.writeString(this.field_target_division);
            dest.writeString(this.field_type);
            dest.writeString(this.field_remuneration);
            dest.writeString(this.field_booktime);
            dest.writeString(this.field_personalpicshow);
            dest.writeString(this.delta);
            dest.writeString(this.field_user_statement);
            dest.writeString(this.field_user_tags);
            dest.writeString(this.delta_1);
            dest.writeString(this.field_user_location);
            dest.writeString(this.field_user_matches);
            dest.writeString(this.field_user_age);
            dest.writeString(this.field_user_gender);
            dest.writeString(this.field_user_mobile);
            dest.writeString(this.field_user_starsign);
            dest.writeString(this.field_user_stars);
            dest.writeString(this.field_user_nickname);
            dest.writeString(this.field_user_gamename);
            dest.writeString(this.field_user_game_platform);
            dest.writeString(this.field_user_platform);
            dest.writeString(this.field_user_game_level);
            dest.writeString(this.user_picture);
            dest.writeString(this.field_user_point);
            dest.writeString(this.field_user_level);
            dest.writeString(this.field_user_voice);
            dest.writeString(this.uid);
            dest.writeString(this.nid);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.title = in.readString();
            this.field_task_state = in.readString();
            this.field_gotuser = in.readString();
            this.field_teach_game_numbers = in.readString();
            this.field_stars = in.readString();
            this.field_anytime = in.readString();
            this.field_gamename = in.readString();
            this.field_game_platform = in.readString();
            this.field_game_level = in.readString();
            this.field_target_division = in.readString();
            this.field_type = in.readString();
            this.field_remuneration = in.readString();
            this.field_booktime = in.readString();
            this.field_personalpicshow = in.readString();
            this.delta = in.readString();
            this.field_user_statement = in.readString();
            this.field_user_tags = in.readString();
            this.delta_1 = in.readString();
            this.field_user_location = in.readString();
            this.field_user_matches = in.readString();
            this.field_user_age = in.readString();
            this.field_user_gender = in.readString();
            this.field_user_mobile = in.readString();
            this.field_user_starsign = in.readString();
            this.field_user_stars = in.readString();
            this.field_user_nickname = in.readString();
            this.field_user_gamename = in.readString();
            this.field_user_game_platform = in.readString();
            this.field_user_platform = in.readString();
            this.field_user_game_level = in.readString();
            this.user_picture = in.readString();
            this.field_user_point = in.readString();
            this.field_user_level = in.readString();
            this.field_user_voice = in.readString();
            this.uid = in.readString();
            this.nid = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
