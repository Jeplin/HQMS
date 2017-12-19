package com.jeplin.hackathonqm.RetroFit;

/**
 * Created by jeplin on 23-09-2017.
 */

public class UserService {

    //private static String BASE_URL="http://localhost:8000/";
    private static String BASE_URL="http://st9.idsil.com/hqms/public/";
//    private static String BASE_URL="https://
// .typicode.com/";

    public static ApiInterface getApiClient(){
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
