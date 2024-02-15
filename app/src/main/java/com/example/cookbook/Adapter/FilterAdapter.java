package com.example.cookbook.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cookbook.R;
import com.example.cookbook.databinding.ViewholderFilterListBinding;
import com.example.cookbook.domain.FilterItemDomain;

import java.util.ArrayList;

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    ArrayList<FilterItemDomain> filter ;
    Context context;
    FilterClickListener clickListener;

    public interface FilterClickListener {
        void onFilterClicked(String filter);
    }


    public FilterAdapter(ArrayList<FilterItemDomain> filter, FilterClickListener clickListener) {
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

        FilterItemDomain currentItem = filter.get(position);
        holder.binding.filterText.setText(currentItem.getName());
        holder.binding.getRoot().setBackgroundResource(0);
        if (currentItem.isSelected()) {
            holder.binding.getRoot().setBackgroundResource(R.drawable.white_background_filter);
            holder.binding.filterText.setTextColor(ContextCompat.getColor(context,R.color.white));
        } else {
            holder.binding.getRoot().setBackgroundResource(R.drawable.black_outline);
            holder.binding.filterText.setTextColor(ContextCompat.getColor(context,R.color.black));
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION && clickListener != null) {
                    clickListener.onFilterClicked(filter.get(adapterPosition).getName());
                    filter.get(adapterPosition).setSelected(true);
                    notifyDataSetChanged();


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
