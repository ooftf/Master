package com.jd.jmworkstation.jmview.jsbridge;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * JsMessage简介
 *
 * @author lihang
 * @date 2018-10-15 20:49:12
 */
public class JsMessage {
    /**
     * nativeId
     */
    private String nativeId;
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
    /**
     * name
     */
    private String name;
    /**
     * info
     */
    private JSONObject info;
    /**
     * responseData
     */
    private JSONObject responseData;

    /**
     * 解析JsMessage 数组
     *
     * @param message
     * @return
     */
    public static List<JsMessage> parseArray(String message) {
        List<JsMessage> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(message);
            for (int i = 0; i < jsonArray.length(); i++) {
                JsMessage result = parse(jsonArray.getString(i));
                list.add(result);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 解析JsMessage
     *
     * @param
     * @return
     */
    public static JsMessage parse(String message) {
        JsMessage result = new JsMessage();
        try {
            JSONObject jsonObject = new JSONObject(message);
            if (jsonObject.has("nativeId")) {
                result.setNativeId(jsonObject.getString("nativeId"));
            }
            if (jsonObject.has("command")) {
                result.setCommand(jsonObject.getString("command"));
            }
            if (jsonObject.has("param")) {
                result.setParam(jsonObject.getJSONObject("param"));
            }
            if (jsonObject.has("responseData")) {
                result.setParam(jsonObject.getJSONObject("responseData"));
            }
            if (jsonObject.has("name")) {
                result.setName(jsonObject.getString("name"));
            }
            if (jsonObject.has("info")) {
                result.setInfo(jsonObject.getJSONObject("info"));
            }
            if (jsonObject.has("callbackId")) {
                result.setCallbackId(jsonObject.getString("callbackId"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public String getCommand() {
        return command;
    }

    private void setCommand(String command) {
        this.command = command;
    }

    public JSONObject getParam() {
        return param;
    }

    private void setParam(JSONObject param) {
        this.param = param;
    }

    public String getCallbackId() {
        return callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JSONObject getInfo() {
        return info;
    }

    public void setInfo(JSONObject info) {
        this.info = info;
    }

    public JSONObject getResponseData() {
        return responseData;
    }

    public void setResponseData(JSONObject responseData) {
        this.responseData = responseData;
    }
}
