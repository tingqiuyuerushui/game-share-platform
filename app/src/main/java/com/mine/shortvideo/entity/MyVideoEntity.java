package com.mine.shortvideo.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.mine.shortvideo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：created by lun.zhang on 9/3/2018 18:15
 * 邮箱：zhanglun_study@163.com
 */
public class MyVideoEntity implements Parcelable {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * title : <a href="/d86/node/36" hreflang="en">sample video test</a>
         * field_media_video_file :
         <span class="file file--mime-video-mp4 file--video"> <a href="http://www.uaes.site:8088/d86/sites/default/files/2018-09/123.mp4" type="video/mp4; length=1989393">123.mp4</a></span>

         * field_media_oembed_video :
         */

        private String title;
        private String nid;
        private String field_media_video_file;
        private String field_media_oembed_video;

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

        public String getNid() {
            return nid;
        }

        public void setNid(String nid) {
            this.nid = nid;
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

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.nid);
            dest.writeString(this.field_media_video_file);
            dest.writeString(this.field_media_oembed_video);
        }

        protected DataBean(Parcel in) {
            this.title = in.readString();
            this.nid = in.readString();
            this.field_media_video_file = in.readString();
            this.field_media_oembed_video = in.readString();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public MyVideoEntity() {
    }

    protected MyVideoEntity(Parcel in) {
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<MyVideoEntity> CREATOR = new Parcelable.Creator<MyVideoEntity>() {
        @Override
        public MyVideoEntity createFromParcel(Parcel source) {
            return new MyVideoEntity(source);
        }

        @Override
        public MyVideoEntity[] newArray(int size) {
            return new MyVideoEntity[size];
        }
    };
}
