package com.jiejiedai.api.leetcode.chains;

public class LastInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) {
        Request requset = chain.requset();
        //发送请求
        chain.connection();
        return new Response();
    }
}
