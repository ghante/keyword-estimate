package com.estimater.keywordestimate.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AutoCompleteClient {

    private static final String AUTO_COMPLETE_API = "http://completion.amazon.com/search/complete";

    public List<String> list(String keyword) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient();

        HttpUrl url = HttpUrl.parse(AUTO_COMPLETE_API).newBuilder()
                .addQueryParameter("search-alias", "aps")
                .addQueryParameter("mkt", "1")
                .addQueryParameter("q", keyword)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Response response = okHttpClient.newCall(request).execute();

        List list = new ObjectMapper().readValue(response.body().string(), List.class);
        return (List<String>) list.get(1);
    }
}
