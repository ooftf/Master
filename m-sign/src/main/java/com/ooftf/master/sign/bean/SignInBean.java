package com.ooftf.master.sign.bean;


import com.ooftf.master.session.net.MobBaseBean;

import java.util.ArrayList;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
public class SignInBean extends MobBaseBean {

    /**
     * result : {"token":"b865e754ea06cf6f2d0d29e5e098b381","uid":"685754b464dc6f4fb8c287f8a638885952772aab"}
     */
 /*   {
        "data": {
        "admin": false,
                "chapterTops": [],
        "coinCount": 21,
                "collectIds": [
        19550,
                18930
        ],
        "email": "",
                "icon": "",
                "id": 108837,
                "nickname": "ooftf",
                "password": "",
                "publicName": "ooftf",
                "token": "",
                "type": 0,
                "username": "ooftf"
    },
        "errorCode": 0,
            "errorMsg": ""
    }*/

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : b865e754ea06cf6f2d0d29e5e098b381
         * uid : 685754b464dc6f4fb8c287f8a638885952772aab
         */

        private String token;
        private String username;
        private boolean admin;
        private int coinCount;
        private ArrayList<Integer> collectIds = new ArrayList();
        private String  email;
        private String icon;
        private String id;
        private String nickname;
        private String password;
        private String publicName;
        private int  type;
        public String getUsername() {
            return username;
        }

        public DataBean setUsername(String username) {
            this.username = username;
            return this;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public ArrayList<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(ArrayList<Integer> collectIds) {
            this.collectIds = collectIds;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
