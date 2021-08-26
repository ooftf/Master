package com.ooftf.master.session.net;

import com.ooftf.mapping.lib.IResponse;

import org.jetbrains.annotations.NotNull;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class MobBaseBean implements IResponse {
    public static String success = "0";
    private String errorMsg;
    private String errorCode;

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String msg) {
        this.errorMsg = msg;
    }
    public String getRetCode() {
        return errorCode;
    }

    public void setRetCode(String retCode) {
        this.errorCode = retCode;
    }

    @NotNull
    @Override
    public String getMessage() {
        return errorMsg;
    }

    @Override
    public boolean isSuccess() {
        return success.equals(errorCode);
    }

    @Override
    public boolean isTokenError() {
        return false;
    }
}
