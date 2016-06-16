package com.onlylemi.test6_qiongyou.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.onlylemi.test6_qiongyou.R;
import com.onlylemi.test6_qiongyou.adapter.GuideAdapter;
import com.onlylemi.test6_qiongyou.common.CommonURL;
import com.onlylemi.test6_qiongyou.entity.StateEntity;
import com.onlylemi.test6_qiongyou.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Fragment2
 *
 * @author: onlylemi
 * @time: 2016-06-14 10:46
 */
public class Fragment2 extends Fragment {

    private View mMainView;
    private GridView gridView;

    private GuideAdapter adapter;

    private JsonUtils jsonUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment2, (ViewGroup) getActivity().findViewById(R
                .id.view_pager), false);

        gridView = (GridView) mMainView.findViewById(R.id.gridview);
        adapter = new GuideAdapter(getActivity());
        gridView.setAdapter(adapter);

        jsonUtils = new JsonUtils();
        jsonUtils.getStateJson(CommonURL.EUROPE_PATH)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StateEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(StateEntity stateEntity) {
                        if (null != stateEntity) {
                            List<StateEntity.DataBean.GuidesBean> list = new ArrayList<>();

                            for (StateEntity.DataBean data : stateEntity.getData()) {
                                for (StateEntity.DataBean.GuidesBean guide : data.getGuides()) {
                                    list.add(guide);
                                }
                            }

                            adapter.bindList(list);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) mMainView.getParent();
        if (null != viewGroup) {
            viewGroup.removeAllViewsInLayout();
        }
        return mMainView;
    }
}
