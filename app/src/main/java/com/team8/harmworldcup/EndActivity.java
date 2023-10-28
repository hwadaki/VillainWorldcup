package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    Button restartBtn;
    TextView endText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        restartBtn = findViewById(R.id.restartBtn);
        endText = findViewById(R.id.endText);

        Intent intent = getIntent();

        Integer winner = intent.getIntExtra("winner",-1);

        endText.setText((winner+1)+"번 우승");


        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endintent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(endintent);
            }
        });
    }
}