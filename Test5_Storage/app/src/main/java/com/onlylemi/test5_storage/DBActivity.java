package com.onlylemi.test5_storage;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.onlylemi.test5_storage.db.SQLManager;
import com.onlylemi.test5_storage.entity.LoginResult;
import com.onlylemi.test5_storage.entity.UserInfo;
import com.onlylemi.test5_storage.login_perference.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DBActivity extends AppCompatActivity {

    private static final String TAG = DBActivity.class.getSimpleName();

    private static final String LOGIN_PATH = "http://10.0.11.225:8080/webproject/LoginAction";

    private EditText username, password;
    private Button login;

    private LoginUtils loginUtils;
    private SQLManager sqlManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        loginUtils = new LoginUtils(this);
        sqlManager = new SQLManager(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usernameStr = username.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();

                if (usernameStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入用户名！！！", Toast.LENGTH_SHORT).show();
                }
                if (passwordStr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "请输入密码！！！", Toast.LENGTH_SHORT).show();
                }

                final Map<String, String> params = new HashMap<>();
                params.put("username", usernameStr);
                params.put("password", passwordStr);

                loginUtils.login(LOGIN_PATH, params)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<LoginResult>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(LoginResult loginResult) {
                                if (loginResult.getResult().getResultCode() == 1) {
                                    Toast.makeText(getApplicationContext(), loginResult.getResult
                                            ().getResultMsg(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(DBActivity.this, WelcomeActivity
                                            .class);
                                    startActivity(intent);

                                    // 将账号和密码存到数据库中
                                    new AsyncTask<Map<String, String>, Void, Void>() {

                                        @Override
                                        protected Void doInBackground(Map<String, String>...
                                                                              params) {
                                            UserInfo info = new UserInfo();
                                            info.setUsername(params[0].get("username"));
                                            info.setPassword(params[0].get("password"));
                                            sqlManager.insertUser(info);
                                            return null;
                                        }
                                    }.execute(params);


                                } else {
                                    Toast.makeText(getApplicationContext(), loginResult.getResult
                                            ().getResultMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
