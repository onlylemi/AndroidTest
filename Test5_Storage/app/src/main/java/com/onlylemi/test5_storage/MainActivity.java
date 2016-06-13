package com.onlylemi.test5_storage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.onlylemi.test5_storage.entity.LoginResult;
import com.onlylemi.test5_storage.login_perference.LoginUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LOGIN_PATH = "http://10.0.11.225:8080/webproject/LoginAction";

    private EditText username, password;
    private Button login;
    private CheckBox checkBox;

    private LoginUtils loginUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUtils = new LoginUtils(this);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.r_pword);

        username.setText((String) loginUtils.getSharedMap().get("username"));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameStr = username.getText().toString().trim();
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

                                    Log.i(TAG, "onNext: " + checkBox.isChecked());
                                    if (!checkBox.isChecked()) {
                                        params.put("username", "");
                                        params.put("password", "");
                                    }
                                    loginUtils.isAutoLogin(params);

                                    Intent intent = new Intent(MainActivity.this, WelcomeActivity
                                            .class);
                                    startActivity(intent);
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
