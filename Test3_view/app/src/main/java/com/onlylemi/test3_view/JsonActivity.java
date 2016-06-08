package com.onlylemi.test3_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JsonActivity extends AppCompatActivity {

    private Button button;

    private final static String JSON_PATH = "http://litchiapi.jstv" +
            ".com/api/GetFeeds?column=17&PageSize=20&pageIndex=1&val" +
            "=AD908EDAB9C3ED111A58AF86542CCF50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
