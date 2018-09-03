package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu_2 extends Activity implements View.OnClickListener{

    private TextView headerText;
    private Button headerShopping, headerBurger, meal_no_1, meal_no_2, meal_no_3;
    private ImageView headerImage;

    private int menuID, foodID, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_2);
        initViews();
        getIds();
        setTexts(menuID, foodID);
    }

    private void initViews(){

        //Buttons
        headerBurger = findViewById(R.id.header_burger_button_menu2);
        headerShopping = findViewById(R.id.header_shopping_button_menu2);

        //ImageViews
        headerImage = findViewById(R.id.header_image_menu2);

        //Buttons
        meal_no_1 = findViewById(R.id.submeal_no1_menu2);
        meal_no_2 = findViewById(R.id.submeal_no2_menu2);
        meal_no_3 = findViewById(R.id.submeal_no3_menu2);
        meal_no_1.setOnClickListener(this);
        meal_no_2.setOnClickListener(this);
        meal_no_3.setOnClickListener(this);
        headerShopping.setOnClickListener(this);

    }

    private void getIds (){
        menuID = getIntent().getExtras().getInt("MenuID");
        foodID = getIntent().getExtras().getInt("FoodID");

    }


    //in dieser Methode sollen die Texte und Bilder angepasst werden, jenachdem welches Menü ausgewählt worden ist
    private void setTexts (int menuIdentifier, int foodIdentifier){

        id = menuIdentifier*10+foodIdentifier;


                switch (id){
                    case 11:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.coffeeimage));
                        meal_no_1.setText(R.string.coffee_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.milkcoffeeicon),null, null, null);
                        meal_no_2.setText(R.string.coffee_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.cappuccinoicon),null, null, null);
                        meal_no_3.setText(R.string.coffee_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.espressoicon),null, null, null);
                        break;
                    case 12:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.muesliimage));
                        meal_no_1.setText(R.string.muesli_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.chocolatemuesliicon),null, null, null);
                        meal_no_2.setText(R.string.muesli_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.fruitmuesliicon),null, null, null);
                        meal_no_3.setText(R.string.muesli_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.nutmuesliicon),null, null, null);
                        break;
                    case 13:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.bagelimage));
                        meal_no_1.setText(R.string.bagel_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.sweetbagelicon),null, null, null);
                        meal_no_2.setText(R.string.bagel_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.freshbagelicon),null, null, null);
                        meal_no_3.setText(R.string.bagel_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.salmonicon),null, null, null);
                        break;
                    case 21:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.soupimage));
                        meal_no_1.setText(R.string.soup_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.tomatosoupicon),null, null, null);
                        meal_no_2.setText(R.string.soup_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.asparagussoupicon),null, null, null);
                        meal_no_3.setText(R.string.soup_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.festtagssoupicon),null, null, null);
                        break;
                    case 22:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.pastaimage));
                        meal_no_1.setText(R.string.pasta_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.carbonaraicon),null, null, null);
                        meal_no_2.setText(R.string.pasta_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.napoliicon),null, null, null);
                        meal_no_3.setText(R.string.pasta_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.tunaicon),null, null, null);
                        break;
                    case 23:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.sandwichimage));
                        meal_no_1.setText(R.string.sandwich_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.hamicon),null, null, null);
                        meal_no_2.setText(R.string.sandwich_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.salamiicon),null, null, null);
                        meal_no_3.setText(R.string.sandwich_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.chickenicon),null, null, null);
                        break;
                    case 31:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.pizzaimage));
                        meal_no_1.setText(R.string.pizza_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.hawaiiicon),null, null, null);
                        meal_no_2.setText(R.string.pizza_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.margaritaicon),null, null, null);
                        meal_no_3.setText(R.string.pizza_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.spezialicon),null, null, null);
                        break;
                    case 32:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.meatimage));
                        meal_no_1.setText(R.string.meat_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.burgermeaticon),null, null, null);
                        meal_no_2.setText(R.string.meat_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.steakicon),null, null, null);
                        meal_no_3.setText(R.string.meat_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.schnitzelicon),null, null, null);
                        break;
                    case 33:
                        headerImage.setImageDrawable(getResources().getDrawable(R.drawable.fishimage));
                        meal_no_1.setText(R.string.fish_1);
                        meal_no_1.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.fishandchipsicon),null, null, null);
                        meal_no_2.setText(R.string.fish_2);
                        meal_no_2.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.salmonicon),null, null, null);
                        meal_no_3.setText(R.string.fish_3);
                        meal_no_3.setCompoundDrawablesWithIntrinsicBounds(
                                getResources().getDrawable(R.drawable.calamariicon),null, null, null);
                        break;
                }
        }

        private void finalMenuSelect(int i ){
            int finalFoodID = id*10+i;
            Intent menuSelect = new Intent(Menu_2.this, ItemClass.class);
            menuSelect.putExtra("finalFoodID", finalFoodID);
            startActivity(menuSelect);

        }






    @Override
    public void onClick(View view) {
        // TODO: 30.07.2018 send intents machen, Auf die einzelnen Seiten der Gerichte kommen -> Vermutlich mit Datenbank
        switch (view.getId()){
            case R.id.header_burger_button_menu2:
                break;
            case R.id.header_shopping_button_menu2:
                Intent goToCart = new Intent(this,Cart.class);
                startActivity(goToCart);
                break;
            case R.id.submeal_no1_menu2:
               finalMenuSelect(1);
                break;
            case R.id.submeal_no2_menu2:
               finalMenuSelect(2);
                break;
            case R.id.submeal_no3_menu2:
                finalMenuSelect(3);
                break;
        }
    }
}
