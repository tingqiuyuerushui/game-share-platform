package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/27/2018 15:47
 * 邮箱：zhanglun_study@163.com
 */
public class RegistResultEntity {

    /**
     * data : {"uid":[{"value":13}],"uuid":[{"value":"676b9fbd-99db-465a-9120-8bccd9efb3e0"}],"langcode":[{"value":"en"}],"preferred_langcode":[{"value":"en"}],"preferred_admin_langcode":[],"name":[{"value":"17839997706"}],"mail":[],"timezone":[{"value":"Asia/Shanghai"}],"status":[{"value":true}],"created":[{"value":"2018-08-27T07:50:06+00:00","format":"Y-m-d\\TH:i:sP"}],"changed":[{"value":"2018-08-27T07:50:06+00:00","format":"Y-m-d\\TH:i:sP"}],"access":[{"value":"1970-01-01T00:00:00+00:00","format":"Y-m-d\\TH:i:sP"}],"login":[{"value":"1970-01-01T00:00:00+00:00","format":"Y-m-d\\TH:i:sP"}],"init":[],"roles":[],"default_langcode":[{"value":true}],"field_personalpicshow":[],"field_user_age":[],"field_user_game_level":[{"value":"青铜1星"}],"field_user_game_platform":[{"value":"安卓QQ"}],"field_user_gamename":[{"value":"王者荣耀"}],"field_user_gender":[],"field_user_isvip":[{"value":false}],"field_user_level":[],"field_user_location":[],"field_user_matches":[],"field_user_mobile":[{"value":"17839997702"}],"field_user_nickname":[{"value":"舶域阁丫"}],"field_user_platform":[],"field_user_point":[],"field_user_stars":[],"field_user_starsign":[],"field_user_statement":[],"field_user_tags":[],"field_user_voice":[],"user_picture":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UidBean> uid;
        private List<UuidBean> uuid;
        private List<LangcodeBean> langcode;
        private List<PreferredLangcodeBean> preferred_langcode;
        private List<?> preferred_admin_langcode;
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
        private List<?> field_personalpicshow;
        private List<?> field_user_age;
        private List<FieldUserGameLevelBean> field_user_game_level;
        private List<FieldUserGamePlatformBean> field_user_game_platform;
        private List<FieldUserGamenameBean> field_user_gamename;
        private List<?> field_user_gender;
        private List<FieldUserIsvipBean> field_user_isvip;
        private List<?> field_user_level;
        private List<?> field_user_location;
        private List<?> field_user_matches;
        private List<FieldUserMobileBean> field_user_mobile;
        private List<FieldUserNicknameBean> field_user_nickname;
        private List<?> field_user_platform;
        private List<?> field_user_point;
        private List<?> field_user_stars;
        private List<?> field_user_starsign;
        private List<?> field_user_statement;
        private List<?> field_user_tags;
        private List<?> field_user_voice;
        private List<?> user_picture;

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

        public List<?> getPreferred_admin_langcode() {
            return preferred_admin_langcode;
        }

        public void setPreferred_admin_langcode(List<?> preferred_admin_langcode) {
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

        public List<?> getField_personalpicshow() {
            return field_personalpicshow;
        }

        public void setField_personalpicshow(List<?> field_personalpicshow) {
            this.field_personalpicshow = field_personalpicshow;
        }

        public List<?> getField_user_age() {
            return field_user_age;
        }

        public void setField_user_age(List<?> field_user_age) {
            this.field_user_age = field_user_age;
        }

        public List<FieldUserGameLevelBean> getField_user_game_level() {
            return field_user_game_level;
        }

        public void setField_user_game_level(List<FieldUserGameLevelBean> field_user_game_level) {
            this.field_user_game_level = field_user_game_level;
        }

        public List<FieldUserGamePlatformBean> getField_user_game_platform() {
            return field_user_game_platform;
        }

        public void setField_user_game_platform(List<FieldUserGamePlatformBean> field_user_game_platform) {
            this.field_user_game_platform = field_user_game_platform;
        }

        public List<FieldUserGamenameBean> getField_user_gamename() {
            return field_user_gamename;
        }

        public void setField_user_gamename(List<FieldUserGamenameBean> field_user_gamename) {
            this.field_user_gamename = field_user_gamename;
        }

        public List<?> getField_user_gender() {
            return field_user_gender;
        }

        public void setField_user_gender(List<?> field_user_gender) {
            this.field_user_gender = field_user_gender;
        }

        public List<FieldUserIsvipBean> getField_user_isvip() {
            return field_user_isvip;
        }

        public void setField_user_isvip(List<FieldUserIsvipBean> field_user_isvip) {
            this.field_user_isvip = field_user_isvip;
        }

        public List<?> getField_user_level() {
            return field_user_level;
        }

        public void setField_user_level(List<?> field_user_level) {
            this.field_user_level = field_user_level;
        }

        public List<?> getField_user_location() {
            return field_user_location;
        }

        public void setField_user_location(List<?> field_user_location) {
            this.field_user_location = field_user_location;
        }

        public List<?> getField_user_matches() {
            return field_user_matches;
        }

        public void setField_user_matches(List<?> field_user_matches) {
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

        public List<?> getField_user_platform() {
            return field_user_platform;
        }

        public void setField_user_platform(List<?> field_user_platform) {
            this.field_user_platform = field_user_platform;
        }

        public List<?> getField_user_point() {
            return field_user_point;
        }

        public void setField_user_point(List<?> field_user_point) {
            this.field_user_point = field_user_point;
        }

        public List<?> getField_user_stars() {
            return field_user_stars;
        }

        public void setField_user_stars(List<?> field_user_stars) {
            this.field_user_stars = field_user_stars;
        }

        public List<?> getField_user_starsign() {
            return field_user_starsign;
        }

        public void setField_user_starsign(List<?> field_user_starsign) {
            this.field_user_starsign = field_user_starsign;
        }

        public List<?> getField_user_statement() {
            return field_user_statement;
        }

        public void setField_user_statement(List<?> field_user_statement) {
            this.field_user_statement = field_user_statement;
        }

        public List<?> getField_user_tags() {
            return field_user_tags;
        }

        public void setField_user_tags(List<?> field_user_tags) {
            this.field_user_tags = field_user_tags;
        }

        public List<?> getField_user_voice() {
            return field_user_voice;
        }

        public void setField_user_voice(List<?> field_user_voice) {
            this.field_user_voice = field_user_voice;
        }

        public List<?> getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(List<?> user_picture) {
            this.user_picture = user_picture;
        }

        public static class UidBean {
            /**
             * value : 13
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
             * value : 676b9fbd-99db-465a-9120-8bccd9efb3e0
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

        public static class NameBean {
            /**
             * value : 17839997706
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
             * value : 2018-08-27T07:50:06+00:00
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
             * value : 2018-08-27T07:50:06+00:00
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
             * value : 1970-01-01T00:00:00+00:00
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
             * value : 1970-01-01T00:00:00+00:00
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

        public static class FieldUserGameLevelBean {
            /**
             * value : 青铜1星
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }

        public static class FieldUserGamePlatformBean {
            /**
             * value : 安卓QQ
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

        public static class FieldUserIsvipBean {
            /**
             * value : false
             */

            private boolean value;

            public boolean isValue() {
                return value;
            }

            public void setValue(boolean value) {
                this.value = value;
            }
        }

        public static class FieldUserMobileBean {
            /**
             * value : 17839997702
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
             * value : 舶域阁丫
             */

            private String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
