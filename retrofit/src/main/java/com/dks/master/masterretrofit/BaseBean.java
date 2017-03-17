package com.dks.master.masterretrofit;

/**
 * Created by master on 2017/2/23.
 */

public class BaseBean {

    /**
     * time : 1487920329011
     * success : true
     * code :
     * info :
     */

    private long time;
    private boolean success;
    private String code;
    private String info;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
