package com.onlylemi.test3_view;

import com.google.gson.Gson;
import com.onlylemi.test3_view.entity.Paramz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttpUtils
 *
 * @author: onlylemi
 * @time: 2016-06-07 17:32
 */
public class OKHttpUtils {

    private OkHttpClient client;

    public OKHttpUtils() {
        client = new OkHttpClient().newBuilder().build();
    }

    public void parserJson(String jsonpath) {
        final Request request = new Request.Builder().url(jsonpath).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject paramz = jsonObject.getJSONObject("paramz");
                        JSONArray feeds = paramz.getJSONArray("feeds");


                        Paramz paramz2 = new Gson().fromJson(json, Paramz.class);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
