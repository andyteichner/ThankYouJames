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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


// This is the main activity of our app. Its used to get the user started when ordering food.
// It gives the user two special offers right at the start, to get him interested and uses a burger menu fragment
// to ease the navigation through the app when looking for food
public class MainJames extends FragmentActivity implements View.OnClickListener {



    private ImageView   headerImage;
    private TextView    headerText, offerText;
    private ImageButton offerOne, offerTwo, headerBurger, headerShopping;;

    private BurgerMenu  burgerMenu;
    private FragmentTransaction burgerTransaction;

    public static MealDatabase mealDatabase;
    private final String DATABASE_NAME = "mealDB";

    public static final String CHANNEL_ID = "foodisready";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_james);
        initView();
        initFragment();
        initDB();
        createNotificationChannel();
        fillDatabase();

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


// Quelle: https://www.youtube.com/watch?v=i22INe14JUc
    private void initFragment(){
        //BurgerMenu
        burgerMenu = new BurgerMenu();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_mainJames, burgerMenu).commit();
        getSupportFragmentManager().beginTransaction().hide(burgerMenu).commit();
    }

    // Here the Database, that is used for the storing the information about the meals, gets created. We used a Room Database, according to
    // the exercises during the semester.
    private void initDB(){
        mealDatabase = Room.databaseBuilder(getApplicationContext(), MealDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
    }


    // The Database stores Meal objects, in order to gather all the needed information in a simplified manner. To achieve a clearer structure when
    // filling the Database, we sourced the creation of the meals out into this one.
    private Meal createNewMeal(int id, int nameID, int descriptionID, int drawableID, double priceID){

        Meal meal = new Meal();
        meal.setMealID(id);
        meal.setMealName(getString(nameID));
        meal.setPrice(priceID);
        meal.setImageID(drawableID);
        meal.setDescription(getString(descriptionID));

        return meal;
    }



    //In this method all the information gets loaded into the database. For all the meals we create a object with the needed information and
    // and create an entry in out DB. Before that we check, if the database is already loaded. We gather the information from the string xml and the
    //Constants class. To keep consistency among the app, the 30% offer for the selected meals gets applied at the end of the method and updated
    // into the DB.
    private void fillDatabase(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                if( mealDatabase.daoAccess().numberOfRows() == 0) {
                    //Kaffee
                    Meal milchkaffee = createNewMeal(Constants.MILCHKAFFEE_ID, R.string.milchkaffee_name, R.string.milchkaffee_des, R.drawable.milkcoffeeimage, Constants.milchkaffee_price);
                    mealDatabase.daoAccess().insertMealIntoDB(milchkaffee);
                    Meal cappuccino = createNewMeal(Constants.CAPPUCCINO_ID, R.string.cappu_name, R.string.cappu_des, R.drawable.cappuccinoimage, Constants.cappu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(cappuccino);
                    Meal espresso = createNewMeal(Constants.ESPRESSO_ID, R.string.espresso_name, R.string.espresso_des,  R.drawable.espressoimage, Constants.espresso_price);
                    mealDatabase.daoAccess().insertMealIntoDB(espresso);
                    //Muesli
                    Meal schokomu = createNewMeal(Constants.SCHOKOMUESLI, R.string.schokomu_name, R.string.schokomu_des,R.drawable.chocolatemuesliimage, Constants.schokomu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(schokomu);
                    Meal fruchtmu = createNewMeal(Constants.FRUCHTMUESLI, R.string.fruchtmu_name, R.string.fruchtmu_des, R.drawable.fruitmuesliimage, Constants.fruchtmu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(fruchtmu);
                    Meal nussmu = createNewMeal(Constants.NUSSMUESLI, R.string.nussmu_name, R.string.nussmu_des, R.drawable.nutmuesliimage, Constants.nussmu_price);
                    mealDatabase.daoAccess().insertMealIntoDB(nussmu);
                    //Bagels
                    Meal sweetbagel = createNewMeal(Constants.SWEETBAGEL, R.string.sweetbagel_name, R.string.sweetbagel_des, R.drawable.sweetbagelimage, Constants.sweetbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(sweetbagel);
                    Meal freshbagel = createNewMeal(Constants.FRESHBAGEL, R.string.freshbagel_name, R.string.freshbagel_des, R.drawable.freshbagelimage, Constants.freshbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(freshbagel);
                    Meal defaultBagel = createNewMeal(Constants.DEFAULTBAGEL, R.string.defaultbagel_name, R.string.defaultbagel_des, R.drawable.defaultbagelimage, Constants.defaultbagel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(defaultBagel);
                    //Suppe
                    Meal tomatensuppe = createNewMeal(Constants.TOMATENSUPPE, R.string.tomatensuppe_name, R.string.tomatensuppe_des, R.drawable.tomatosoupimage,Constants.tomatensuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tomatensuppe);
                    Meal spargelsuppe = createNewMeal(Constants.SPARGELSUPPE, R.string.spargelsuppe_name, R.string.spargelsuppe_des, R.drawable.asparagussoupimage,Constants.spargelsuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(spargelsuppe);
                    Meal festtagssuppe = createNewMeal(Constants.FESTTAGSSUPPE, R.string.festtagssuppe_name, R.string.festtagssuppe_des, R.drawable.festtagssuppeimage, Constants.festtagssuppe_price);
                    mealDatabase.daoAccess().insertMealIntoDB(festtagssuppe);
                    //Pasta
                    Meal carbonara = createNewMeal(Constants.PASTA_CARBONARA, R.string.pasta_carbonara_name, R.string.pasta_carbonara_des, R.drawable.pastacarbonaraimage, Constants.pasta_carbonara_price);
                    mealDatabase.daoAccess().insertMealIntoDB(carbonara);
                    Meal napoli = createNewMeal(Constants.PASTA_NAPOLI, R.string.pasta_napoli_name, R.string.pasta_napoli_des, R.drawable.pastanapoliimage, Constants.pasta_napoli_price);
                    mealDatabase.daoAccess().insertMealIntoDB(napoli);
                    Meal tuna = createNewMeal(Constants.PASTA_TUNA, R.string.pasta_tuna_name, R.string.pasta_tuna_des, R.drawable.pastatunaimage, Constants.pasta_tuna_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tuna);
                    //Sandwiches
                    Meal hamSandwich = createNewMeal(Constants.SANDWICH_HAM, R.string.sandwich_ham_name, R.string.sandwich_ham_des, R.drawable.hamsandwichimage, Constants.sandwich_ham_price);
                    mealDatabase.daoAccess().insertMealIntoDB(hamSandwich);
                    Meal tomatoSandwich = createNewMeal(Constants.SANDWICH_TOMATE, R.string.sandwich_tomate_name, R.string.sandwich_tomate_des, R.drawable.tomatosandwichimage, Constants.sandwich_tomate_price);
                    mealDatabase.daoAccess().insertMealIntoDB(tomatoSandwich);
                    Meal chickenSandwich = createNewMeal(Constants.SANDWICH_CHICKEN, R.string.sandwich_chicken_name, R.string.sandwich_chicken_des, R.drawable.chickensandwichimage, Constants.sandwich_chicken_price);
                    mealDatabase.daoAccess().insertMealIntoDB(chickenSandwich);
                    //Pizza
                    Meal pizzaHawaii = createNewMeal(Constants.PIZZA_HAWAII, R.string.pizza_hawaii_name, R.string.pizza_hawaii_des, R.drawable.pizzahawaiiimage, Constants.pizza_hawaii_price);
                    mealDatabase.daoAccess().insertMealIntoDB(pizzaHawaii);
                    Meal pizzaMargaritha =createNewMeal(Constants.PIZZA_MARGARITHA, R.string.pizza_margaritha_name, R.string.pizza_margaritha_des, R.drawable.pizzamargarithaimage, Constants.pizza_margaritha_price);
                    mealDatabase.daoAccess().insertMealIntoDB(pizzaMargaritha);
                    Meal pizzaSpezial = createNewMeal(Constants.PIZZA_SPEZIAL, R.string.pizza_spezial_name, R.string.pizza_spezial_des, R.drawable.pizzaspezialimage, Constants.pizza_spezial_price);
                    mealDatabase.daoAccess().insertMealIntoDB(pizzaSpezial);
                    //Meat
                    Meal meatBurger = createNewMeal(Constants.MEAT_BURGER, R.string.meat_burger_name, R.string.meat_burger_des, R.drawable.burgermeatimage, Constants.meat_burger_price);
                    mealDatabase.daoAccess().insertMealIntoDB(meatBurger);
                    Meal meatSteak = createNewMeal(Constants.MEAT_STEAK, R.string.meat_steak_name, R.string.meat_steak_des, R.drawable.steakimage, Constants.meat_steak_price);
                    mealDatabase.daoAccess().insertMealIntoDB(meatSteak);
                    Meal meatSchnitzel = createNewMeal(Constants.MEAT_SCHNITZEL, R.string.meat_schnitzel_name, R.string.meat_schnitzel_des, R.drawable.schnitzelimage, Constants.meat_schnitzel_price);
                    mealDatabase.daoAccess().insertMealIntoDB(meatSchnitzel);
                    //Fish
                    Meal fishChips = createNewMeal(Constants.FISH_CHIPS, R.string.fish_chips_name, R.string.fish_chips_des, R.drawable.fishandchipsimage, Constants.fish_chips_price);
                    mealDatabase.daoAccess().insertMealIntoDB(fishChips);
                    Meal fishLachs = createNewMeal(Constants.FISH_LACHS, R.string.fish_lachs_name, R.string.fish_lachs_des, R.drawable.salmonsteakimage, Constants.fish_lachs_price);
                    mealDatabase.daoAccess().insertMealIntoDB(fishLachs);
                    Meal fishKalamari = createNewMeal(Constants.FISH_KALAMARI, R.string.fish_kalamari_name, R.string.fish_kalamari_des, R.drawable.calamariimage, Constants.fish_kalamari_price);
                    mealDatabase.daoAccess().insertMealIntoDB(fishKalamari);


                    Meal offerMealOne = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_ONE);
                    Meal offerMealTwo = mealDatabase.daoAccess().fetchOneMealbyMealID(Constants.OFFER_TWO);
                    double offerPriceOne = offerMealOne.getPrice() * Constants.DISCOUNT;
                    double offerPriceTwo = offerMealTwo.getPrice() * Constants.DISCOUNT;
                    mealDatabase.daoAccess().updateMealPrice(offerPriceOne, Constants.OFFER_ONE);
                    mealDatabase.daoAccess().updateMealPrice(offerPriceTwo, Constants.OFFER_TWO);

                }}
        }).start();
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

    private void startOfferIntent(int offerID){
        Intent intent = new Intent(MainJames.this, ItemClass.class);
        intent.putExtra("finalFoodID", offerID);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        // In this switch case we either hide or show the fragment, depending on the current state.
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
