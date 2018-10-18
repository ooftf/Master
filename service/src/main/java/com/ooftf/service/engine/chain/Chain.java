package com.ooftf.service.engine.chain;

import java.io.IOException;
import java.util.List;

/**
 * @author ooftf
 * @date 2018/9/20 0020
 * @desc
 **/
public class Chain {
    List<Interceptor> interceptors;
    int index;
    Request request;

    Chain(List<Interceptor> interceptors, int index, Request request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    Request request() {
        return request;
    }

    Response proceed(Request request) throws IOException {
        return interceptors.get(index).intercept(new Chain(interceptors, index + 1, request));
    }

}
