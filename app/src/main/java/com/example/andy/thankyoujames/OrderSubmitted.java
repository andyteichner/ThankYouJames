package com.example.andy.thankyoujames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class OrderSubmitted extends AppCompatActivity {

    TextView hourText,minuteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_submitted);
        initView();
    }

    private void initView(){
        hourText = findViewById(R.id.hourText);
        minuteText = findViewById(R.id.minuteText);

        hourText.setText(getIntent().getExtras().getString("Hour"));
        minuteText.setText(getIntent().getExtras().getString("Minute"));
        Toast.makeText(this,"Alarm für Bestellung ist gestellt", Toast.LENGTH_LONG).show();
    }
}
