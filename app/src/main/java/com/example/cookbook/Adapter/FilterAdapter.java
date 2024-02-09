package com.example.cookbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookbook.databinding.ViewholderFilterListBinding;
import com.example.cookbook.databinding.ViewholderPupListBinding;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    ArrayList<String> filter ;
    Context context;
    FilterClickListener clickListener;

    public interface FilterClickListener {
        void onFilterClicked(String filter);
    }


    public FilterAdapter(ArrayList<String> filter, FilterClickListener clickListener) {
        this.filter = filter;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderFilterListBinding binding = ViewholderFilterListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterAdapter.ViewHolder holder, int position) {

        holder.binding.filterText.setText(filter.get(position));

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.onFilterClicked(filter.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return filter.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderFilterListBinding binding;

        public ViewHolder(ViewholderFilterListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
