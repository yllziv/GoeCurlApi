package com.yanglonglong;

import com.google.gson.Gson;

public class GsonTest {
    public static void main(String[] args) {
        People p = new People();
        p.setAge(20);
        p.setName("People");
        p.setSetName(true);
        Gson gson = new Gson();
        System.out.println(gson.toJson(p));
    }
}
