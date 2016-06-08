package com.onlylemi.test4_lizhi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onlylemi.test4_lizhi.R;
import com.onlylemi.test4_lizhi.entity.Paramz;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * ParamzAdapter
 *
 * @author: onlylemi
 * @time: 2016-06-08 9:56
 */
public class ParamzAdapter extends BaseAdapter {

    private Context context;
    private List<Paramz.ParamzBean.FeedsBean> list;

    public ParamzAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void bindList(List<Paramz.ParamzBean.FeedsBean> list) {
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
        ViewHolder holder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.paramz, parent, false);

            holder = new ViewHolder();
            holder.subject = (TextView) convertView.findViewById(R.id.subject);
            holder.summary = (TextView) convertView.findViewById(R.id.summary);
            holder.cover = (ImageView) convertView.findViewById(R.id.cover);
            holder.changed = (TextView) convertView.findViewById(R.id.changed);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.subject.setText(list.get(position).getData().getSubject());
        holder.summary.setText(list.get(position).getData().getSummary());
        Picasso.with(context)
                .load("http://litchiapi.jstv.com" + list.get(position).getData().getCover())
                .resize(260, 260)
                .centerCrop()
                .into(holder.cover);
        holder.changed.setText(list.get(position).getData().getChanged());

        return convertView;
    }

    static class ViewHolder {
        TextView subject;
        TextView summary;
        ImageView cover;
        TextView changed;
    }
}
