package com.example.andy.thankyoujames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu_2 extends AppCompatActivity implements View.OnClickListener{

    private TextView headerText;
    private Button headerShopping, headerBurger, meal_no_1, meal_no_2, meal_no_3;
    private ImageView headerImage;

    private int menuID, foodID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_2);
        initViews();
        getIds();
        setTexts(menuID, foodID);
    }

    private void initViews(){

        //TextViews
        headerText = findViewById(R.id.header_text_menu1);

        //Buttons
        headerBurger = findViewById(R.id.header_burger_button_menu1);
        headerShopping = findViewById(R.id.header_shopping_button_menu1);

        //ImageViews
        headerImage = findViewById(R.id.header_image_menu1);

        //ImageButtons
        meal_no_1 = findViewById(R.id.supermeal_no1);
        meal_no_2 = findViewById(R.id.supermeal_no2);
        meal_no_3 = findViewById(R.id.supermeal_no3);

    }

    private void getIds (){
        menuID = getIntent().getExtras().getInt("MenuID");
        foodID = getIntent().getExtras().getInt("FoodID");

    }

    //in dieser Methode sollen die Texte und Bilder angepasst werden, jenachdem welches Menü ausgewählt worden ist
    private void setTexts (int menuIdentifier, int foodIdentifier){

        switch ( menuIdentifier){
            case 1:
                switch (foodIdentifier){
                    case 1:
                        meal_no_1.setText(R.string.coffee_1);
                        meal_no_2.setText(R.string.coffee_2);
                        meal_no_3.setText(R.string.coffee_3);
                        break;
                    case 2:
                        meal_no_1.setText(R.string.muesli_1);
                        meal_no_2.setText(R.string.muesli_2);
                        meal_no_3.setText(R.string.muesli_3);
                        break;
                    case 3:
                        meal_no_1.setText(R.string.beagle_1);
                        meal_no_2.setText(R.string.beagle_2);
                        meal_no_3.setText(R.string.beagle_3);
                        break;
                }
            case 2:
                switch (foodIdentifier){
                    case 1:
                        meal_no_1.setText(R.string.soup_1);
                        meal_no_2.setText(R.string.soup_2);
                        meal_no_3.setText(R.string.soup_3);
                        break;
                    case 2:
                        meal_no_1.setText(R.string.pasta_1);
                        meal_no_2.setText(R.string.pasta_2);
                        meal_no_3.setText(R.string.pasta_3);
                        break;
                    case 3:
                        meal_no_1.setText(R.string.sandwich_1);
                        meal_no_2.setText(R.string.sandwich_2);
                        meal_no_3.setText(R.string.sandwich_3);
                        break;
                }
            case 3:
                switch (foodIdentifier){
                    case 1:
                        meal_no_1.setText(R.string.pizza_1);
                        meal_no_2.setText(R.string.pizza_2);
                        meal_no_3.setText(R.string.pizza_3);
                        break;
                    case 2:
                        meal_no_1.setText(R.string.meat_1);
                        meal_no_2.setText(R.string.meat_2);
                        meal_no_3.setText(R.string.meat_3);
                        break;
                    case 3:
                        meal_no_1.setText(R.string.fish_1);
                        meal_no_2.setText(R.string.fish_2);
                        meal_no_3.setText(R.string.fish_3);
                        break;
                }
        }

    }




    @Override
    public void onClick(View view) {
        // TODO: 30.07.2018 send intents machen, Auf die einzelnen Seiten der Gerichte kommen -> Vermutlich mit Datenbank
        switch (view.getId()){
            case R.id.header_burger_button:
                break;
            case R.id.header_shopping_button:
                break;
            case R.id.supermeal_no1:
                break;
            case R.id.supermeal_no2:
                break;
            case R.id.supermeal_no3:
                break;
        }
    }
}
