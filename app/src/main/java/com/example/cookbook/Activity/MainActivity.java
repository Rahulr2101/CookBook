package com.example.cookbook.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cookbook.Adapter.PopularAdapter;
import com.example.cookbook.R;
import com.example.cookbook.databinding.ActivityMainBinding;
import com.example.cookbook.domain.PopularDomain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        PopularAdapter adapter = new PopularAdapter(items);
        binding.PopularView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        binding.PopularView.setAdapter(adapter);

        String url = "https://www.themealdb.com/api/json/v1/1/filter.php?a=Canadian";
        RequestQueue queue = Volley.newRequestQueue(this);

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
                                items.add(new PopularDomain(singleitem.getString("strMealThumb"), singleitem.getString("strMeal")));
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


//        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}