package com.ooftf.master.sign.bean;


import com.ooftf.master.session.net.MobBaseBean;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class SignInBean extends MobBaseBean {

    /**
     * result : {"token":"b865e754ea06cf6f2d0d29e5e098b381","uid":"685754b464dc6f4fb8c287f8a638885952772aab"}
     */

    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * token : b865e754ea06cf6f2d0d29e5e098b381
         * uid : 685754b464dc6f4fb8c287f8a638885952772aab
         */

        private String token;
        private String uid;
        private String userName;

        public String getUserName() {
            return userName;
        }

        public ResultBean setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
