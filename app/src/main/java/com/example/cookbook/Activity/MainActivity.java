package com.example.cookbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.cookbook.R;
import com.example.cookbook.databinding.ActivityMainBinding;
import com.example.cookbook.favorite;
import com.example.cookbook.home;
import com.example.cookbook.search;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new home());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            final int id =  item.getItemId();
            if(id == R.id.search) {
                replaceFragment(new search());
            } else if (id == R.id.home) {
                replaceFragment(new home());
            } else if (id == R.id.favorite) {
                replaceFragment(new favorite());
            }

            return true;
        });



    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}