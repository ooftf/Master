package com.ooftf.service.engine.chain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 用来熟悉责任链设计模式
 *
 * @author ooftf
 * @date 2018/9/20 0020
 **/
public class Business {

    List<Interceptor> interceptors = new ArrayList<>();

    void addInterceptro(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    Response start(Request request) {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.addAll(this.interceptors);
        interceptors.add(chain -> new Response());
        return interceptors.get(0).intercept(new Chain(interceptors, 0, request));
    }

}
