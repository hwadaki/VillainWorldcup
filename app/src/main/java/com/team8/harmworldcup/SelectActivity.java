package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectActivity extends AppCompatActivity {

    TextView text1, text2;
    Button choiceBtn1, choiceBtn2;

    String[] villains_name = {"again","black","gun","danso","dance","chair"};
    String[] youtube_url = {"a","b","c","d","e","f"};
    String[] choiceList = {};
    Integer gang = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        choiceBtn1 = findViewById(R.id.choiceBtn1);
        choiceBtn2 = findViewById(R.id.choiceBtn2);
        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);

        Intent intent = getIntent();

        Integer maxNum = intent.getIntExtra("num",0);


        text1.setText(villains_name[gang]);
        text2.setText(villains_name[gang+1]);

        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gang += 2;
                if(gang<maxNum){
                    text1.setText(villains_name[gang]);
                    text2.setText(villains_name[gang+1]);
                }
                else{
                    Intent endintent = new Intent(getApplicationContext(), EndActivity.class);
                    //결과값 넘겨주는 거 추가해야
                    //그리고 사실 얘는 지금 한번만 토너먼트 거친 채 넘어가는 거라서, 원래는 결승전까지 다 끝난뒤에 넘어가야함 따라서 임시용

                    startActivity(endintent);
                }
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gang += 2;
                if(gang<maxNum){
                    text1.setText(villains_name[gang]);
                    text2.setText(villains_name[gang+1]);
                } else {
                    Intent endintent = new Intent(getApplicationContext(), EndActivity.class);
                    //결과값 넘겨주는 거 추가해야
                    //그리고 사실 얘는 지금 한번만 토너먼트 거친 채 넘어가는 거라서, 원래는 결승전까지 다 끝난뒤에 넘어가야함 따라서 임시용

                    startActivity(endintent);
                }
            }
        });
    }
}