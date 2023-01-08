package com.jiejiedai.api.leetcode.chains;

import java.util.List;

public class RealChains implements Interceptor.Chain {
    Request request;
    static List<Interceptor> interceptors;
    int index;
    RealChains(int index){
        this.index = index;
    }
    @Override
    public Request requset() {
        return request;
    }

    @Override
    public Response process(Request request) {
        RealChains realChains = new RealChains(index + 1);
        Interceptor interceptor = interceptors.get(index);
        Response response = interceptor.intercept(realChains);
        return response;
    }

    @Override
    public String connection() {
        return "response";
    }
}
