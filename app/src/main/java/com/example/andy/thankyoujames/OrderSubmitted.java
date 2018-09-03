package com.example.andy.thankyoujames;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSubmitted extends AppCompatActivity {

    TextView hourText,minuteText, totalSum;
    ImageButton googleMapsImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submitted);
        initView();
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
