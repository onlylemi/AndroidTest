package com.onlylemi.test4_lizhi.json;

import com.google.gson.Gson;
import com.onlylemi.test4_lizhi.entity.Paramz;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * JsonUtils
 *
 * @author: onlylemi
 * @time: 2016-06-08 9:33
 */
public class JsonUtils {

    private OkHttpClient client;

    public JsonUtils() {
        client = new OkHttpClient().newBuilder().build();
    }

    public Observable<Paramz> getJsonParamz(final String url) {

        return Observable.create(new Observable.OnSubscribe<Paramz>() {
            @Override
            public void call(final Subscriber<? super Paramz> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                Paramz paramz = new Gson().fromJson(response.body().string(),
                                        Paramz.class);
                                if (null != paramz) {
                                    subscriber.onNext(paramz);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
