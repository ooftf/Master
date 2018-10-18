package com.ooftf.service.engine.pigeon;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ooftf
 * @date 2018/9/9 0009
 * @desc
 **/
public class Pigeon {
    static Map<String, Service> serviceHolder = new HashMap<>();

    static void post(final String address, final String param, Response response) {
        Service service = serviceHolder.get(address);
        if (service == null) {
            service.handleRequest(param, response);
        } else {
            response.onServiceNoFind();
        }
    }

    static void registerService(String url, Service service) {
        serviceHolder.put(url, service);
    }

    static void unRegisterService(Service service) {
        serviceHolder.remove(service);
    }


    interface Service {
        /**
         * 处理请求
         *
         * @param params
         * @param response
         */
        void handleRequest(String params, Response response);
    }


    interface Response {
        void onSuccess(String responseBody);

        void onFail(String message);

        void onServiceNoFind();
    }
}
