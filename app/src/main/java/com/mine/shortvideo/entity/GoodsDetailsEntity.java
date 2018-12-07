package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 12/7/2018 13:04
 * 邮箱：zhanglun_study@163.com
 */
public class GoodsDetailsEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title : 权利的游戏周边 铁王座模型道具可配1/12手办人偶创意手机底座
         * field_c_goods_price : 299.00
         * field_c_goods_code : 6
         * field_c_goods_cover : /sites/default/files/2018-12/1_12.jpg
         * field_c_goods_detail_pics : /sites/default/files/2018-12/TB2fCh_XYH8F1JjSszfXXXPmXXa-2328870297.jpg, /sites/default/files/2018-12/TB2gm2laxf9F1JjSZFNXXbtIVXa-2328870297.jpg, /sites/default/files/2018-12/TB2LG9zaEifF1JjSspnXXa5pVXa-2328870297.jpg, /sites/default/files/2018-12/TB2n7d4arH8F1JjSszfXXXPmXXa-2328870297.jpg, /sites/default/files/2018-12/TB2r1aPawAEF1JjSZFLXXbzNXXa-2328870297.jpg, /sites/default/files/2018-12/TB2rx1FX5afF1Jjy1zcXXcu5XXa-2328870297.jpg, /sites/default/files/2018-12/TB2xaiwaEifF1JjSszcXXc2qpXa-2328870297.jpg, /sites/default/files/2018-12/TB2ZVeAatqgF1JjSsppXXaBNXXa-2328870297.jpg, /sites/default/files/2018-12/TB251OAayafF1Jjy1zcXXcu5XXa-2328870297.jpg
         * field_c_good_main_pics : /sites/default/files/2018-12/1_11.jpg, /sites/default/files/2018-12/2_13.jpg
         */

        private String title;
        private String field_c_goods_price;
        private String field_c_goods_code;
        private String field_c_goods_cover;
        private String field_c_goods_detail_pics;
        private String field_c_good_main_pics;

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

        public String getField_c_goods_detail_pics() {
            return field_c_goods_detail_pics;
        }

        public void setField_c_goods_detail_pics(String field_c_goods_detail_pics) {
            this.field_c_goods_detail_pics = field_c_goods_detail_pics;
        }

        public String getField_c_good_main_pics() {
            return field_c_good_main_pics;
        }

        public void setField_c_good_main_pics(String field_c_good_main_pics) {
            this.field_c_good_main_pics = field_c_good_main_pics;
        }
    }
}
