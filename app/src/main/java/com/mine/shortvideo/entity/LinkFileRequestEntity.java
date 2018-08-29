package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/29/2018 15:52
 * 邮箱：zhanglun_study@163.com
 */
public class LinkFileRequestEntity {

    private List<TypeBean> type;
    private List<TitleBean> title;
    private List<FieldHeroImageBean> field_hero_image;

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public List<TitleBean> getTitle() {
        return title;
    }

    public void setTitle(List<TitleBean> title) {
        this.title = title;
    }

    public List<FieldHeroImageBean> getField_hero_image() {
        return field_hero_image;
    }

    public void setField_hero_image(List<FieldHeroImageBean> field_hero_image) {
        this.field_hero_image = field_hero_image;
    }

    public static class TypeBean {
        /**
         * value : article
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class TitleBean {
        /**
         * value : Dramallama
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class FieldHeroImageBean {
        /**
         * target_id : 345345
         * description : The most fascinating image ever!
         */

        private int target_id;
        private String description;

        public int getTarget_id() {
            return target_id;
        }

        public void setTarget_id(int target_id) {
            this.target_id = target_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
