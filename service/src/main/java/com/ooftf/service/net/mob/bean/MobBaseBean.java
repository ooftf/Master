package com.ooftf.service.net.mob.bean;

import com.ooftf.mapping.lib.IResponse;

import org.jetbrains.annotations.NotNull;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class MobBaseBean implements IResponse {
    public static String success = "200";
    private String msg;
    private String retCode;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    @NotNull
    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public boolean isSuccess() {
        return success.equals(retCode);
    }

    @Override
    public boolean isTokenError() {
        return false;
    }
}
