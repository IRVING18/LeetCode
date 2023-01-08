package com.jiejiedai.api;


import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InputParamSign {
    public static void main(String[] args) {
        String sysSecret = "0a091b3aa4324435aab703142518a8f7";
        String sign = Sign(sysSecret);
        System.out.println(sign);
    }

    public static String Sign(String sysSecret) {
        Map<String, String> map = new HashMap<String, String>();
//        $params = [
//        "order_id" =>"19072426795315681646",
//                "charge_finish_time" =>"2019-07-24 16:46:14",
//                "customer_order_no" =>"535126603",
//                "order_status" =>"success",
//                "recharge_description" =>"充值成功",
//                "product_id" =>"10000497",
//                "price" =>"1.0000",
//                "buy_num" =>"2",
//                "operator_serial_number" =>"",
//    ];
        map.put("order_id", "19072426795315681646");
        map.put("charge_finish_time", "2019-07-24 16:46:14");
        map.put("customer_order_no", "535126603");
        map.put("order_status", "success");
        map.put("recharge_description", "充值成功");
        map.put("product_id", "10000497");
        map.put("price", "1.0000");
        map.put("buy_num", "2");
        map.put("operator_serial_number", "");

        JSONObject resultJson = JSONObject.fromObject(map);


        System.out.println(resultJson.toString());
        char[] s = resultJson.toString().toCharArray();
        Arrays.sort(s);

        System.out.println(s);

        System.out.println(new String(s));

        String outputSignOriginalStr = new String(s) + sysSecret;
        String sign = MD5Utils.MD5(outputSignOriginalStr);
        return sign;
    }

//    {"order_id":"19072426795315681646","charge_finish_time":"2019-07-24 16:46:14","customer_order_no":"535126603","order_status":"success","recharge_description":"\u5145\u5145\u503c\u6210\u529f","product_id":"10000497","price":"1.0000","buy_num":"2","operator_serial_number":""}
}