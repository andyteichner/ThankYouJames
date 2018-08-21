package com.example.andy.thankyoujames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSubmitted extends AppCompatActivity {

    TextView hourText,minuteText;
    ImageButton googleMapsImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submitted);
        initView();
    }

    private void initView() {
        hourText = findViewById(R.id.hourText);
        minuteText = findViewById(R.id.minuteText);
        googleMapsImgBtn = findViewById(R.id.googleMapsImgBtn);

        hourText.setText(getIntent().getExtras().getString("Hour"));
        minuteText.setText(getIntent().getExtras().getString("Minute"));

        googleMapsImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSubmitted.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }


}
