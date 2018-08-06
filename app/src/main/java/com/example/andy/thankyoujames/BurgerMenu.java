package com.example.andy.thankyoujames;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BurgerMenu extends Fragment {

    private TextView    jamesText;
    private Button      morningButton, noonBottun, eveningButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_burger_menu, container, false );
        jamesText = v.findViewById(R.id.fragment_text);
        morningButton = v.findViewById(R.id.morning_button);
        morningButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startMenuMorning = new Intent(getActivity(), Menu_1.class);
                startMenuMorning.putExtra("Identifier", 1);
                startActivity(startMenuMorning);
            }
        });
        noonBottun = v.findViewById(R.id.noon_button);
        noonBottun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMenuNoon = new Intent(getActivity(), Menu_1.class);
                startMenuNoon.putExtra("Identifier", 2);
                startActivity(startMenuNoon);
            }
        });
        eveningButton = v.findViewById(R.id.evening_button);
        eveningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startMenuEvening = new Intent(getActivity(), Menu_1.class);
                startMenuEvening.putExtra("Identifier", 3);
                startActivity(startMenuEvening);
            }
        });

        return v;
    }
}
