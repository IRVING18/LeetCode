package com.jiejiedai.api.utils;

import com.jiejiedai.api.client.ClientManager;
import com.jiejiedai.api.client.JieClient;
import net.sf.json.JSONObject;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone","181489541**");

        try {
            request("",map,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String request(String method, Map<String, Object> bizData, boolean dataEnc) throws Exception {
        ClientManager clientManager = new ClientManager();
        JieClient jieClient = clientManager.createClient();

        Map<String, String> params = new HashMap<String, String>();
        String data = JSONObject.fromObject(bizData).toString();
//		String data = "{\"amount\":9,\"carInfo\":3,\"cashIncome\":11500,\"cityName\":\"深圳市\",\"customerIp\":\"200.21.22.23\",\"houseInfo\":2,\"identityType\":1,\"isHasParticle\":1,\"isHasPublicFund\":1,\"phone\":\"15012706451\",\"sex\":1,\"source\":\"yzc\",\"username\":\"测试\",\"workerWagesPay\":2}";
        data = JSONObject.fromObject(data).toString();
        if (dataEnc) {
            PrivateKey pk = RSAUtils.getPrivateKey(jieClient.getPriviateKey());
            data = RSAUtils.encrypt(data, pk);
        }
        params.put("data", data);
        System.out.println("wzzzzzz      "+data);
        params.put("method", "jjd.api.phone.match");
        params.put("dataEnc", dataEnc ? "1" : "0");


        return jieClient.execute(params);
    }

    public static String request(String method, Map<String, Object> bizData) throws Exception {
        return request(method, bizData, false);
    }

//    OpK2mXzoqSE7EPHyTH+bsmGzLkVPRe9hOd+cBX/8tEMLolvTse+zzbaRpn0E3bQwTrJj+G0u1ZfTVVyHJLHyy2s/MVkrJ6M52ExTD2D6RStyaVeA45KD+fwsWmNMEZiIIsfwONsgWhYI4RLzpq2M4bO03IBX6mVp7mK5EP0pkyk=
//
//    OpK2mXzoqSE7EPHyTH+bsmGzLkVPRe9hOd+cBX/8tEMLolvTse+zzbaRpn0E3bQwTrJj+G0u1ZfTVVyHJLHyy2s/MVkrJ6M52ExTD2D6RStyaVeA45KD+fwsWmNMEZiIIsfwONsgWhYI4RLzpq2M4bO03IBX6mVp7mK5EP0pkyk=
}
