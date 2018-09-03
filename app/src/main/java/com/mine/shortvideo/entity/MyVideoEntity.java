package com.mine.shortvideo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 作者：created by lun.zhang on 9/3/2018 18:15
 * 邮箱：zhanglun_study@163.com
 */
public class MyVideoEntity implements Parcelable{


    private List<DataBean> data;

    protected MyVideoEntity(Parcel in) {
    }

    public static final Creator<MyVideoEntity> CREATOR = new Creator<MyVideoEntity>() {
        @Override
        public MyVideoEntity createFromParcel(Parcel in) {
            return new MyVideoEntity(in);
        }

        @Override
        public MyVideoEntity[] newArray(int size) {
            return new MyVideoEntity[size];
        }
    };

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class DataBean {
        /**
         * title : <a href="/d86/node/36" hreflang="en">sample video test</a>
         * field_media_video_file :
         <span class="file file--mime-video-mp4 file--video"> <a href="http://www.uaes.site:8088/d86/sites/default/files/2018-09/123.mp4" type="video/mp4; length=1989393">123.mp4</a></span>

         * field_media_oembed_video :
         */

        private String title;
        private String field_media_video_file;
        private String field_media_oembed_video;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getField_media_video_file() {
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
    }
}
