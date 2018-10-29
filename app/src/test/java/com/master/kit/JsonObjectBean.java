package com.master.kit;


import org.json.JSONObject;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/29 0029
 */
public class JsonObjectBean {
    private String command;
    private JSONObject param;
    private String callbackId;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public JSONObject getParam() {
        return param;
    }

    public void setParam(JSONObject param) {
        this.param = param;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }
}
