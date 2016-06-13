package com.onlylemi.test4_lizhi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onlylemi.test4_lizhi.R;
import com.onlylemi.test4_lizhi.entity.Place;
import com.onlylemi.test4_lizhi.utils.XUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * PhotoAdapter
 *
 * @author: onlylemi
 * @time: 2016-06-08 15:23
 */
public class RelatedGuidesAdapter extends BaseAdapter {

    private Context context;
    private List<Place.DataBean.RelatedGuidesBean> list;

    public RelatedGuidesAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void bindList(List<Place.DataBean.RelatedGuidesBean> list) {
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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout
                    .related_guides_listview_item, parent, false);

            holder = new ViewHolder();
            holder.cover = (ImageView) convertView.findViewById(R.id.cover);
            holder.cnname = (TextView) convertView.findViewById(R.id.cnname);
            holder.category_title = (TextView) convertView.findViewById(R.id.category_title);
            holder.update_time = (TextView) convertView.findViewById(R.id.update_time);
            holder.download = (ImageView) convertView.findViewById(R.id.download);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
                .load("http://litchiapi.jstv.com" + list.get(position).getCover())
                .resize(300, 500)
                .centerCrop()
                .into(holder.cover);
        holder.cnname.setText(list.get(position).getCnname());
        holder.category_title.setText(list.get(position).getCategory_title() + "/" + list.get
                (position).getCountry_name_cn());
        holder.update_time.setText(XUtils.formatTime(list.get(position).getUpdate_time()) + "更新");

        return convertView;
    }

    static class ViewHolder {
        ImageView cover;
        TextView cnname;
        TextView category_title;
        TextView update_time;
        ImageView download;
    }
}
