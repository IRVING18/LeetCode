package com.jiejiedai.api.client.impl;

import com.jiejiedai.api.client.JieClient;
import com.jiejiedai.api.utils.CommonUtil;
import com.jiejiedai.api.utils.HttpTools;
import com.jiejiedai.api.utils.RSAUtils;

import java.util.Date;
import java.util.Map;

public class DefaultJieClient implements JieClient {
    private String serverUrl = "";
    //修改真实的私钥,请使用pkcs8的密钥
    private String privateKey = "";
    private String uid = "";
    private String format = "json";
    private String signType = "RSA";
    private int connectTimeOut = 5000;
    private int readTimeOut = 15000;

    public DefaultJieClient(String url, String privateKey, String uid) {
        this.serverUrl = url;
        this.uid = uid;
        this.privateKey = privateKey;
    }

    public DefaultJieClient() {

    }

    @Override
    public String execute(Map<String, String> params) throws Exception {
        params.put("uid", this.uid);
        params.put("signType", this.signType);
        params.put("format", this.format);
        params.put("timestamp", String.valueOf(new Date().getTime()/1000));
        String paramsStr = CommonUtil.getSortParams(params);

        System.out.println("wzzzzzz   "+paramsStr);



        String sign = RSAUtils.sign(paramsStr, RSAUtils.getPrivateKey(privateKey));

        System.out.println("wzzzzzz   "+sign);
        params.put("sign", sign);


        System.out.println(params);
        String result = HttpTools.post(this.serverUrl, params, this.connectTimeOut, this.readTimeOut);

        System.out.println("wzzzzzz   "+result);

        return result;
    }

	@Override
	public String getPriviateKey() {
		return this.privateKey;
	}

}
