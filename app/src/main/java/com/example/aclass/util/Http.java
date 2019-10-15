package com.example.aclass.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Http {
    public  static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client =new OkHttpClient();
        Request request =new Request.Builder().build();
        client.newCall(request).enqueue(callback);
    }
}
