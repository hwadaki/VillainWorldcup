package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class BracketActivity extends AppCompatActivity {

    TextView eightT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_bracket);

        int[] EightGang = {R.id.Eightgang1,R.id.Eightgang2,R.id.Eightgang3,R.id.Eightgang4,R.id.Eightgang5,R.id.Eightgang6,R.id.Eightgang7,R.id.Eightgang8};
        int[] EightText = {R.id.eight1,R.id.eight2,R.id.eight3,R.id.eight4};
        int[] FourGang = {R.id.Fourgang1,R.id.Fourgang2,R.id.Fourgang3,R.id.Fourgang4};
        int[] ThreeGang = {R.id.Threegang1,R.id.Threegang2};
        int[] FinalGang = {R.id.final1, R.id.final2};

        eightT = findViewById(R.id.eightT);

        Intent intent = getIntent();
        Integer winner = intent.getIntExtra("winner",-1);
        Integer ThreeResult = intent.getIntExtra("ThreeResult",-1);
        int[] history = intent.getIntArrayExtra("history");

        String[] villains_name = getResources().getStringArray(R.array.villain);

        if(history.length==16){ //8강 시작
            for(int n=1; n<4 ; n++){
                TextView eighttext = findViewById(EightText[n]);
                eighttext.setVisibility(View.VISIBLE);
            }
            eightT.setText("VS");
            for(int n=0; n<8 ; n++){
                Button EightgangButton = findViewById(EightGang[n]);
                EightgangButton.setText(villains_name[history[n]]);
                EightgangButton.setVisibility(View.VISIBLE);

                if(history[(n/2)+8]==history[n]){
                    EightgangButton.setBackgroundColor(Color.BLUE);
                } else {
                    EightgangButton.setBackgroundColor(Color.RED);
                }
            }
            for(int n=0; n<4 ; n++){
                Button FourgangButton = findViewById(FourGang[n]);
                FourgangButton.setText(villains_name[history[8+n]]);

                if(!(history[(n/2)+12]==history[n+8])){ //3,4위전에 없어야함
                    FourgangButton.setBackgroundColor(Color.BLUE);
                } else {
                    FourgangButton.setBackgroundColor(Color.RED);
                }
            }
            for(int n=0; n<2 ; n++){
                Button ThreegangButton = findViewById(ThreeGang[n]);
                ThreegangButton.setText(villains_name[history[12+n]]);

                System.out.println(ThreeResult);

                if(ThreeResult==history[n+12]){
                    ThreegangButton.setBackgroundColor(Color.BLUE);
                } else {
                    ThreegangButton.setBackgroundColor(Color.RED);
                }
            }
            for(int n=0; n<2 ; n++){
                Button FinalgangButton = findViewById(FinalGang[n]);
                FinalgangButton.setText(villains_name[history[14+n]]);

                if(history[14+n]==winner){
                    FinalgangButton.setBackgroundColor(Color.BLUE);
                } else {
                    FinalgangButton.setBackgroundColor(Color.RED);
                }
            }
        } else{
            for(int n=1; n<4 ; n++){
                TextView eighttext = findViewById(EightText[n]);
                eighttext.setVisibility(View.INVISIBLE);
            }
            eightT.setText("미진행");
            for(int n=0; n<8 ; n++){
                Button EightgangButton = findViewById(EightGang[n]);
                EightgangButton.setVisibility(View.INVISIBLE);
            }
            for(int n=0; n<4 ; n++){
                Button FourgangButton = findViewById(FourGang[n]);
                FourgangButton.setText(villains_name[history[n]]);

                if(!(history[(n/2)+4]==history[n])){ //3,4위전에 없어야함
                    FourgangButton.setBackgroundColor(Color.BLUE);
                } else {
                    FourgangButton.setBackgroundColor(Color.RED);
                }
            }
            for(int n=0; n<2 ; n++){
                Button ThreegangButton = findViewById(ThreeGang[n]);
                ThreegangButton.setText(villains_name[history[4+n]]);

                System.out.println(ThreeResult);

                if(ThreeResult==history[n+4]){
                    ThreegangButton.setBackgroundColor(Color.BLUE);
                } else {
                    ThreegangButton.setBackgroundColor(Color.RED);
                }
            }
            for(int n=0; n<2 ; n++){
                Button FinalgangButton = findViewById(FinalGang[n]);
                FinalgangButton.setText(villains_name[history[6+n]]);

                if(history[6+n]==winner){
                    FinalgangButton.setBackgroundColor(Color.BLUE);
                } else {
                    FinalgangButton.setBackgroundColor(Color.RED);
                }
            }
        }
    }
}