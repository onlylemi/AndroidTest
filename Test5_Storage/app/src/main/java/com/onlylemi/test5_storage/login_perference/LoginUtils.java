package com.onlylemi.test5_storage.login_perference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.onlylemi.test5_storage.entity.LoginResult;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * LoginUtils
 *
 * @author: onlylemi
 * @time: 2016-06-13 10:10
 */
public class LoginUtils {

    private static final String TAG = LoginUtils.class.getSimpleName();

    private final String SHARED_FILENAME = "login";

    private OkHttpClient client;
    private Context context;

    public LoginUtils(Context context) {
        client = new OkHttpClient().newBuilder().build();
        this.context = context;
    }

    public boolean isAutoLogin(Map<String, String> params) {
        SharedPreferences preferences = context.getSharedPreferences(SHARED_FILENAME, Context
                .MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                editor.putString(entry.getKey(), entry.getValue());
            }
        }
        return editor.commit();
    }

    public Map<String, ?> getSharedMap() {
        return context.getSharedPreferences(SHARED_FILENAME, Context.MODE_PRIVATE).getAll();
    }

    public Observable<LoginResult> login(final String url, final Map<String, String>
            params) {
        return Observable.create(new Observable.OnSubscribe<LoginResult>() {
            @Override
            public void call(final Subscriber<? super LoginResult> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (null != params && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            builder.add(entry.getKey(), entry.getValue());
                        }
                    }
                    RequestBody requestBody = builder.build();
                    Request request = new Request.Builder().post(requestBody).url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json_values = response.body().string();
                                Log.i(TAG, "onResponse: " + json_values);
                                LoginResult loginResult = new Gson().fromJson(json_values,
                                        LoginResult.class);
                                if (null != loginResult) {
                                    subscriber.onNext(loginResult);
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public Observable<LoginResult> login1(final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<LoginResult>() {
            @Override
            public void call(final Subscriber<? super LoginResult> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (null != params && !params.isEmpty()) {
                        for (Map.Entry<String, String> entry : params.entrySet()) {
                            builder.add(entry.getKey(), entry.getValue());
                        }
                    }
                    RequestBody requestBody = builder.build();
                    Request request = new Request.Builder().post(requestBody).url(url).build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                String json_values = response.body().string();
                                Log.i(TAG, "onResponse: json_values=" + json_values);
                                LoginResult loginResult = new Gson().fromJson(json_values,
                                        LoginResult.class);
                                if (null != loginResult) {
                                    subscriber.onNext(loginResult);
                                }
                            }
                        }
                    });
                }
            }
        });
    }
}
