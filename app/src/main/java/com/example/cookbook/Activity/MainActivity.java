package com.example.cookbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookbook.Adapter.FilterAdapter;
import com.example.cookbook.Adapter.PopularAdapter;
import com.example.cookbook.R;
import com.example.cookbook.databinding.ActivityMainBinding;
import com.example.cookbook.domain.PopularDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FilterAdapter.FilterClickListener {
    ActivityMainBinding binding;
    TextView recipeCountTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        recipeCountTextView = binding.recipeCount;

        initRecyclerView();

    }

    @Override
    public void onFilterClicked(String filter){
        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?a=" + filter;
        fetchRecipes(url);
    }


    public void fetchRecipes(String url){

        ArrayList<PopularDomain> items = new ArrayList<>();
        ArrayList<String> filter = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(this);

        PopularAdapter adapter = new PopularAdapter(items);

        binding.PopularView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
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
                                items.add(new PopularDomain(singleitem.getString("strMealThumb"), singleitem.getString("strMeal"),singleitem.getString("idMeal")));
                            }
                            adapter.notifyDataSetChanged(); // Notify adapter of data changes
                            recipeCountTextView.setText(items.size()+" recipes");
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

        ArrayList<String> filter = new ArrayList<>();
        String defaultFilter = "French";
        filter.add("Canadian");
        filter.add(defaultFilter);
        filter.add("Japanese");
        filter.add("Indian");
        filter.add("Russian");
        filter.add("Spanish");
        filter.add("Mexican");
        FilterAdapter filterAdapter = new FilterAdapter(filter,this);



        binding.filterView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        binding.filterView.setAdapter(filterAdapter);


        fetchRecipes("https://www.themealdb.com/api/json/v1/1/filter.php?a=" + defaultFilter);

    }
}