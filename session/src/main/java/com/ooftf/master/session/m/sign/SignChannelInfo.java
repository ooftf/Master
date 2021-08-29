package com.ooftf.master.session.m.sign;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/19 0019
 */
public class SignChannelInfo {
    public SignChannelInfo() {

    }

    public SignChannelInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
