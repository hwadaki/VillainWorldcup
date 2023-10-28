package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button StartBtn;
    Spinner selectGang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_main);

        StartBtn = findViewById(R.id.startBtn);
        selectGang = findViewById(R.id.selectGang);

        StartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                intent.putExtra("num",8);

                startActivity(intent);
            }
        });
    }
}