package com.example.cookbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.example.cookbook.Adapter.PopularAdapter;
import com.example.cookbook.R;
import com.example.cookbook.databinding.ActivityMainBinding;
import com.example.cookbook.domain.PopularDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initRecyclerView();

    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("item_1","hher"));
        items.add(new PopularDomain("item_1","jdif"));
        items.add(new PopularDomain("item_1","djf"));




        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}