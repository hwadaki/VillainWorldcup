package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    Button restartBtn,bracketBtn;
    TextView endText;

    ImageView endImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_end);

        restartBtn = findViewById(R.id.restartBtn);
        bracketBtn = findViewById(R.id.bracketBtn);
        endText = findViewById(R.id.endText);
        endImage = findViewById(R.id.endImage);

        String[] villains_name = getResources().getStringArray(R.array.villain);

        Intent intent = getIntent();

        Integer winner = intent.getIntExtra("winner",-1);

        endText.setText(villains_name[winner]+" 우승");

        int img = getResources().getIdentifier("@drawable/villain_img_" + winner,"drawable", getPackageName());
        endImage.setImageResource(img);


        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent endintent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(endintent);
            }
        });

        bracketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bracketintent = new Intent(getApplicationContext(), BracketActivity.class);

                startActivity(bracketintent);
            }
        });
    }
}