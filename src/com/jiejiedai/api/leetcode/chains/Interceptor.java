package com.jiejiedai.api.leetcode.chains;

public interface Interceptor {
    Response intercept(Chain chain);

    interface Chain {
        Request requset();
        Response process(Request request);
        String connection();
    }
}
