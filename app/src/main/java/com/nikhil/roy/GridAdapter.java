package com.nikhil.roy; // ← আপনার প্যাকেজ নাম এখানে দিন

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private String[] names;
    private int[] icons;

    public GridAdapter(Context context, String[] names, int[] icons) {
        this.context = context;
        this.names = names;
        this.icons = icons;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int i) {
        return names[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView icon = view.findViewById(R.id.grid_icon);
        TextView text = view.findViewById(R.id.grid_text);

        icon.setImageResource(icons[i]);
        text.setText(names[i]);

        return view;
    }
}