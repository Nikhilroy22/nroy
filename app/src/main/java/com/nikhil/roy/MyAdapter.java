package com.nikhil.roy; // ← আপনার প্যাকেজ নাম এখানে দিন

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> itemList;

    public MyAdapter(List<String> itemList) {
        this.itemList = itemList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1); // Simple layout textView
        }
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.textView.setText(item);

        // 🔹 Click Listener
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Clicked: " + item, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}