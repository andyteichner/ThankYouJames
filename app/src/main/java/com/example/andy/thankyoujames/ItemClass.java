package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends Activity implements View.OnClickListener{

    private Button plusButton, minusButton, shoppingCart;
    private TextView nameTag, descriptionTag, counterText;
    private ImageView mealImage;

    private int counter1 = 1;
    private int finalFoodID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
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

    private int updateCounter(int i ) {
        int quantity = counter1;
        if (i == 1) {
            counter1++;
            String counterAsString = "" + counter1;
            counterText.setText(counterAsString);
        } else if (i == 0 && counter1 > 1) {
            counter1--;
            String counterAsString = "" + counter1;
            counterText.setText(counterAsString);
        } else if (i == 0 && counter1 == 1) {
            String counterAsString = "" + counter1;
            counterText.setText(counterAsString);
        }

        return quantity;
        //checken, ob Logik hinter Rückgabe des Quantity-Werts stimmt
    }

    //Hier noch überlegen, wie Daten in Cart gespeichert werden können, ohne sofort auf Cart-Activity zu wechseln
    //und stattdessen nur kurzer Toast, dass Item zu Cart hinzugefügt wurde
    private void addToCart(int quantity){
        Intent intent = new Intent(ProductActivity.this, Cart.class);
        intent.putExtra("finalFoodID",finalFoodID);
        intent.putExtra("itemQuantity",quantity);
        //Toast.makeText(this,"Item zum Warenkorb hinzugefügt", Toast.LENGTH_SHORT).show();
        startActivity(intent);
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
                addToCart(counter1);
                break;
        }
    }
}
