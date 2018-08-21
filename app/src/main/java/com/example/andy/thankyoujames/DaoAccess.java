package com.example.andy.thankyoujames;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface DaoAccess {
    @Insert
    void insertMealIntoDB(Meal meal);

    @Query("SELECT * FROM meal WHERE mealID = :mealNumber")
    Meal fetchOneMealbyMealID(int mealNumber);

    @Query("SELECT count(*) FROM meal")
    int numberOfRows();

    @Query("DELETE FROM meal")
    void nukeTable();

    @Query("UPDATE meal SET price = :newMealPrice WHERE mealID = :mealNumber")
    void updateMealPrice(double newMealPrice, int mealNumber);
}
