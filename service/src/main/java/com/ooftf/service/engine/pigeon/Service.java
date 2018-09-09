package com.ooftf.service.engine.pigeon;

import io.reactivex.Observable;

/**
 * @author ooftf
 * @date 2018/9/10 0010
 * @desc
 **/
public interface Service {

    /**
     * @param params
     * @return
     */
    Observable<String> handleRequest(String params);
}