package com.example.cookbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.databinding.ActivityDetailBinding;
import com.example.cookbook.databinding.ListItemLayoutBinding;
import com.example.cookbook.domain.IngredientsDomain;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.Viewholder> {

    ArrayList<IngredientsDomain> items;

    public IngredientAdapter(ArrayList<IngredientsDomain> items) {
        this.items = items;
    }

    Context context;


    @NonNull
    @Override
    public IngredientAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemLayoutBinding binding = ListItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new IngredientAdapter.Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.Viewholder holder, int position) {
        IngredientsDomain ingredient = items.get(position);
        holder.binding.IngredientView.setText(ingredient.getIngredientName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ListItemLayoutBinding binding;
        public Viewholder(ListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
