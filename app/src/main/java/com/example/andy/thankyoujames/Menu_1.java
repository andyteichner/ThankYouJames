package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu_1 extends Activity implements View.OnClickListener {

    private TextView headerText;
    private Button headerShopping, headerBurger, meal_no_1, meal_no_2, meal_no_3;
    private ImageView headerImage;


    private int menuIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_1);
        menuIdentifier = getIntent().getExtras().getInt("Identifier");
        initViews();
        setTexts();


    }

    private void initViews() {

        //Buttons
        headerBurger = findViewById(R.id.header_burger_button_menu1);
        headerShopping = findViewById(R.id.header_shopping_button_menu1);

        //ImageViews
        headerImage = findViewById(R.id.header_image_menu1);

        //Buttons zum Menuewechsel
        meal_no_1 = findViewById(R.id.supermeal_no1);
        meal_no_2 = findViewById(R.id.supermeal_no2);
        meal_no_3 = findViewById(R.id.supermeal_no3);
        meal_no_1.setOnClickListener(this);
        meal_no_2.setOnClickListener(this);
        meal_no_3.setOnClickListener(this);
        headerShopping.setOnClickListener(this);

    }



    //in dieser Methode sollen die Texte und Bilder angepasst werden, jenachdem welches Menü ausgewählt worden ist 
    private void setTexts() {

        switch (menuIdentifier) {
            case 1:
                //headerImage noch was raussuchen für case 1 Frühstück, case 2 Mittag, case 3 Abendessen
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.morgenimage));
                meal_no_1.setText(R.string.supermeal_morning_1);
                meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                         getResources().getDrawable(R.drawable.coffeeicon),null, null, null);
                meal_no_2.setText(R.string.supermeal_morning_2);
                meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.muesliicon),null, null, null);
                meal_no_3.setText(R.string.supermeal_morning_3);
                meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.bagelicon),null, null, null);
                break;
            case 2:
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.mittagimage));
                meal_no_1.setText(R.string.supermeal_noon_1);
                meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.soupicon),null, null, null);
                meal_no_2.setText(R.string.supermeal_noon_2);
                meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.pastaicon),null, null, null);
                meal_no_3.setText(R.string.supermeal_noon_3);
                meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.sandwichicon),null, null, null);
                break;
            case 3:
                headerImage.setImageDrawable(getResources().getDrawable(R.drawable.abendimage));
                meal_no_1.setText(R.string.supermeal_evening_1);
                meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.pizzaicon),null, null, null);
                meal_no_2.setText(R.string.supermeal_evening_2);
                meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.meaticon),null, null, null);
                meal_no_3.setText(R.string.supermeal_evening_3);
                meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                        getResources().getDrawable(R.drawable.fishicon),null, null, null);
                break;
        }

    }

    private void sendIntents(int id) {

        Intent menuSelect = new Intent(Menu_1.this, Menu_2.class);
        menuSelect.putExtra("MenuID", menuIdentifier);
        menuSelect.putExtra("FoodID", id);
        startActivity(menuSelect);
    }


        @Override
        public void onClick (View view){

            switch (view.getId()) {
                case R.id.header_burger_button_menu1:
                    break;
                case R.id.header_shopping_button_menu1:
                    Intent goToCart = new Intent(this,Cart.class);
                    startActivity(goToCart);
                    break;
                case R.id.supermeal_no1:
                    sendIntents(1);
                    break;
                case R.id.supermeal_no2:
                    sendIntents(2);
                    break;
                case R.id.supermeal_no3:
                    sendIntents(3);
                    break;
            }
        }
    }

