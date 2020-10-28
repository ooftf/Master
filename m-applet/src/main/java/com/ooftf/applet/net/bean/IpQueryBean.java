package com.ooftf.applet.net.bean;

import com.ooftf.master.session.net.MobBaseBean;

/**
 * Created by 99474 on 2018/1/23 0023.
 */

public class IpQueryBean extends MobBaseBean {

    /**
     * msg : success
     * result : {"city":"上海","country":"中国","ip":"222.73.199.34","province":"上海"}
     * retCode : 200
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
         * city : 上海
         * country : 中国
         * ip : 222.73.199.34
         * province : 上海
         */

        private String city;
        private String country;
        private String ip;
        private String province;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }
}
