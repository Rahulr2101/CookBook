package com.example.cookbook.domain;

import java.util.ArrayList;

public class RecipeDetailDomain {
    private String mealId;
    private String strMeal;

    private String strInstructions;
    private String RecipePic;
    private ArrayList<IngredientsDomain> IngredientsList;

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getRecipePic() {
        return RecipePic;
    }

    public void setRecipePic(String recipePic) {
        RecipePic = recipePic;
    }

    public ArrayList<IngredientsDomain> getIngredientsList() {
        return IngredientsList;
    }

    public void setIngredientsList(ArrayList<IngredientsDomain> ingredientsList) {
        IngredientsList = ingredientsList;
    }


}
