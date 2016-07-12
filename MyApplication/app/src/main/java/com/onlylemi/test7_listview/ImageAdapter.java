package com.onlylemi.test7_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * ImageAdapter
 *
 * @author: onlylemi
 * @time: 2016-07-09 17:05
 */
public class ImageAdapter extends BaseAdapter {

    private List<String> list;
    private Context context;

    public ImageAdapter() {
        list = new ArrayList<>();
    }

    public void bindList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        context = parent.getContext();
        String url = list.get(position);

        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, parent,
                    false);
            holder.image = (ImageView) convertView.findViewById(R.id.image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.image.setTag(url);
        if (!url.isEmpty()) {
            if (AsyncImageLoader.getInstance().loadImage(holder.image, url)) {
                holder.image.setImageResource(R.mipmap.ic_launcher);
            }
        }

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
    }
}
