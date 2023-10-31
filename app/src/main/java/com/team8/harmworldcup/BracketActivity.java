package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class BracketActivity extends AppCompatActivity {

    Button Eightgang1, Eightgang2,Eightgang3,Eightgang4,Eightgang5,Eightgang6,Eightgang7,Eightgang8;
    Button Fourgang1, Fourgang2, Fourgang3, Fourgang4;
    Button Threegang1, Threegang2;
    Button final1, final2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket);

        Eightgang1 = findViewById(R.id.Eightgang1);
        Eightgang2 = findViewById(R.id.Eightgang2);
        Eightgang3 = findViewById(R.id.Eightgang3);
        Eightgang4 = findViewById(R.id.Eightgang4);
        Eightgang5 = findViewById(R.id.Eightgang5);
        Eightgang6 = findViewById(R.id.Eightgang6);
        Eightgang7 = findViewById(R.id.Eightgang7);
        Eightgang8 = findViewById(R.id.Eightgang8);

        Fourgang1 = findViewById(R.id.Fourgang1);
        Fourgang2 = findViewById(R.id.Fourgang2);
        Fourgang3 = findViewById(R.id.Fourgang3);
        Fourgang4 = findViewById(R.id.Fourgang4);

        Threegang1 = findViewById(R.id.Threegang1);
        Threegang2 = findViewById(R.id.Threegang2);

        final1 = findViewById(R.id.final1);
        final2 = findViewById(R.id.final2);


    }
}