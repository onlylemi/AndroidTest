package com.onlylemi.test3_view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private Button btn1, btn2;
    private ImageView imageView1, imageView2;

    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        imageView1 = (ImageView) findViewById(R.id.image_view1);
        imageView2 = (ImageView) findViewById(R.id.image_view2);


        final String image_path = "http://ww3.sinaimg.cn/large/610dc034jw1f4mibgnto4j20nv0d6gp2" +
                ".jpg";
        client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder().url(image_path).build();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            byte[] buffer = response.body().bytes();
                            final Bitmap bitmap = BitmapFactory.decodeByteArray(buffer, 0, buffer
                                    .length);
                            imageView1.post(new Runnable() {
                                @Override
                                public void run() {
                                    imageView1.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }
                });
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(Main2Activity.this).load(image_path).into(imageView2);
            }
        });

    }
}
