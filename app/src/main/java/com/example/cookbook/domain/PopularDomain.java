package com.example.cookbook.domain;

public class PopularDomain {
    private String title;
    private String picUrl;

    public PopularDomain(String  picUrl,String title){
        this.picUrl = picUrl;
        this.title = title;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
