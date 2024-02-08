package com.example.cookbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.cookbook.databinding.ViewholderPupListBinding;
import com.example.cookbook.domain.PopularDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    ArrayList<PopularDomain> items ;
    Context context;
    ViewholderPupListBinding binding;

    public PopularAdapter(ArrayList<PopularDomain> list) {
        this.items = list;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding =  ViewholderPupListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        binding.titleText.setText(items.get(position).getTitle());
        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context).load(drawableResourced).transform(new GranularRoundedCorners(30,30,0,0)).into(binding.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(ViewholderPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
