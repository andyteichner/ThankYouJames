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

//This is the main shopping cart activity. It displays all the products the user selected, as well as the total price and gives the options to
// delete either parts or the entire content of the cart. Its also gives the options to continue shopping or finalise the order.
public class Cart extends Activity implements View.OnClickListener{

    private TextView cartHeader, textTotal, textSumPrice;
    private Button  btnSendOrder, btnKeepShopping, btnClearCart;
    private ListView itemList;
    private MealListAdapter adapter;

    private double sumPrice = 0;

    private ArrayList<Integer> shoppingItems;
    public static ArrayList<Integer> numberOfAppearances = new ArrayList<>();
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

    // The following two methods are used to get all products selected by the user in order to display them later on.
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


    //To give the user a easy overlook on what he selected, the meals get shown in this ListView with the essential information.
    //To display the them properly, we used a custom adapter for meals.
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

    //The next three methods are used do show the current price.
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

    }

    //Clears the cart in its entirety.
    private void clearShoppingCart(){

        sumPrice = 0;
        shoppingItems.clear();
        shoppedMeals.clear();
        adapter.notifyDataSetChanged();
        textSumPrice.setText( 0 + "€");
    }

    //Deletes single selected items from the cart.
    private void itemLongClicked(int position){

        updatePrice(position);
        shoppingItems.remove(position);
        shoppedMeals.remove(position);
        adapter.notifyDataSetChanged();

    }

    //In order to give the user a final overview of his order on the last activity "OrderSubmitted", we want to show him or her
    // the items with the ordered amount. To get these information form the cart, we first sort them. After that we go through the
    // "shoppingItems" and check the amount for each new item. The amount will be saved in the list "numberOfAppearances" and which item it is
    // in the list "soloAppearanceItem". These will be accessible for the "OrderSubmitted" Activity. There is a bug with the sorting algorithm,
    // which we explain in our documentary.
    private void sortItems(){
        Collections.sort(shoppingItems);
        numberOfAppearances.add(Collections.frequency(shoppingItems, shoppingItems.get(0)));
        soloAppearanceItem.add(shoppingItems.get(0));
        for( int i = 1; i < shoppingItems.size(); i++){
            if (shoppingItems.get(i) != shoppingItems.get(i -1)){
                numberOfAppearances.add(Collections.frequency(shoppingItems, shoppingItems.get(i)));
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
                if ( sumPrice == 0){
                    Toast.makeText(this, R.string.noOrder, Toast.LENGTH_SHORT).show();
                }else{
                sortItems();
                Intent getToTimer = new Intent(Cart.this, TimerActivity.class);
                startActivity(getToTimer);}
                break;
            case R.id.btnClearCart:
                clearShoppingCart();
                Toast.makeText(this, "Warenkorb geleert", Toast.LENGTH_SHORT).show();
        }

    }

}
