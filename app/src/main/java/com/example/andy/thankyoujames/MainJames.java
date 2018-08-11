package com.example.andy.thankyoujames;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainJames extends FragmentActivity implements View.OnClickListener {

    private final String DATABASE_NAME = "mealDB";

    private ImageView   headerImage;
    private TextView    headerText, offerText;
    private Button      headerBurger, headerShopping;
    private ImageButton offerOne, offerTwo;

    private BurgerMenu  burgerMenu;
    private FragmentManager burgerManager;
    private FragmentTransaction burgerTransaction;
    private boolean fragmentGetsShown = false;

    public static MealDatabase mealDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_james);
        initView();
        initFragment();
        initDB();
        deleteDatabaseEntries();
        fillDatabase();

    }

    private void initDB(){
        mealDatabase = Room.databaseBuilder(getApplicationContext(), MealDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }


    private Meal createNewMeal(int id, int nameID, int descriptionID, int drawableID, double priceID){

        Meal meal = new Meal();
        meal.setMealID(id);
        meal.setMealName(getString(nameID));
        meal.setPrice(priceID);
        meal.setImageID(drawableID);
        meal.setDescription(getString(descriptionID));

        return meal;
    }

    private void fillDatabase(){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if( mealDatabase.daoAccess().numberOfRows() == 0) {
                        //Kaffee
                    Meal milchkaffee = createNewMeal(Constants.MILCHKAFFEE_ID, R.string.milchkaffee_name, R.string.milchkaffee_des, R.drawable.test_image, Constants.milchkaffee_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(milchkaffee);
                    Meal cappuccino = createNewMeal(Constants.CAPPUCCINO_ID, R.string.cappu_name, R.string.cappu_des, R.drawable.test_image, Constants.cappu_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(cappuccino);
                    Meal espresso = createNewMeal(Constants.ESPRESSO_ID, R.string.espresso_name, R.string.espresso_des,  R.drawable.test_image, Constants.espresso_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(espresso);
                        //Muesli
                    Meal schokomu = createNewMeal(Constants.SCHOKOMUESLI, R.string.schokomu_name, R.string.schokomu_des,R.drawable.test_image, Constants.schokomu_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(schokomu);
                    Meal fruchtmu = createNewMeal(Constants.FRUCHTMUESLI, R.string.fruchtmu_name, R.string.fruchtmu_des, R.drawable.test_image, Constants.fruchtmu_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(fruchtmu);
                    Meal nussmu = createNewMeal(Constants.NUSSMUESLI, R.string.nussmu_name, R.string.nussmu_des, R.drawable.test_image, Constants.nussmu_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(nussmu);
                        //Bagels
                    Meal sweetbagel = createNewMeal(Constants.SWEETBAGEL, R.string.sweetbagel_name, R.string.sweetbagel_des, R.drawable.test_image, Constants.sweetbagel_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(sweetbagel);
                    Meal freshbagel = createNewMeal(Constants.FRESHBAGEL, R.string.freshbagel_name, R.string.freshbagel_des, R.drawable.test_image, Constants.freshbagel_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(freshbagel);
                    Meal defaultBagel = createNewMeal(Constants.DEFAULTBAGEL, R.string.defaultbagel_name, R.string.defaultbagel_des, R.drawable.test_image, Constants.defaultbagel_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(defaultBagel);
                        //Suppe
                    Meal tomatensuppe = createNewMeal(Constants.TOAMTENSUPPE, R.string.tomatensuppe_name, R.string.toamtensuppe_des, R.drawable.test_image,Constants.toamtensuppe_price);
                    mealDatabase.daoAccess().insertOnlySingleFriend(tomatensuppe);
    }}
            }).start();


    }

    private void deleteDatabaseEntries(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDatabase.daoAccess().nukeTable();
            }
        }).start();
    }

    private void initFragment(){

        //BurgerMenu
        burgerMenu = new BurgerMenu();
       getSupportFragmentManager().beginTransaction().add(R.id.fragment_mainJames, burgerMenu).commit();
       getSupportFragmentManager().beginTransaction().hide(burgerMenu).commit();
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

    private void startOfferIntent(int offerID){
        Intent intent = new Intent(MainJames.this, ItemClass.class);
        intent.putExtra("finalFoodID", offerID);
        startActivity(intent);

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
                startOfferIntent(Constants.MILCHKAFFEE_ID);
                break;
            case R.id.main_james_offer_two:
                startOfferIntent(Constants.SWEETBAGEL);
                break;
        }

    }
}
