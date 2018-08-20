package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Cart extends Activity implements View.OnClickListener{

    private TextView cartHeader, textTotal, textSumPrice;
    private Button  btnSendOrder, btnKeepShopping;
    private ListView itemList;
    private MealListAdapter adapter;

    private double sumPrices = 0;

    private ArrayList<Integer> shoppingItems;
    private ArrayList<Meal> shoppedMeals = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        getShoppingItems();
        setUpListView();
        //setTexts();
    }

    private void initViews(){

        //TextViews
        cartHeader = findViewById(R.id.cartHeader);
        textTotal = findViewById(R.id.textTotal);
        textSumPrice = findViewById(R.id.textSumPrice);

        //Buttons
        btnSendOrder = findViewById(R.id.btnSendOrder);
        btnKeepShopping = findViewById(R.id.btnContinueShopping);
        btnKeepShopping.setOnClickListener(this);
        btnSendOrder.setOnClickListener(this);


    }


    private void getShoppingItems(){
        shoppingItems = ItemClass.shoppingCart.getShoppingItems();
        getShoppedMeals();
    }

    private void getShoppedMeals(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < shoppingItems.size(); i ++){

                    Meal newShoppedMeal;
                    newShoppedMeal = MainJames.mealDatabase.daoAccess().fetchOneMealbyMealID(shoppingItems.get(i));
                    sumPrices += newShoppedMeal.getPrice();
                    System.out.println(sumPrices);
                    //getSumPrice(newShoppedMeal.getPrice());
                    shoppedMeals.add(newShoppedMeal);
                    }
                }
            }).start();

    }

   // private void getSumPrice(double newPrice){
     //   sumPrices += newPrice;
    //}

    private void setUpListView(){

        //ListView
        itemList = findViewById(R.id.itemView);

        adapter = new MealListAdapter(getApplicationContext(), shoppedMeals);
        itemList.setAdapter(adapter);

        itemList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                itemLongClicked(position);
                return false;
            }
        });

    }

    /*public void setTexts(){
        String result = ""+ sumPrices;

        textSumPrice.setText(result);
    }*/

    private void clearShoppingCart(){

        ItemClass.shoppingCart.clearShoppingCart();
        shoppingItems.clear();
        shoppedMeals.clear();
        adapter.notifyDataSetChanged();
    }

    private void itemLongClicked(int position){

        shoppingItems.remove(position);
        shoppedMeals.remove(position);
        ItemClass.shoppingCart.removeSingleItem(position);
        adapter.notifyDataSetChanged();

    }





    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnContinueShopping:
                Intent intent = new Intent(Cart.this, MainJames.class);
                startActivity(intent);
                break;
            case R.id.btnSendOrder:
                Intent getToTimer = new Intent(Cart.this, TimerActivity.class);
                startActivity(getToTimer);
                break;
            case R.id.btnClearCart:
                clearShoppingCart();
                Toast.makeText(this, "Warenkorb geleert", Toast.LENGTH_SHORT).show();
        }

    }

}
