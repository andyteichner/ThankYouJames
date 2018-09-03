package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Cart extends Activity implements View.OnClickListener{

    private TextView cartHeader, textTotal, textSumPrice;
    private Button  btnSendOrder, btnKeepShopping, btnClearCart;
    private ListView itemList;
    private MealListAdapter adapter;

    private double sumPrice = 0;

    private ArrayList<Integer> shoppingItems;
    public static ArrayList<Integer> numberOfAppearences = new ArrayList<>();
    public static ArrayList<Integer> soloAppearanceItem = new ArrayList<>();
    private ArrayList<Meal> shoppedMeals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        getShoppingItems();
        setUpListView();

    }

    private void initViews(){

        //TextViews
        cartHeader = findViewById(R.id.cartHeader);
        textTotal = findViewById(R.id.textTotal);
        textSumPrice = findViewById(R.id.textSumPrice);

        //Buttons
        btnSendOrder = findViewById(R.id.btnSendOrder);
        btnKeepShopping = findViewById(R.id.btnContinueShopping);
        btnClearCart = findViewById(R.id.btnClearCart);
        btnClearCart.setOnClickListener(this);
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
                    shoppedMeals.add(newShoppedMeal);
                    }
                    setTexts();
                }
            }).start();

    }


    private void setUpListView(){

        //ListView
        itemList = findViewById(R.id.itemView);

        adapter = new MealListAdapter(getApplicationContext(), shoppedMeals);
        itemList.setAdapter(adapter);

        itemList.setOnItemLongClickListener(   new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                itemLongClicked(position);
                return false;
            }
        });

    }

    private void getTotalPrice(){

        for (int i = 0; i < shoppedMeals.size(); i++){

            sumPrice += shoppedMeals.get(i).getPrice();
        }

    }

    private void updatePrice(int position){

        double minusPrice = shoppedMeals.get(position).getPrice();
        sumPrice = sumPrice - minusPrice;
        textSumPrice.setText(new DecimalFormat("##.##").format(sumPrice)+"€");



    }



    private void setTexts(){
        getTotalPrice();
        textSumPrice.setText(new DecimalFormat("##.##").format(sumPrice)+ "€" );

//        Intent intent = new Intent (this, OrderSubmitted.class);
//        String totalSum = Double.toString(sumPrice);
//        intent.putExtra("TotalSum",totalSum);


    }

    private void clearShoppingCart(){

        sumPrice = 0;
        shoppingItems.clear();
        shoppedMeals.clear();
        adapter.notifyDataSetChanged();
        textSumPrice.setText( 0 + "€");
    }

    private void itemLongClicked(int position){

        updatePrice(position);
        shoppingItems.remove(position);
        shoppedMeals.remove(position);
        adapter.notifyDataSetChanged();

    }

    private void sortItems(){
        Collections.sort(shoppingItems);
        numberOfAppearences.add(Collections.frequency(shoppingItems, shoppingItems.get(0)));
        soloAppearanceItem.add(shoppingItems.get(0));
        for( int i = 1; i < shoppingItems.size(); i++){
            if (shoppingItems.get(i) != shoppingItems.get(i -1)){
                numberOfAppearences.add(Collections.frequency(shoppingItems, shoppingItems.get(i)));
                soloAppearanceItem.add(shoppingItems.get(i));
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnContinueShopping:
                Intent intent = new Intent(Cart.this, MainJames.class);
                startActivity(intent);
                break;
            case R.id.btnSendOrder:
                sortItems();
                Intent getToTimer = new Intent(Cart.this, TimerActivity.class);
                //String totalSum = Double.toString(sumPrice);
               // getToTimer.putExtra("TotalSum",totalSum);
                startActivity(getToTimer);
                break;
            case R.id.btnClearCart:
                clearShoppingCart();
                Toast.makeText(this, "Warenkorb geleert", Toast.LENGTH_SHORT).show();
        }

    }

}
