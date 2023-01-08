package com.jiejiedai.api.client;

import java.util.Map;

/**
 * @author  gaosi
 * @version 20190326
 */
public interface JieClient {
	
	/**
	 * 执行
	 * @param params  参数
	 * @return
	 * @throws Exception
	 */
	public String execute(Map<String,String> params) throws Exception;
	
	public String getPriviateKey();
}
