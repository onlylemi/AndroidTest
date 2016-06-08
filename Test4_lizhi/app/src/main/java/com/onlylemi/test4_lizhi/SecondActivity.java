package com.onlylemi.test4_lizhi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.onlylemi.test4_lizhi.adapter.PhotoAdapter;
import com.onlylemi.test4_lizhi.common.CommonURL;
import com.onlylemi.test4_lizhi.entity.Paramz;
import com.onlylemi.test4_lizhi.json.JsonUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SecondActivity extends AppCompatActivity {

    private JsonUtils utils;
    private PhotoAdapter adapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        gridView = (GridView) findViewById(R.id.gridview);
        adapter = new PhotoAdapter(this);
        gridView.setAdapter(adapter);

        utils = new JsonUtils();
        utils.getJsonParamz(CommonURL.LIZHI_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Paramz>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Paramz paramz) {
                        List<Paramz.ParamzBean.FeedsBean> list = paramz.getParamz().getFeeds();
                        adapter.bindList(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }
}
