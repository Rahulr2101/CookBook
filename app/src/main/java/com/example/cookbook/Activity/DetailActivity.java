package com.example.cookbook.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.cookbook.R;
import com.example.cookbook.databinding.ActivityDetailBinding;
import com.example.cookbook.domain.RecipeDetailDomain;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private RecipeDetailDomain object;
    private LinearLayout ingredientLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ingredientLayout = findViewById(R.id.RecipeInst);

        object = new RecipeDetailDomain();
        object.setMealId(getIntent().getStringExtra("MEAL_ID").toString());
        fetchRecipeDetails();
    }

    private void fetchRecipeDetails() {
        String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + object.getMealId();

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray mealsArray = jsonObject.getJSONArray("meals");


                            JSONObject mealObject = mealsArray.getJSONObject(0);


                            object = new RecipeDetailDomain();
                            object.setMealId(mealObject.getString("idMeal"));
                            object.setStrMeal(mealObject.getString("strMeal"));


                            binding.detailTitleName.setText(object.getStrMeal());
                            object.setVideoUrl(mealObject.getString("strYoutube"));
                            object.setRecipePic(mealObject.getString("strMealThumb"));
                            object.setStrInstructions(mealObject.getString("strInstructions"));

                            for (int i = 1; i <= 20; i++) {
                                String ingredient = mealObject.getString("strIngredient" + i);
                                String measure = mealObject.getString("strMeasure" + i);
                                if (!ingredient.isEmpty() && !measure.isEmpty()) {
                                    addIngredientTextView(ingredient, measure);
                                }
                            }
                            binding.recipeinst.setText(object.getStrInstructions());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Glide.with(DetailActivity.this)
                                .load(object.getRecipePic())
                                .transform(new GranularRoundedCorners(30, 30, 0, 0))
                                .into( binding.recipePic);
                        youtubePlayBack(object.getVideoUrl());
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

    // Method to add TextViews for ingredients dynamically
    private void addIngredientTextView(String ingredient, String measure) {
        TextView textView = new TextView(this);
        textView.setText(ingredient + " - " + measure);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ingredientLayout.addView(textView);
    }


    public void onBackButtonClicked(View view) {
        finish();
    }

    public void youtubePlayBack(String url){
        String videoId ="null";


        int index = url.indexOf("v=");
        if (index != -1) {

            videoId = url.substring(index + 2);
            int endIndex = videoId.indexOf("&");
            if (endIndex != -1) {
                videoId = videoId.substring(0, endIndex);
            }
        }
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        String finalVideoId = videoId;
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {

                String Id = finalVideoId;
                youTubePlayer.loadVideo(Id, 0);
            }
        });
    }
}
