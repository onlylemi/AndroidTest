package com.onlylemi.test6_qiongyou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onlylemi.test6_qiongyou.R;
import com.onlylemi.test6_qiongyou.entity.StateEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * GuideAdapter
 *
 * @author: onlylemi
 * @time: 2016-06-14 16:17
 */
public class GuideAdapter extends BaseAdapter {

    private List<StateEntity.DataBean.GuidesBean> list;
    private Context context;

    public GuideAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void bindList(List<StateEntity.DataBean.GuidesBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.guide, parent, false);

            holder = new ViewHolder();
            holder.cover = (ImageView) convertView.findViewById(R.id.cover);
            holder.guide_cnname = (TextView) convertView.findViewById(R.id.guide_cnname);
            holder.guide_enname = (TextView) convertView.findViewById(R.id.guide_enname);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
                .load(list.get(position).getCover() + "/670_446.jpg")
                .centerCrop()
                .resize(250, 400)
                .into(holder.cover);
        holder.guide_cnname.setText(list.get(position).getGuide_cnname());
        holder.guide_enname.setText(list.get(position).getGuide_enname());

        return convertView;
    }

    static class ViewHolder {
        ImageView cover;
        TextView guide_cnname;
        TextView guide_enname;
    }
}
