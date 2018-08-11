package com.example.andy.thankyoujames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andy.thankyoujames.adapter.CartItemAdapter;


public class Cart extends AppCompatActivity {

    private Button btnContinueShopping, btnSendOrder;
    private ListView itemListView;
    private TextView textSumPrice, textTotal, cartHeader, textFoodID, textQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        getExtras();

        CartItemAdapter cartItemAdapter = new CartItemAdapter(this);
        //cartItemAdapter.update(getCartItems());

        itemListView.setAdapter(cartItemAdapter);

    }

    private void initView(){
        //Buttons
        btnContinueShopping = (Button)findViewById(R.id.btnContinueShopping);
        btnSendOrder = (Button)findViewById(R.id.btnSendOrder);
        //ListView
        itemListView = (ListView) findViewById(R.id.cartItems);
        //TextViews
        textSumPrice = (TextView) findViewById(R.id.textSumPrice);
        textTotal =(TextView) findViewById(R.id.textTotal);
        cartHeader = (TextView) findViewById(R.id.cartHeader);
    }

    private void getExtras() {
        int foodID = getIntent().getExtras().getInt("finalFoodID");
        int quantity = getIntent().getExtras().getInt("itemQuantity");
        //double price = ...


    }

}
