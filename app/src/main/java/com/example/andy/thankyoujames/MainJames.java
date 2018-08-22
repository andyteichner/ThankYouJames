package com.example.andy.thankyoujames;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActionBar;

public class MainJames extends FragmentActivity implements View.OnClickListener {

    private final String DATABASE_NAME = "mealDB";

    private ImageView   headerImage;
    private TextView    headerText, offerText;
    private ImageButton offerOne, offerTwo, headerBurger, headerShopping;;

    private BurgerMenu  burgerMenu;
    private FragmentTransaction burgerTransaction;

    public static MealDatabase mealDatabase;

    public static final String CHANNEL_ID = "foodisready";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_james);
        initView();
        initFragment();
        initDB();
        createNotificationChannel();
        deleteDatabaseEntries();
        fillDatabase();
        //updateForOffers();


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

    //Quelle:https://codinginflow.com/tutorials/android/foreground-service
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Essen ist fertig",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }

    }

    private void fillDatabase(){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if( mealDatabase.daoAccess().numberOfRows() == 0) {
                        //Kaffee
                    Meal milchkaffee = createNewMeal(Constants.MILCHKAFFEE_ID, R.string.milchkaffee_name, R.string.milchkaffee_des, R.drawable.coffee, Constants.milchkaffee_price);
                    mealDatabase.daoAccess().insertMealIntoDB(milchkaffee);
                    Meal cappuccino = createNewMeal(Constants.CAPPUCCINO_ID, R.string.cappu_name, R.string.cappu_des, R.drawable.coffee, Constants.cappu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(cappuccino);
                    Meal espresso = createNewMeal(Constants.ESPRESSO_ID, R.string.espresso_name, R.string.espresso_des,  R.drawable.coffee, Constants.espresso_price);
                    mealDatabase.daoAccess().insertMealIntoDB(espresso);
                        //Muesli
                    Meal schokomu = createNewMeal(Constants.SCHOKOMUESLI, R.string.schokomu_name, R.string.schokomu_des,R.drawable.test_image, Constants.schokomu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(schokomu);
                    Meal fruchtmu = createNewMeal(Constants.FRUCHTMUESLI, R.string.fruchtmu_name, R.string.fruchtmu_des, R.drawable.test_image, Constants.fruchtmu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(fruchtmu);
                    Meal nussmu = createNewMeal(Constants.NUSSMUESLI, R.string.nussmu_name, R.string.nussmu_des, R.drawable.test_image, Constants.nussmu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(nussmu);
                        //Bagels
                    Meal sweetbagel = createNewMeal(Constants.SWEETBAGEL, R.string.sweetbagel_name, R.string.sweetbagel_des, R.drawable.test_image, Constants.sweetbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(sweetbagel);
                    Meal freshbagel = createNewMeal(Constants.FRESHBAGEL, R.string.freshbagel_name, R.string.freshbagel_des, R.drawable.test_image, Constants.freshbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(freshbagel);
                    Meal defaultBagel = createNewMeal(Constants.DEFAULTBAGEL, R.string.defaultbagel_name, R.string.defaultbagel_des, R.drawable.test_image, Constants.defaultbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(defaultBagel);
                    /*    //Suppe
                    Meal tomatensuppe = createNewMeal(Constants.TOAMTENSUPPE, R.string.tomatensuppe_name, R.string.toamtensuppe_des, R.drawable.test_image,Constants.toamtensuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tomatensuppe);
                    Meal spargelsupee = createNewMeal(Constants.SPARGELSUPPE, R.string.spargelsuppe_name, R.string.spargelsuppe_des, R.drawable.test_image,Constants.spargelsuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tomatensuppe);
                    Meal festtagssuppe = createNewMeal(Constants.FESTTAGSSUPEE, R.string.festtagssuppe_name, R.string.festtagssuppe_des, R.drawable.test_image, Constants.festtagssuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(festtagssuppe);
                        //Pasta
                    Meal carbonara = createNewMeal(Constants.PASTA_CARBONARA, R.string.pasta_carbonara_name, R.string.pasta_carbonara_des, R.drawable.test_image, Constants.pasta_carbonara_price);
                    mealDatabase.daoAccess().insertMealIntoDB(carbonara);
                    Meal napoli = createNewMeal(Constants.PASTA_NAPOLI, R.string.pasta_napoli_name, R.string.pasta_napoli_des, R.drawable.test_image, Constants.pasta_napoli_price);
                    mealDatabase.daoAccess().insertMealIntoDB(napoli);
                    Meal tuna = createNewMeal(Constants.PASTA_TUNA, R.string.pasta_tuna_name, R.string.pasta_tuna_des, R.drawable.test_image, Constants.pasta_tuna_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tuna);
                        //Sandwiches
                    Meal hamSandwich = createNewMeal(Constants.SANDWICH_HAM, R.string.sandwich_ham_name, R.string.sandwich_ham_des, R.drawable.test_image, Constants.sandwich_ham_price);
                    mealDatabase.daoAccess().insertMealIntoDB(hamSandwich);
                    Meal tomatoSandwich = createNewMeal(Constants.SANDWICH_TOMATE, R.string.sandwich_tomate_name, R.string.sandwich_tomate_des, R.drawable.test_image, Constants.sandwich_tomate_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tomatoSandwich);
                    Meal chickenSandwich = createNewMeal(Constants.SANDWICH_CHICKEN, R.string.sandwich_chicken_name, R.string.sandwich_chicken_des, R.drawable.test_image, Constants.sandwich_chicken_price);
                    mealDatabase.daoAccess().insertMealIntoDB(chickenSandwich);*/

                        Meal offerMealOne = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_ONE);
                        Meal offerMealTwo = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_TWO);
                        double offerPriceOne = offerMealOne.getPrice() * 0.7;
                        double offerPriceTwo = offerMealTwo.getPrice() * 0.7;
                        mealDatabase.daoAccess().updateMealPrice(offerPriceOne, Constants.OFFER_ONE);
                        mealDatabase.daoAccess().updateMealPrice(offerPriceTwo, Constants.OFFER_TWO);

                    }}
            }).start();
    }

    /*private void updateForOffers( ){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Meal offerMealOne = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_ONE);
                Meal offerMealTwo = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_TWO);
                double offerPriceOne = offerMealOne.getPrice() * 0.7;
                double offerPriceTwo = offerMealTwo.getPrice() * 0.7;
                mealDatabase.daoAccess().updateMealPrice(offerPriceOne, Constants.OFFER_ONE);
                mealDatabase.daoAccess().updateMealPrice(offerPriceTwo, Constants.OFFER_TWO);

            }
        }).start();
    }*/

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
                Intent intent = new Intent(MainJames.this, Cart.class);
                startActivity(intent);
                break;
            case R.id.main_james_offer_one:
                startOfferIntent(Constants.OFFER_ONE);
                break;
            case R.id.main_james_offer_two:
                startOfferIntent(Constants.OFFER_TWO);
                break;
        }

    }
}
