package com.jeplin.hackathonqm.RetroFit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jeplin on 23-09-2017.
 */

public class ApiClient {

    private static Retrofit retrofit=null;

    public static Retrofit getClient(String baseUrl){
        if (retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
