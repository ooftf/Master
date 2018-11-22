package com.master.kit;

import com.alibaba.fastjson.JSON;

import org.junit.Test;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/29 0029
 */
public class JsonObjectTest {
    @Test
    public void testFromJson(){
        String json = "{\"command\":\"showShareFunctionButtons\",\"param\":{\"imageUrl\":\"https://img30.360buyimg.com/popXue/jfs/t1/5192/16/11086/76964/5bcd9028E63b78ce2/83232b8b7470f7e4.dpg\"},\"callbackId\":\"cb_2_1540808595939\"}";
        JsonObjectBean jsonObjectBean = JSON.parseObject(json, JsonObjectBean.class);
        JsMessage jsMessage = JSON.parseObject(json, JsMessage.class);
        System.out.print(jsonObjectBean.getParam());
        System.out.print(jsMessage.getParam());
    }
}
