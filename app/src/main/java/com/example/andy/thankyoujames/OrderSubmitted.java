package com.example.andy.thankyoujames;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderSubmitted extends AppCompatActivity {

    private TextView hourText,minuteText, totalSum;
    private ImageButton googleMapsImgBtn;

    private ListView itemList;
    private ArrayAdapter<String> adapter;

    private ArrayList<Integer> shoppedItemAppearances ;
    private ArrayList<Integer> soloShoppedItems ;
    private ArrayList <String> nameAndFrequencyItem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submitted);
        initView();
        initListView();

    }

    private void setupContentForListView() {

    shoppedItemAppearances = Cart.numberOfAppearences;
    soloShoppedItems = Cart.soloAppearanceItem;


    new Thread(new Runnable() {
        @Override
        public void run() {

            for (int i = 0; i < soloShoppedItems.size(); i++){
                Meal resultMeal = MainJames.mealDatabase.daoAccess().fetchOneMealbyMealID(soloShoppedItems.get(i));
                String newName  = resultMeal.getMealName();
                nameAndFrequencyItem.add( newName + " " + shoppedItemAppearances.get(i) + "x");
            }

        }
    }).start();
    }

    private void initListView(){
        setupContentForListView();

        itemList = findViewById(R.id.listViewSummary);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nameAndFrequencyItem);
        itemList.setAdapter(adapter);

    }
    private void initView() {
        //Textview
        hourText = findViewById(R.id.hourText);
        minuteText = findViewById(R.id.minuteText);
        totalSum=findViewById(R.id.textSumPrice);

        //ImageButton
        googleMapsImgBtn = findViewById(R.id.googleMapsImgBtn);



        hourText.setText(getIntent().getExtras().getString("Hour"));
        minuteText.setText(getIntent().getExtras().getString("Minute"));
        //total price to be paid
        //totalSum.setText(getIntent().getExtras().getString("totalSum"));



        //GoogleMaps-Icon
        googleMapsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Quelle: https://developers.google.com/maps/documentation/urls/android-intents
                Uri gmmIntentUri = Uri.parse("geo:48.998589,12.094472?q="+ Uri.encode("Universität Regensburg"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }

            }
        });
    }

    @Override
    public void onBackPressed(){

    }


}
