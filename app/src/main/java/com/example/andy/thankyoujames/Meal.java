package com.example.andy.thankyoujames;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

@Entity
public class Meal  {

    @NonNull
    @PrimaryKey
    private int mealID;
    private int imageID;
    private double price;
    private String mealName;
    private String description;


    @NonNull
    public int getMealID() {
        return mealID;
    }

    public void setMealID(@NonNull int mealID) {
        this.mealID = mealID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
