package com.mine.shortvideo.entity;

import java.util.List;

/**
 * 作者：created by lun.zhang on 8/29/2018 15:18
 * 邮箱：zhanglun_study@163.com
 */
public class UploadFileResultEntity {

    private List<FidBean> fid;
    private List<UuidBean> uuid;
    private List<LangcodeBean> langcode;
    private List<UidBean> uid;
    private List<FilenameBean> filename;
    private List<UriBean> uri;
    private List<FilemimeBean> filemime;
    private List<FilesizeBean> filesize;
    private List<StatusBean> status;
    private List<CreatedBean> created;
    private List<ChangedBean> changed;

    public List<FidBean> getFid() {
        return fid;
    }

    public void setFid(List<FidBean> fid) {
        this.fid = fid;
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

    public List<UidBean> getUid() {
        return uid;
    }

    public void setUid(List<UidBean> uid) {
        this.uid = uid;
    }

    public List<FilenameBean> getFilename() {
        return filename;
    }

    public void setFilename(List<FilenameBean> filename) {
        this.filename = filename;
    }

    public List<UriBean> getUri() {
        return uri;
    }

    public void setUri(List<UriBean> uri) {
        this.uri = uri;
    }

    public List<FilemimeBean> getFilemime() {
        return filemime;
    }

    public void setFilemime(List<FilemimeBean> filemime) {
        this.filemime = filemime;
    }

    public List<FilesizeBean> getFilesize() {
        return filesize;
    }

    public void setFilesize(List<FilesizeBean> filesize) {
        this.filesize = filesize;
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

    public static class FidBean {
        /**
         * value : 29
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
         * value : f06c6647-b2a3-4ff2-8af8-31463f5fac8c
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

    public static class UidBean {
        /**
         * target_id : 1
         * target_type : user
         * target_uuid : c2b09ff8-345a-43fa-b9ae-68af0c9cd3ea
         * url : /d86/user/1
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

    public static class FilenameBean {
        /**
         * value : 619ec35e56b7c650.png
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class UriBean {
        /**
         * value : public://2018-08/619ec35e56b7c650.png
         * url : /d86/sites/default/files/2018-08/619ec35e56b7c650.png
         */

        private String value;
        private String url;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class FilemimeBean {
        /**
         * value : image/png
         */

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class FilesizeBean {
        /**
         * value : 3683
         */

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class StatusBean {
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

    public static class CreatedBean {
        /**
         * value : 2018-08-29T07:10:18+00:00
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
         * value : 2018-08-29T07:10:18+00:00
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
}
