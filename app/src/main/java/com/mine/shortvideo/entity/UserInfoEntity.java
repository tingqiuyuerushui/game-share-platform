package com.mine.shortvideo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：created by lun.zhang on 8/10/2018 10:44
 * 邮箱：zhanglun_study@163.com
 */
public class UserInfoEntity{


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }
    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UidBean> uid;
        private List<UuidBean> uuid;
        private List<LangcodeBean> langcode;
        private List<PreferredLangcodeBean> preferred_langcode;
        private List<PreferredAdminLangcodeBean> preferred_admin_langcode;
        private List<NameBean> name;
        private List<?> mail;
        private List<TimezoneBean> timezone;
        private List<StatusBean> status;
        private List<CreatedBean> created;
        private List<ChangedBean> changed;
        private List<AccessBean> access;
        private List<LoginBean> login;
        private List<?> init;
        private List<?> roles;
        private List<DefaultLangcodeBean> default_langcode;
        private List<FieldPersonalpicshowBean> field_personalpicshow;
        private List<FieldUserAgeBean> field_user_age;
        private List<FieldUserGameLevelBean> field_user_game_level;
        private List<?> field_user_game_platform;
        private List<FieldUserGamenameBean> field_user_gamename;
        private List<FieldUserGenderBean> field_user_gender;
        private List<FieldUserIsvipBean> field_user_isvip;
        private List<FieldUserLevelBean> field_user_level;
        private List<?> field_user_location;
        private List<FieldUserMatchesBean> field_user_matches;
        private List<FieldUserMobileBean> field_user_mobile;
        private List<FieldUserNicknameBean> field_user_nickname;
        private List<FieldUserPlatformBean> field_user_platform;
        private List<FieldUserPointBean> field_user_point;
        private List<FieldUserStarsBean> field_user_stars;
        private List<FieldUserStarsignBean> field_user_starsign;
        private List<FieldUserStatementBean> field_user_statement;
        private List<?> field_user_tags;
        private List<FieldUserVoiceBean> field_user_voice;
        private List<UserPictureBean> user_picture;

        public List<UidBean> getUid() {
            return uid;
        }

        public void setUid(List<UidBean> uid) {
            this.uid = uid;
        }

        public List<UuidBean> getUuid() {
            return uuid;
        }

        public void setUuid(List<UuidBean> uuid) {
            this.uuid = uuid;
        }

        public List<LangcodeBean> getLangcode() {
            return langcode;
        }

        public void setLangcode(List<LangcodeBean> langcode) {
            this.langcode = langcode;
        }

        public List<PreferredLangcodeBean> getPreferred_langcode() {
            return preferred_langcode;
        }

        public void setPreferred_langcode(List<PreferredLangcodeBean> preferred_langcode) {
            this.preferred_langcode = preferred_langcode;
        }

        public List<PreferredAdminLangcodeBean> getPreferred_admin_langcode() {
            return preferred_admin_langcode;
        }

        public void setPreferred_admin_langcode(List<PreferredAdminLangcodeBean> preferred_admin_langcode) {
            this.preferred_admin_langcode = preferred_admin_langcode;
        }

        public List<NameBean> getName() {
            return name;
        }

        public void setName(List<NameBean> name) {
            this.name = name;
        }

        public List<?> getMail() {
            return mail;
        }

        public void setMail(List<?> mail) {
            this.mail = mail;
        }

        public List<TimezoneBean> getTimezone() {
            return timezone;
        }

        public void setTimezone(List<TimezoneBean> timezone) {
            this.timezone = timezone;
        }

        public List<StatusBean> getStatus() {
            return status;
        }

        public void setStatus(List<StatusBean> status) {
            this.status = status;
        }

        public List<CreatedBean> getCreated() {
            return created;
        }

        public void setCreated(List<CreatedBean> created) {
            this.created = created;
        }

        public List<ChangedBean> getChanged() {
            return changed;
        }

        public void setChanged(List<ChangedBean> changed) {
            this.changed = changed;
        }

        public List<AccessBean> getAccess() {
            return access;
        }

        public void setAccess(List<AccessBean> access) {
            this.access = access;
        }

        public List<LoginBean> getLogin() {
            return login;
        }

        public void setLogin(List<LoginBean> login) {
            this.login = login;
        }

        public List<?> getInit() {
            return init;
        }

        public void setInit(List<?> init) {
            this.init = init;
        }

        public List<?> getRoles() {
            return roles;
        }

        public void setRoles(List<?> roles) {
            this.roles = roles;
        }

        public List<DefaultLangcodeBean> getDefault_langcode() {
            return default_langcode;
        }

        public void setDefault_langcode(List<DefaultLangcodeBean> default_langcode) {
            this.default_langcode = default_langcode;
        }

        public List<FieldPersonalpicshowBean> getField_personalpicshow() {
            return field_personalpicshow;
        }

        public void setField_personalpicshow(List<FieldPersonalpicshowBean> field_personalpicshow) {
            this.field_personalpicshow = field_personalpicshow;
        }

        public List<FieldUserAgeBean> getField_user_age() {
            return field_user_age;
        }

        public void setField_user_age(List<FieldUserAgeBean> field_user_age) {
            this.field_user_age = field_user_age;
        }

        public List<FieldUserGameLevelBean> getField_user_game_level() {
            return field_user_game_level;
        }

        public void setField_user_game_level(List<FieldUserGameLevelBean> field_user_game_level) {
            this.field_user_game_level = field_user_game_level;
        }

        public List<?> getField_user_game_platform() {
            return field_user_game_platform;
        }

        public void setField_user_game_platform(List<?> field_user_game_platform) {
            this.field_user_game_platform = field_user_game_platform;
        }

        public List<FieldUserGamenameBean> getField_user_gamename() {
            return field_user_gamename;
        }

        public void setField_user_gamename(List<FieldUserGamenameBean> field_user_gamename) {
            this.field_user_gamename = field_user_gamename;
        }

        public List<FieldUserGenderBean> getField_user_gender() {
            return field_user_gender;
        }

        public void setField_user_gender(List<FieldUserGenderBean> field_user_gender) {
            this.field_user_gender = field_user_gender;
        }

        public List<FieldUserIsvipBean> getField_user_isvip() {
            return field_user_isvip;
        }

        public void setField_user_isvip(List<FieldUserIsvipBean> field_user_isvip) {
            this.field_user_isvip = field_user_isvip;
        }

        public List<FieldUserLevelBean> getField_user_level() {
            return field_user_level;
        }

        public void setField_user_level(List<FieldUserLevelBean> field_user_level) {
            this.field_user_level = field_user_level;
        }

        public List<?> getField_user_location() {
            return field_user_location;
        }

        public void setField_user_location(List<?> field_user_location) {
            this.field_user_location = field_user_location;
        }

        public List<FieldUserMatchesBean> getField_user_matches() {
            return field_user_matches;
        }

        public void setField_user_matches(List<FieldUserMatchesBean> field_user_matches) {
            this.field_user_matches = field_user_matches;
        }

        public List<FieldUserMobileBean> getField_user_mobile() {
            return field_user_mobile;
        }

        public void setField_user_mobile(List<FieldUserMobileBean> field_user_mobile) {
            this.field_user_mobile = field_user_mobile;
        }

        public List<FieldUserNicknameBean> getField_user_nickname() {
            return field_user_nickname;
        }

        public void setField_user_nickname(List<FieldUserNicknameBean> field_user_nickname) {
            this.field_user_nickname = field_user_nickname;
        }

        public List<FieldUserPlatformBean> getField_user_platform() {
            return field_user_platform;
        }

        public void setField_user_platform(List<FieldUserPlatformBean> field_user_platform) {
            this.field_user_platform = field_user_platform;
        }

        public List<FieldUserPointBean> getField_user_point() {
            return field_user_point;
        }

        public void setField_user_point(List<FieldUserPointBean> field_user_point) {
            this.field_user_point = field_user_point;
        }

        public List<FieldUserStarsBean> getField_user_stars() {
            return field_user_stars;
        }

        public void setField_user_stars(List<FieldUserStarsBean> field_user_stars) {
            this.field_user_stars = field_user_stars;
        }

        public List<FieldUserStarsignBean> getField_user_starsign() {
            return field_user_starsign;
        }

        public void setField_user_starsign(List<FieldUserStarsignBean> field_user_starsign) {
            this.field_user_starsign = field_user_starsign;
        }

        public List<FieldUserStatementBean> getField_user_statement() {
            return field_user_statement;
        }

        public void setField_user_statement(List<FieldUserStatementBean> field_user_statement) {
            this.field_user_statement = field_user_statement;
        }

        public List<?> getField_user_tags() {
            return field_user_tags;
        }

        public void setField_user_tags(List<?> field_user_tags) {
            this.field_user_tags = field_user_tags;
        }

        public List<FieldUserVoiceBean> getField_user_voice() {
            return field_user_voice;
        }

        public void setField_user_voice(List<FieldUserVoiceBean> field_user_voice) {
            this.field_user_voice = field_user_voice;
        }

        public List<UserPictureBean> getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(List<UserPictureBean> user_picture) {
            this.user_picture = user_picture;
        }

        public static class UidBean {
            /**
             * value : 2
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class UuidBean {
            /**
             * value : 9c426e78-254f-4833-9af8-98d90eece083
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class LangcodeBean {
            /**
             * value : en
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class PreferredLangcodeBean {
            /**
             * value : en
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class PreferredAdminLangcodeBean {
            /**
             * value : en
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class NameBean {
            /**
             * value : 13812340001
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class TimezoneBean {
            /**
             * value : Asia/Shanghai
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class StatusBean {
            /**
             * value : true
             */

            private boolean value;

            public boolean isValue() {
                return value;
            }

            public void setValue(boolean value) {
                this.value = value;
            }
        }

        public static class CreatedBean {
            /**
             * value : 2018-08-08T21:50:29+00:00
             * format : Y-m-d\TH:i:sP
             */

            private String value;
            private String format;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }
        }

        public static class ChangedBean {
            /**
             * value : 2018-08-31T08:26:34+00:00
             * format : Y-m-d\TH:i:sP
             */

            private String value;
            private String format;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }
        }

        public static class AccessBean {
            /**
             * value : 2018-08-31T22:59:15+00:00
             * format : Y-m-d\TH:i:sP
             */

            private String value;
            private String format;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }
        }

        public static class LoginBean {
            /**
             * value : 2018-08-27T10:48:54+00:00
             * format : Y-m-d\TH:i:sP
             */

            private String value;
            private String format;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getFormat() {
                return format;
            }

            public void setFormat(String format) {
                this.format = format;
            }
        }

        public static class DefaultLangcodeBean {
            /**
             * value : true
             */

            private boolean value;

            public boolean isValue() {
                return value;
            }

            public void setValue(boolean value) {
                this.value = value;
            }
        }

        public static class FieldPersonalpicshowBean {
            /**
             * target_id : 22
             * alt : alternatice text 1
             * title :
             * width : 750
             * height : 1334
             * target_type : file
             * target_uuid : 774e7120-13c6-47eb-899f-433cc71dddcf
             * url : http://www.uaes.site:8088/d86/sites/default/files/2018-08/98464533.jpg
             */

            private int target_id;
            private String alt;
            private String title;
            private int width;
            private int height;
            private String target_type;
            private String target_uuid;
            private String url;

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_uuid() {
                return target_uuid;
            }

            public void setTarget_uuid(String target_uuid) {
                this.target_uuid = target_uuid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class FieldUserAgeBean {
            /**
             * value : 18
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class FieldUserGameLevelBean {
            /**
             * value : 15
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserGamenameBean {
            /**
             * value : 王者荣耀
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserGenderBean {
            /**
             * value : 女
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserIsvipBean {
            /**
             * value : true
             */

            private boolean value;

            public boolean isValue() {
                return value;
            }

            public void setValue(boolean value) {
                this.value = value;
            }
        }

        public static class FieldUserLevelBean {
            /**
             * value : 16.00
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserMatchesBean {
            /**
             * value : 46
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class FieldUserMobileBean {
            /**
             * value : 13812340001
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserNicknameBean {
            /**
             * value : 会飞的毛毛虫
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserPlatformBean {
            /**
             * value : 安卓微信
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserPointBean {
            /**
             * value : 100
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class FieldUserStarsBean {
            /**
             * value : 20
             */

            private int value;

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }
        }

        public static class FieldUserStarsignBean {
            /**
             * value : 白羊座
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserStatementBean {
            /**
             * value : 爱笑的女孩运气都不会太差呦
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserVoiceBean {
            /**
             * target_id : 1
             * target_type : media
             * target_uuid : ac7825f3-88ed-46ac-b6e5-3207665fb413
             * url : /d86/media/1
             */

            private int target_id;
            private String target_type;
            private String target_uuid;
            private String url;

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_uuid() {
                return target_uuid;
            }

            public void setTarget_uuid(String target_uuid) {
                this.target_uuid = target_uuid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class UserPictureBean {
            /**
             * target_id : 7
             * alt :
             * title :
             * width : 48
             * height : 48
             * target_type : file
             * target_uuid : eeaac525-6d00-4462-b23a-15f30a36288e
             * url : http://www.uaes.site:8088/d86/sites/default/files/pictures/2018-08/cx_0.png
             */

            private int target_id;
            private String alt;
            private String title;
            private int width;
            private int height;
            private String target_type;
            private String target_uuid;
            private String url;

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_uuid() {
                return target_uuid;
            }

            public void setTarget_uuid(String target_uuid) {
                this.target_uuid = target_uuid;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
