package com.onlylemi.test6_qiongyou.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onlylemi.test6_qiongyou.R;

/**
 * Fragment3
 *
 * @author: onlylemi
 * @time: 2016-06-14 10:52
 */
public class Fragment3 extends Fragment {

    private View mMainView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment3, (ViewGroup) getActivity().findViewById(R
                .id.view_pager), false);


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
