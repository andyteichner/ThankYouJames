package com.example.andy.thankyoujames;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Meal.class}, version = 3, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
