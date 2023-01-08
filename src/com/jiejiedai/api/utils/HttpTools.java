package com.jiejiedai.api.utils;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpTools {
	public static String post(String url,Map<String,String> params,int connectTimeout,int readTimeOut){
		if(params==null || params.isEmpty()){
			return "";
		}
		CloseableHttpClient httpClient = null;
		
		HttpPost httpPost = new HttpPost(url);
		try{
			httpClient = HttpClients.createDefault();
			List<NameValuePair> httpParams = new ArrayList<NameValuePair>();
			for(Iterator<Entry<String, String>> it=params.entrySet().iterator();it.hasNext();){
				Entry<String,String> entry = it.next();
				httpParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(httpParams);
			httpPost.setEntity(entity);
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)
																.setSocketTimeout(readTimeOut)
																.setStaleConnectionCheckEnabled(true)
																.build();
			httpPost.setConfig(requestConfig);
			
			CloseableHttpResponse response = null;
			try{
				response = httpClient.execute(httpPost);
			}catch(Exception e){
				for (int i = 0; i < 2; i++) {
                    try {
                        response = httpClient.execute(httpPost);
                        break;
                    } catch (Exception e2) {
                        if(i == 1) {
                            throw e2;
                        } else {
                            continue;
                        }
                    }
                }
			}
			if(response==null){
				return "";
			}else{
				if(response.getStatusLine().getStatusCode()>=400){
					
				}
			}
			HttpEntity httpEntity = response.getEntity();
			if(httpEntity != null){
				return EntityUtils.toString(httpEntity, "UTF-8");
			}
		} catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(httpPost != null) {
            	httpPost.releaseConnection();
            }
        }
		return "";
	}
}
