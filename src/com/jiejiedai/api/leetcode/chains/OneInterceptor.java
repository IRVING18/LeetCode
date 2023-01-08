package com.jiejiedai.api.leetcode.chains;

public class OneInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request requset = chain.requset();
        //处理request
        return chain.process(requset);
    }
}
