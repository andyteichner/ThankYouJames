package com.example.andy.thankyoujames;

import java.util.ArrayList;

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
