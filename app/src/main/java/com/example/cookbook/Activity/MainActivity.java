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
        clearBottomNavigationViewSelection();
        replaceFragment(new home());



        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            final int id =  item.getItemId();
            if(id == R.id.search) {
                replaceFragment(new search());
            } else if (id == R.id.favorite) {
                replaceFragment(new favorite());
            }

            return true;
        });

        binding.homebut.setOnClickListener(view -> {
            replaceFragment(new home());
            clearBottomNavigationViewSelection();
        });

    }

    private void clearBottomNavigationViewSelection() {
        binding.bottomNavigationView.getMenu().getItem(0).setChecked(false);
        binding.bottomNavigationView.getMenu().getItem(1).setChecked(false);
    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}