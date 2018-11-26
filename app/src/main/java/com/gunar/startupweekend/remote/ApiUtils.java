package com.gunar.startupweekend.remote;

public class ApiUtils {
    public static String BASE_URL = "http://192.168.43.80:8000/";

    public static ApiService getApiServices(){
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }

    public static void changeApiBaseUrl(String newApiBaseUrl) {
        BASE_URL = newApiBaseUrl;
    }

}
