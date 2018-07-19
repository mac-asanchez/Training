package com.example.user.githubrest.client;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String AUTH_TOKEN = "5dfb8b79de82d2dbd5961c6c14907961e1b9f5e5";

    @Override
    public Response intercept(Chain chain)
            throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader("Authenticator", AUTH_TOKEN)
                .build();

        Response response = chain.proceed(request);
        return response;
    }
}