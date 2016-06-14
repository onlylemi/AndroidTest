package com.onlylemi.test6_qiongyou.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.onlylemi.test6_qiongyou.R;

/**
 * Fragment1
 *
 * @author: onlylemi
 * @time: 2016-06-14 10:06
 */
public class Fragment1 extends Fragment {

    private Button button;
    private View mMainView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 加载布局
        LayoutInflater inflater = getActivity().getLayoutInflater();
        mMainView = inflater.inflate(R.layout.fragment1, (ViewGroup) getActivity().findViewById(
                R.id.view_pager), false);

        button = (Button) mMainView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "Click me!", Toast
                        .LENGTH_SHORT).show();
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
