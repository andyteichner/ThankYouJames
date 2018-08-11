package com.example.andy.thankyoujames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    private TextView warenkorbView;


    private ArrayList<Integer> shoppingItems;
    private String cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        setText();
    }


    private void initViews(){
        warenkorbView = findViewById(R.id.warenkorb_text);
    }

    private void setText(){

       shoppingItems= ItemClass.cart.getShoppingItems();

       for (int i = 0; i < shoppingItems.size(); i++){
           cartItems = "" + shoppingItems;
       }
       warenkorbView.setText(cartItems);


    }




}
