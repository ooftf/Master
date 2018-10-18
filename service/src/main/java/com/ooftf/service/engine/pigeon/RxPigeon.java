package com.ooftf.service.engine.pigeon;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author ooftf
 * @date 2018/9/9 0009
 * @desc
 **/
public class RxPigeon {
    private static RxPigeon instance = new RxPigeon();
    private Map<String, Service> serviceHolder = new HashMap<>();

    private RxPigeon() {

    }

    public static RxPigeon getInstance() {
        return instance;
    }

    public Observable<String> post(final String address, final String param) {
        Service service = serviceHolder.get(address);
        if (service == null) {
            return service.handleRequest(param);
        } else {
            return Observable.error(new Exception("ServiceNoFindException"));
        }
    }

    public void registerService(String url, Service service) {
        serviceHolder.put(url, service);
    }

    public void unregisterService(String url) {
        serviceHolder.remove(url);
    }

}
