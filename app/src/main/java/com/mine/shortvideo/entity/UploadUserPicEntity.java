package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 9/7/2018 15:29
 * 邮箱：zhanglun_study@163.com
 */
public class UploadUserPicEntity {

    private List<UidBean> uid;
    private List<FieldPersonalpicshowBean> field_personalpicshow;

    public List<UidBean> getUid() {
        return uid;
    }

    public void setUid(List<UidBean> uid) {
        this.uid = uid;
    }

    public List<FieldPersonalpicshowBean> getField_personalpicshow() {
        return field_personalpicshow;
    }

    public void setField_personalpicshow(List<FieldPersonalpicshowBean> field_personalpicshow) {
        this.field_personalpicshow = field_personalpicshow;
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

    public static class FieldPersonalpicshowBean {
        /**
         * target_id : 22
         * alt : alternatice text 1
         * url : http://uaes.site:8088/d86/sites/default/files/2018-08/98464533.jpg
         */

        private int target_id;
        private String alt;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
