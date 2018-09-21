package com.mine.shortvideo.entity;

/**
 * 作者：created by lun.zhang on 9/21/2018 09:22
 * 邮箱：zhanglun_study@163.com
 */
public class LikeCollectEntity {

    /**
     * entity_id : 59
     * entity_type : node
     * flag_id : like
     * my_uid : 1
     * flag_action : flag
     */

    private int entity_id;
    private String entity_type;
    private String flag_id;
    private int my_uid;
    private String flag_action;

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getFlag_id() {
        return flag_id;
    }

    public void setFlag_id(String flag_id) {
        this.flag_id = flag_id;
    }

    public int getMy_uid() {
        return my_uid;
    }

    public void setMy_uid(int my_uid) {
        this.my_uid = my_uid;
    }

    public String getFlag_action() {
        return flag_action;
    }

    public void setFlag_action(String flag_action) {
        this.flag_action = flag_action;
    }
}
