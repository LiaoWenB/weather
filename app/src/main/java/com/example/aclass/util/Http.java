package com.example.aclass.util;

import com.bumptech.glide.request.Request;

import okhttp3.OkHttpClient;

public class Http {
    public  static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);

    }
}
