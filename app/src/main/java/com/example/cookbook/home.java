package com.example.cookbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookbook.Adapter.FilterAdapter;
import com.example.cookbook.Adapter.PopularAdapter;
import com.example.cookbook.databinding.FragmentHomeBinding;
import com.example.cookbook.domain.FilterItemDomain;
import com.example.cookbook.domain.PopularDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class home extends Fragment implements FilterAdapter.FilterClickListener {
    private FragmentHomeBinding binding;
    private TextView recipeCountTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipeCountTextView = binding.recipeCount;

        initRecyclerView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onFilterClicked(String filter) {
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?c=" + filter;
        fetchRecipes(url);
    }

    public void fetchRecipes(String url) {
        ArrayList<PopularDomain> items = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(requireActivity());
        PopularAdapter adapter = new PopularAdapter(items);
        binding.PopularView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.PopularView.setAdapter(adapter);

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
                            recipeCountTextView.setText(items.size() + " recipes");
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

    private void initRecyclerView() {
        ArrayList<FilterItemDomain> filter = new ArrayList<>();
        String defaultFilter = "Starter";
        filter.add(new FilterItemDomain(defaultFilter, true));
        filter.add(new FilterItemDomain("Breakfast", false));
        filter.add(new FilterItemDomain("Dessert", false));
        filter.add(new FilterItemDomain("Vegan", false));
        filter.add(new FilterItemDomain("Vegetarian", false));
        filter.add(new FilterItemDomain("Chicken", false));
        filter.add(new FilterItemDomain("Pasta", false));
        filter.add(new FilterItemDomain("Seafood", false));
        filter.add(new FilterItemDomain("Side", false));
        filter.add(new FilterItemDomain("Pork", false));
        filter.add(new FilterItemDomain("Lamb", false));
        FilterItemDomain.setFilterItemsList(filter);
        FilterAdapter filterAdapter = new FilterAdapter(filter, this);

        binding.filterView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.filterView.setAdapter(filterAdapter);

        fetchRecipes("https://www.themealdb.com/api/json/v1/1/filter.php?c=" + defaultFilter);
    }
}
