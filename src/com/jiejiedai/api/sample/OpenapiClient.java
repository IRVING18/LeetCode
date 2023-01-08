package com.jiejiedai.api.sample;

import com.jiejiedai.api.request.OpenapiRequest;
import com.jiejiedai.api.utils.RequestUtil;
import net.sf.json.JSONObject;

import java.net.URLEncoder;

public class OpenapiClient {
	
	OpenapiRequest openapiRequest = new OpenapiRequest();
	private String method;
	private boolean dataEnc = false;
	
	public JSONObject execute() throws Exception{
		String result = RequestUtil.request(this.method, this.openapiRequest.getParams(),this.dataEnc);
		if(result==null || result.isEmpty()){
			throw new Exception("Request  interface returns null");
		}
		JSONObject json = JSONObject.fromObject(result);
		if(json==null){
			throw new Exception("Request interface got a non-json result");
        }
        return json;
	}
	
	public static void main(String[] args) throws Exception {
		OpenapiClient sample = new OpenapiClient();
		sample.setMethod("jjd.api.phone.match");
		sample.setField("phone","18148954181");
		sample.dataEnc(true);
		try {
			JSONObject result = sample.execute();
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	public void setField(String key,String value){
		this.openapiRequest.putParam(key, value);
	}
	
	public void dataEnc(boolean isEnc){
//		if(isEnc){
//			this.openapiRequest.putParam("dataEnc", "1");
//		}else{
//			this.openapiRequest.putParam("dataEnc", "0");
//		}
		this.dataEnc = isEnc;
	}
}
