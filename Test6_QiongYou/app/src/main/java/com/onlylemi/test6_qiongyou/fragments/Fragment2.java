package com.onlylemi.test6_qiongyou.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onlylemi.test6_qiongyou.R;

/**
 * Fragment2
 *
 * @author: onlylemi
 * @time: 2016-06-14 10:46
 */
public class Fragment2 extends Fragment {

    private View mMianView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMianView = inflater.inflate(R.layout.fragment2, (ViewGroup) getActivity().findViewById(R
                .id.view_pager), false);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) mMianView.getParent();
        if (null != viewGroup) {
            viewGroup.removeAllViewsInLayout();
        }
        return mMianView;
    }
}
