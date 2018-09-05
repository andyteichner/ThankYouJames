package com.example.andy.thankyoujames;

import java.util.ArrayList;

// This class functions a storage area while selection different meals. The are stored by their ids.
public class ShoppingCart {

    private ArrayList<Integer> shoppingItems = new ArrayList<>();

    public ShoppingCart(){

    }

    public void addShoppingItem(int id){
        shoppingItems.add(id);
    }

    public ArrayList getShoppingItems(){
        return shoppingItems;
    }




}
