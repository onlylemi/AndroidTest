package com.onlylemi.test10_viewpager;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPagerAdapter
 *
 * @author: onlylemi
 * @time: 2016-07-21 10:55
 */
public class ViewPagerAdapter extends PagerAdapter {

    private static final String TAG = ViewPagerAdapter.class.getSimpleName();

    private List<Entity> list;
    private View[] views = new View[3];

    public ViewPagerAdapter() {
        this.list = new ArrayList<>();
    }

    public void bindList(List<Entity> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % list.size();
        int index = position % views.length;

        Log.i(TAG, "instantiateItem: position=" + position);
        Log.i(TAG, "instantiateItem: index=" + index);

        ViewHolder holder = null;
        if (views[index] == null) {
            holder = new ViewHolder();

            views[index] = LayoutInflater.from(container.getContext()).inflate(
                    R.layout.viewpager_item, null);
            holder.imageView = (ImageView) views[index].findViewById(R.id.imageview);
            holder.textView = (TextView) views[index].findViewById(R.id.textview);

            views[index].setTag(holder);
        } else {
            holder = (ViewHolder) views[index].getTag();
        }

        // 判断该 view 是否已经被添加
        ViewParent vp = views[index].getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(views[index]);
        }

        holder.imageView.setImageResource(list.get(position).getImg());
        holder.textView.setText(list.get(position).getTitle());

        container.addView(views[index]);

        return views[index];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % list.size();
        int index = position % views.length;

//        Log.i(TAG, "destroyItem: destroyItem=" + position);
//        Log.i(TAG, "destroyItem: index=" + index);
        // 不要调用
//        container.removeView(views[index]);
    }

    static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
