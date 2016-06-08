package com.onlylemi.test4_lizhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.onlylemi.test4_lizhi.adapter.ParamzAdapter;
import com.onlylemi.test4_lizhi.common.CommonURL;
import com.onlylemi.test4_lizhi.entity.Paramz;
import com.onlylemi.test4_lizhi.json.JsonUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ParamzAdapter adapter;
    private JsonUtils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ParamzAdapter(this);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

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
