package com.onlylemi.test4_lizhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.onlylemi.test4_lizhi.adapter.RelatedGuidesAdapter;
import com.onlylemi.test4_lizhi.common.CommonURL;
import com.onlylemi.test4_lizhi.entity.Place;
import com.onlylemi.test4_lizhi.json.JsonUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ThirdActivity extends AppCompatActivity {

    private RelatedGuidesAdapter adapter;
    private ListView listView;

    private JsonUtils jsonUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        listView = (ListView) findViewById(R.id.related_guides_listview);
        adapter = new RelatedGuidesAdapter(this);
        listView.setAdapter(adapter);

        jsonUtils = new JsonUtils();
        jsonUtils.getJsonPlace(CommonURL.CITY_URL)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Place>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Place place) {
                        List<Place.DataBean.RelatedGuidesBean> list = place.getData()
                                .getRelated_guides();
                        adapter.bindList(list);
                        adapter.notifyDataSetChanged();
                    }
                });

    }
}
