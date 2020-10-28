package com.ooftf.applet.net.bean;

import com.ooftf.master.session.net.MobBaseBean;

/**
 * Created by 99474 on 2018/1/23 0023.
 */

public class PhoneQueryBean extends MobBaseBean {

    /**
     * msg : success
     * result : {"city":"北京","cityCode":"010","mobileNumber":"1530015","operator":"电信CDMA卡","province":"北京","zipCode":"100000"}
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
         * city : 北京
         * cityCode : 010
         * mobileNumber : 1530015
         * operator : 电信CDMA卡
         * province : 北京
         * zipCode : 100000
         */

        private String city;
        private String cityCode;
        private String mobileNumber;
        private String operator;
        private String province;
        private String zipCode;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
