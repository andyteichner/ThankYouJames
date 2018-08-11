package com.example.andy.thankyoujames;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainJames extends FragmentActivity implements View.OnClickListener {

    private ImageView   headerImage;
    private TextView    headerText, offerText;
    private Button      headerBurger, headerShopping;
    private ImageButton offerOne, offerTwo;

    private BurgerMenu  burgerMenu;
    private FragmentManager burgerManager;
    private FragmentTransaction burgerTransaction;
    private boolean fragmentGetsShown = false;

    private MealDatabase mealDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_james);
        initView();
        initFragment();

    }

    private void initDB(){
        mealDatabase = Room.databaseBuilder
    }

    private void initFragment(){

        //BurgerMenu
        burgerMenu = new BurgerMenu();
       burgerManager = getSupportFragmentManager();
    }

    private void initView(){
        // Buttons
        headerBurger = findViewById(R.id.header_burger_button);
        headerShopping = findViewById(R.id.header_shopping_button);
        headerBurger.setOnClickListener(this);
        headerShopping.setOnClickListener(this);

        // ImageViews
        headerImage = findViewById(R.id.header_image);

        // TextViews
        headerText = findViewById(R.id.header_text);
        offerText = findViewById(R.id.main_james_offer_text);

        // ImageButtons
        offerOne = findViewById(R.id.main_james_offer_one);
        offerTwo = findViewById(R.id.main_james_offer_two);
        offerOne.setOnClickListener(this);
        offerTwo.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        // TODO: 29.07.2018  intents für die Angebote sowie das Fragment und den Warenkorb
        switch (view.getId()){
            case R.id.header_burger_button:
               burgerTransaction = getSupportFragmentManager().beginTransaction();
                burgerTransaction.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                if (burgerMenu.isHidden()) {
                    burgerTransaction.show(burgerMenu);

                } else {
                    burgerTransaction.hide(burgerMenu);
                }
                burgerTransaction.commit();

                break;
            case R.id.header_shopping_button:
                break;
            case R.id.main_james_offer_one:
                break;
            case R.id.main_james_offer_two:
                break;
        }

    }
}
