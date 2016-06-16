package com.onlylemi.test6_qiongyou;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.onlylemi.test6_qiongyou.common.CommonURL;
import com.onlylemi.test6_qiongyou.entity.DetailEntity;
import com.onlylemi.test6_qiongyou.json.JsonUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NextActivity extends AppCompatActivity {

    private TextView textView;
    private JsonUtils jsonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textView = new TextView(this);
        setContentView(textView);


        String guide_id = getIntent().getStringExtra("guide_id");
        jsonUtils = new JsonUtils();
        jsonUtils.getDetailJson(CommonURL.SECOND_DETAIL_URL + guide_id)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final DetailEntity detailEntity) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(detailEntity.getData().getInfo());
                            }
                        });
                    }
                });

    }
}
