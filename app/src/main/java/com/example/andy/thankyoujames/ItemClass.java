package com.example.andy.thankyoujames;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//The ItemClass activity functions as the final stage of the menu selection. Here the ID, which was created by selection through out the previous
// menus, gets translated into the final meal with its information and picture. With a connection to the database via DaoAccess the specific
// content for each meal can be gathered only by the ID itself. With a instance of the "ShoppingCart" the wanted amount of the meal can be added to
// the cart when clicking "zum Warenkorb hinzufuegen".
public class ItemClass extends FragmentActivity implements View.OnClickListener{

    private Button plusButton, minusButton, shoppingCartButton, headerBurger, headerShopping;
    private TextView nameTag, descriptionTag, counterText, priceTag;
    private ImageView ImageTag;


    private int counter1 = 1;
    private int finalFoodID;
    private boolean isOfferWitchDiscount = false;
    private String selectedMeal;

    private BurgerMenu  burgerMenu;
    private FragmentTransaction burgerTransaction;

    public static ShoppingCart shoppingCart = new ShoppingCart();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_class);
        initViews();
        initFragment();
        getFinalID();
        checkForOffer();
        setTexts();

    }

    private void getFinalID() {
        finalFoodID = getIntent().getExtras().getInt("finalFoodID");

    }

    // Checks if the selected meal is a offer.
    private void checkForOffer(){
            if(finalFoodID == Constants.OFFER_ONE || finalFoodID == Constants.OFFER_TWO){
                isOfferWitchDiscount = true;
            }else {
                isOfferWitchDiscount = false;
            }
    }



// Here we access the database via the DaoAccess in extra Thread. We can gather the all information from the Database and update the texts and
// images. If the meal is a offer the price will be shown in green.
    private void setTexts(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Meal resultMeal;
                resultMeal = MainJames.mealDatabase.daoAccess().fetchOneMealbyMealID(finalFoodID);
                if (resultMeal != null){
                    final String mealName = resultMeal.getMealName();
                    selectedMeal = resultMeal.getMealName();
                    final String mealDes = resultMeal.getDescription();
                    double realPrice = resultMeal.getPrice();
                    final String mealPrice =""+ realPrice;
                    final int mealImageID = resultMeal.getImageID();
                    final Drawable mealImage = getDrawable(mealImageID);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            nameTag.setText(mealName);
                            descriptionTag.setText(mealDes);
                            priceTag.setText(mealPrice + "€");
                            ImageTag.setImageDrawable(mealImage);
                            if (isOfferWitchDiscount){
                                priceTag.setTextColor(Color.GREEN);
                            }
                        }
                    });
                }
            }
        }).start();

    }

    private void initViews(){
        //Buttons
        plusButton = findViewById(R.id.counter_plus);
        minusButton = findViewById(R.id.counter_minus);
        shoppingCartButton = findViewById(R.id.shopping_button);
        headerBurger = findViewById(R.id.header_burger_button_itemcl);
        headerShopping = findViewById(R.id.header_shopping_button_itemcl);
        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        shoppingCartButton.setOnClickListener(this);
        headerBurger.setOnClickListener(this);
        headerShopping.setOnClickListener(this);

        //TextView

        nameTag = findViewById(R.id.name_tag);
        descriptionTag = findViewById(R.id.description_tag);
        priceTag = findViewById(R.id.price_tag);
        counterText = findViewById(R.id.counter);
        counterText.setText("1");

        //Image
        ImageTag = findViewById(R.id.meal_image);
    }

    //this operates the counter for how manny products you want to buy.
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

    private void initFragment(){
        burgerMenu = new BurgerMenu();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_itemcl, burgerMenu).commit();
        getSupportFragmentManager().beginTransaction().hide(burgerMenu).commit();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.header_burger_button_itemcl:
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
            case R.id.header_shopping_button_itemcl:
                Intent goToCart = new Intent(this,Cart.class);
                startActivity(goToCart);
                break;
            case R.id.counter_plus:
                updateCounter(1);
                break;
            case R.id.counter_minus:
                updateCounter(0);
                break;
            case R.id.shopping_button:
                // if u click this button, the amount of selected meals will be added to the cart.
                for (int i = 1; i <= counter1; i++){
                shoppingCart.addShoppingItem(finalFoodID);}
                Toast.makeText(this, ""+ counter1 + " "+selectedMeal+" zum Warenkorb hinzugefügt", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
