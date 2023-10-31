package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button StartBtn;
    Spinner spinnerGang;

    int gang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartBtn = findViewById(R.id.startBtn);
        StartBtn.setBackgroundColor(Color.RED);
        spinnerGang = findViewById(R.id.selectGang);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gang,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGang.setAdapter(adapter);

        spinnerGang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    gang = 8;
                } else if(i==1){
                    gang = 4;
                }
                System.out.println(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gang = 8;
                System.out.println("baamm");
            }

        });

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.putExtra("num",gang);

                startActivity(intent);
            }
        });
    }
}