package com.onlylemi.test6_qiongyou.json;

import android.util.Log;

import com.google.gson.Gson;
import com.onlylemi.test6_qiongyou.entity.DetailEntity;
import com.onlylemi.test6_qiongyou.entity.StateEntity;

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
 * @time: 2016-06-14 15:45
 */
public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private OkHttpClient client;

    public JsonUtils() {
        client = new OkHttpClient().newBuilder().build();
    }

    /**
     * 获取页面实体类
     *
     * @param url
     * @return
     */
    public Observable<StateEntity> getStateJson(final String url) {
        return Observable.create(new Observable.OnSubscribe<StateEntity>() {
            @Override
            public void call(final Subscriber<? super StateEntity> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    final Request request = new Request.Builder().url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json_value = response.body().string();
                                Log.i(TAG, "onResponse: json_value-->" + json_value);
                                StateEntity stateEntity = new Gson().fromJson(json_value,
                                        StateEntity.class);
                                if (null != stateEntity) {
                                    subscriber.onNext(stateEntity);
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public Observable<DetailEntity> getDetailJson(final String url) {
        return Observable.create(new Observable.OnSubscribe<DetailEntity>() {
            @Override
            public void call(final Subscriber<? super DetailEntity> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    final Request request = new Request.Builder().url(url).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json_value = response.body().string();
                                Log.i(TAG, "onResponse: json_value-->" + json_value);
                                DetailEntity detailEntity = new Gson().fromJson(json_value,
                                        DetailEntity.class);
                                if (null != detailEntity) {
                                    subscriber.onNext(detailEntity);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
