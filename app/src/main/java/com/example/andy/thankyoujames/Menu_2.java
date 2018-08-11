package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        //TextViews
        headerText = findViewById(R.id.header_text_menu2);

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
                        meal_no_1.setText(R.string.coffee_1);
                        meal_no_2.setText(R.string.coffee_2);
                        meal_no_3.setText(R.string.coffee_3);
                        break;
                    case 12:
                        meal_no_1.setText(R.string.muesli_1);
                        meal_no_2.setText(R.string.muesli_2);
                        meal_no_3.setText(R.string.muesli_3);
                        break;
                    case 13:
                        meal_no_1.setText(R.string.beagle_1);
                        meal_no_2.setText(R.string.beagle_2);
                        meal_no_3.setText(R.string.beagle_3);
                        break;
                    case 21:
                        meal_no_1.setText(R.string.soup_1);
                        meal_no_2.setText(R.string.soup_2);
                        meal_no_3.setText(R.string.soup_3);
                        break;
                    case 22:
                        meal_no_1.setText(R.string.pasta_1);
                        meal_no_2.setText(R.string.pasta_2);
                        meal_no_3.setText(R.string.pasta_3);
                        break;
                    case 23:
                        meal_no_1.setText(R.string.sandwich_1);
                        meal_no_2.setText(R.string.sandwich_2);
                        meal_no_3.setText(R.string.sandwich_3);
                        break;
                    case 31:
                        meal_no_1.setText(R.string.pizza_1);
                        meal_no_2.setText(R.string.pizza_2);
                        meal_no_3.setText(R.string.pizza_3);
                        break;
                    case 32:
                        meal_no_1.setText(R.string.meat_1);
                        meal_no_2.setText(R.string.meat_2);
                        meal_no_3.setText(R.string.meat_3);
                        break;
                    case 33:
                        meal_no_1.setText(R.string.fish_1);
                        meal_no_2.setText(R.string.fish_2);
                        meal_no_3.setText(R.string.fish_3);
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
            case R.id.header_burger_button:
                break;
            case R.id.header_shopping_button:
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
