package com.master.kit;


import com.alibaba.fastjson.JSONObject;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * JsMessage简介
 *
 * @author lihang
 * @date 2018-10-15 20:49:12
 */
public class JsMessage {
  /*  *//**
     * nativeId
     *//*
    private String nativeId;*/
    /**
     * command
     */
    private String command;
    /**
     * param
     */
    private JSONObject param;
    /**
     * callbackId
     */
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
