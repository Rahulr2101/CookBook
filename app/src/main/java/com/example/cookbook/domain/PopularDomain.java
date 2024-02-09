package com.example.cookbook.domain;

import java.security.PublicKey;

public class PopularDomain {
    private String title;
    private String picUrl;

    private String mealId;

    public PopularDomain(String  picUrl,String title,String mealId){
        this.picUrl = picUrl;
        this.title = title;
        this.mealId = mealId;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setMealId(String mealId){
        this.mealId =mealId;
    }

    public String getMealId(){
        return this.mealId;
    }
    public String getPicUrl() {
        return this.picUrl;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

}
