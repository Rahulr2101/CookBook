package com.example.cookbook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.cookbook.Activity.DetailActivity;
import com.example.cookbook.databinding.SearchCardBinding;
import com.example.cookbook.domain.PopularDomain;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Viewholder> {
    private ArrayList<PopularDomain> items;
    private Context context;

    public SearchAdapter(ArrayList<PopularDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SearchCardBinding binding = SearchCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        context =  parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Bind data to the ViewHolder here
        PopularDomain currentItem = items.get(position);
        holder.binding.dishName.setText(currentItem.getTitle());
        loadImage(currentItem.getPicUrl(), holder.binding.dishPic);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("MEAL_ID",currentItem.getMealId());
                context.startActivity(intent);
            }
        });
    }

    private void loadImage(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                .into(imageView);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private SearchCardBinding binding;

        public Viewholder(SearchCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
