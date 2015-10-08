package com.yanglonglong;

import net.sf.json.JSONObject;

public class studyJson {
    public static void stringToJson(String jsonStr) {
        System.out.println("json字符串转json对象");
        jsonStr = "{'password':'123456','username':'张三'}";
        JSONObject jsonObj = JSONObject.fromString(jsonStr);
        String username = jsonObj.getString("username");
        String password = jsonObj.optString("password");
        System.out.println("username=" + username
                + "\t password=" + password);
        System.out.println(jsonObj.toString());
    }
    public static void main(String[] args){
        stringToJson("");
    }
}
