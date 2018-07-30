package com.example.andy.thankyoujames;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu_1 extends Activity implements View.OnClickListener{

    private TextView    headerText;
    private Button      headerShopping, headerBurger, meal_no_1, meal_no_2, meal_no_3;
    private ImageView   headerImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_1);
        initViews();
        // TODO: 30.07.2018 bei einkommenden Intent die Methode setTexts aufrufen  
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

    //in dieser Methode sollen die Texte und Bilder angepasst werden, jenachdem welches Menü ausgewählt worden ist 
    private void setTexts (){

        int menuIdentifier = getIntent().getExtras().getInt("Identifier");
        switch ( menuIdentifier){
            case 1:
                meal_no_1.setText(R.string.supermeal_morning_1);
                meal_no_2.setText(R.string.supermeal_morning_2);
                meal_no_3.setText(R.string.supermeal_morning_3);
                break;
            case 2:
                meal_no_1.setText(R.string.supermeal_noon_1);
                meal_no_2.setText(R.string.supermeal_noon_2);
                meal_no_3.setText(R.string.supermeal_noon_3);
                break;
            case 3:
                meal_no_1.setText(R.string.supermeal_evening_1);
                meal_no_2.setText(R.string.supermeal_evening_2);
                meal_no_3.setText(R.string.supermeal_evening_3);
                break;
        }

    }




    @Override
    public void onClick(View view) {
        // TODO: 30.07.2018 send intents machen  
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
