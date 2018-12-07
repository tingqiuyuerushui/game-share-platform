package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 12/7/2018 10:20
 * 邮箱：zhanglun_study@163.com
 */
public class AllGoodsEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 绝地枪模吃鸡游戏周边玩具空投落地成盒木盒六件套礼品摆件
         * field_c_goods_price : 228.00
         * field_c_goods_code : 7
         * field_c_goods_cover : /sites/default/files/2018-12/5_9.jpg
         */

        private String title;
        private String field_c_goods_price;
        private String field_c_goods_code;
        private String field_c_goods_cover;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField_c_goods_price() {
            return field_c_goods_price;
        }

        public void setField_c_goods_price(String field_c_goods_price) {
            this.field_c_goods_price = field_c_goods_price;
        }

        public String getField_c_goods_code() {
            return field_c_goods_code;
        }

        public void setField_c_goods_code(String field_c_goods_code) {
            this.field_c_goods_code = field_c_goods_code;
        }

        public String getField_c_goods_cover() {
            return field_c_goods_cover;
        }

        public void setField_c_goods_cover(String field_c_goods_cover) {
            this.field_c_goods_cover = field_c_goods_cover;
        }
    }
}
