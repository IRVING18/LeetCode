package com.jiejiedai.api;

import org.apache.http.util.TextUtils;

import java.util.*;

public class ChannelServiceSign {


    public static String sign(HashMap<String, String> map) {
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        // 构造签名键值对的格式
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : infoIds) {
            if (!TextUtils.isEmpty(item.getKey())) {
                String key = item.getKey();
                String val = item.getValue();
                if (!TextUtils.isEmpty(val)) {
                    sb.append(key).append("=").append(val).append("&");
                }
            }

        }
        String result = sb.toString();
        result = result.substring(0, result.length() - 1);

        return MD5Utils.MD5(result);
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("app_id", "173");
        map.put("order_no", "2202694572");
        map.put("timestamp", "1592215986");
        map.put("biz_phone", "nAmlu8IWYp1LbRXNeFYIxQ==");
        String sign = sign(map);
        System.out.println(sign);
    }
}
