package com.example.cookbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookbook.Adapter.PopularAdapter;
import com.example.cookbook.Adapter.SearchAdapter;
import com.example.cookbook.databinding.FragmentSearchBinding;
import com.example.cookbook.domain.PopularDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class search extends Fragment {
    private @NonNull FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());


        binding.searchVi.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searchText = newText;
                fetchSearchResults(searchText);
                return true;
            }
        });
        return binding.getRoot();
    }


    private void fetchSearchResults(String query) {
        ArrayList<PopularDomain> items = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        SearchAdapter adapter = new SearchAdapter(items);
        binding.allrecipe.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        binding.allrecipe.setAdapter(adapter);

        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            items.clear(); // Clear the list before adding new items
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray mealsArray = jsonObject.getJSONArray("meals");
                            for (int i = 0; i < mealsArray.length(); i++) {
                                JSONObject singleitem = mealsArray.getJSONObject(i);
                                items.add(new PopularDomain(singleitem.getString("strMealThumb"), singleitem.getString("strMeal"), singleitem.getString("idMeal")));
                            }
                            adapter.notifyDataSetChanged(); // Notify adapter of data changes
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("api", "onErrorResponse" + error.getLocalizedMessage());
                    }
                });

        queue.add(stringRequest);
    }


}