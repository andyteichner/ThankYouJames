package com.example.andy.thankyoujames;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemClass extends Activity implements View.OnClickListener{

    private Button plusButton, minusButton, shoppingCart;
    private TextView nameTag, descriptionTag, counterText;
    private ImageView mealImage;

    private int counter1 = 1;
    private int finalFoodID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_class);
        initViews();
        getFinalID();
        setTexts();
    }

    private void getFinalID(){
        finalFoodID = getIntent().getExtras().getInt("finalFoodID");

    }

    private void setTexts(){
        counterText.setText("1");
        String idAsString = ""+ finalFoodID;
        nameTag.setText(idAsString);
        descriptionTag.setText(idAsString);
    }

    private void initViews(){

        //Buttons
        plusButton = findViewById(R.id.counter_plus);
        minusButton = findViewById(R.id.counter_minus);
        shoppingCart = findViewById(R.id.shopping_button);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        shoppingCart.setOnClickListener(this);

        //TextView

        nameTag = findViewById(R.id.name_tag);
        descriptionTag = findViewById(R.id.description_tag);
        counterText = findViewById(R.id.counter);

        //Image
        mealImage = findViewById(R.id.meal_image);
    }

    private void updateCounter(int i ){
        if (i ==1 ){
            counter1++;
            String counterAsString = ""+counter1;
            counterText.setText(counterAsString);
        } else if( i == 0 && counter1 > 1){
            counter1 --;
            String counterAsString = ""+counter1;
            counterText.setText(counterAsString);
        }else if (i == 0 && counter1 == 1){
            String counterAsString = ""+counter1;
            counterText.setText(counterAsString);
        }
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.counter_plus:
                updateCounter(1);
                break;
            case R.id.counter_minus:
                updateCounter(0);
                break;
            case R.id.shopping_button:
                break;
        }
    }
}
