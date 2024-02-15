package com.example.cookbook.domain;

import androidx.recyclerview.widget.RecyclerView;

public class IngredientsDomain {

    private String IngredientName;
    private String MeasureUnit;

    public String getIngredientName() {
        return IngredientName;
    }

    public void setIngredientName(String ingredientName) {
        IngredientName = ingredientName;
    }

    public String getMeasureUnit() {
        return MeasureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        MeasureUnit = measureUnit;
    }



    public IngredientsDomain(String IngredientName, String measureUnit){
        this.IngredientName = IngredientName;
        this.MeasureUnit =  measureUnit;
    }


}
