package com.team8.harmworldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SelectActivity extends AppCompatActivity {
    TextView text1, text2;
    Button choiceBtn1, choiceBtn2;

    String[] villains_name = {"test1","test2","test3","test4","test5","test6","test7","test8","test9","test10"};
    String[] youtube_url = {"a","b","c","d","e","f","g","h","j","k"};
    static Queue<Integer> choiceQueue = new LinkedList<>();
    Integer challenge = 0;

    int[] array_start, array_4gang, array_34; //시작, 4강, 결승 배열 기록
    int[] array_2gang = new int[2]; //34위전

    int num1,num2;


    public static int[] randomChoice(int n){
        int[] originalArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] selectedVillains = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            // 랜덤 인덱스 생성
            int randomIndex = rand.nextInt(originalArray.length);

            // 중복을 피하기 위해 이미 선택된 요소가 아니라면 선택
            if (originalArray[randomIndex] != -1) {
                //selectedVillains[i] = originalArray[randomIndex];
                choiceQueue.offer(randomIndex);

                // 이미 선택된 요소는 -1로 표시하여 중복을 방지
                originalArray[randomIndex] = -1;
            } else {
                // 이미 선택된 요소외에 다시 선택하기 위해 반복
                i--;
            }
            System.out.println(randomIndex);
        }
        return selectedVillains;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_select);

        choiceBtn1 = findViewById(R.id.choiceBtn1);
        choiceBtn2 = findViewById(R.id.choiceBtn2);
        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);

        Intent intent = getIntent();

        Integer maxNum = intent.getIntExtra("num",0);

        array_start = randomChoice(maxNum); //시작 array 저장
        int size = choiceQueue.size();//임시
        for(int j=0; j<size; j++){
            int q = choiceQueue.poll();
            System.out.print(q+1);
            System.out.print("/");
            choiceQueue.offer(q);
        };
        System.out.println();

        num1 = choiceQueue.poll();
        num2 = choiceQueue.poll();

        text1.setText(villains_name[num1]);
        text2.setText(villains_name[num2]);

        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge += 1;
                if(challenge<maxNum){
                    System.out.println(maxNum-challenge);
                    if (maxNum-challenge==1) { //결승전
                        System.out.println("이제 결승전입니다.");
                        Toast.makeText(getApplicationContext(), "이제 결승전입니다.", Toast.LENGTH_SHORT).show();
                    } else if(maxNum-challenge==2){
                        System.out.println("이제 3,4위전입니다.");
                        Toast.makeText(getApplicationContext(), "이제 3,4위전입니다.", Toast.LENGTH_SHORT).show();

                    }
                    else if(maxNum-challenge==4){
                        System.out.println("이제 준결승전입니다.");
                    }

                    if(maxNum-challenge==1){ //3,4위전
                        choiceQueue.offer(array_2gang[0]);
                        choiceQueue.offer(array_2gang[1]);

                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();

                    } else if(maxNum-challenge==3 || maxNum-challenge==2){ //준결승전
                        array_2gang[(maxNum-challenge-1)%2] = num1;
                        choiceQueue.offer(num2); //34위전꺼 먼저 추가

                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();

                    } else {
                        choiceQueue.offer(num1); //1번 추가
                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();
                    }

                    text1.setText(villains_name[num1]);
                    text2.setText(villains_name[num2]);
                }
                else{
                    Intent endintent = new Intent(getApplicationContext(), EndActivity.class);
                    endintent.putExtra("winner",num1);

                    startActivity(endintent);
                }
                int size = choiceQueue.size();//임시
                for(int j=0; j<size; j++){
                    int q = choiceQueue.poll();
                    System.out.print(q+1);
                    System.out.print("/");
                    choiceQueue.offer(q);
                };
                System.out.println();
            }
        });
        choiceBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge += 1;
                if(challenge<maxNum){
                    System.out.println(maxNum-challenge);
                    if (maxNum-challenge==1) { //결승전
                        System.out.println("이제 결승전입니다.");
                        Toast.makeText(getApplicationContext(), "이제 결승전입니다.", Toast.LENGTH_SHORT).show();
                    } else if(maxNum-challenge==2){
                        System.out.println("이제 3,4위전입니다.");
                        Toast.makeText(getApplicationContext(), "이제 3,4위전입니다.", Toast.LENGTH_SHORT).show();

                    }
                    else if(maxNum-challenge==4){
                        System.out.println("이제 준결승전입니다.");
                    }

                    if(maxNum-challenge==1){ //3,4위전
                        choiceQueue.offer(array_2gang[0]);
                        choiceQueue.offer(array_2gang[1]);

                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();

                    } else if(maxNum-challenge==3 || maxNum-challenge==2){ //준결승전
                        array_2gang[(maxNum-challenge-1)%2] = num2;
                        choiceQueue.offer(num1); //34위전꺼 먼저 추가

                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();

                    } else {
                        choiceQueue.offer(num2); //1번 추가
                        num1 = choiceQueue.poll();
                        num2 = choiceQueue.poll();
                    }

                    text1.setText(villains_name[num1]);
                    text2.setText(villains_name[num2]);
                }
                else{
                    Intent endintent = new Intent(getApplicationContext(), EndActivity.class);
                    endintent.putExtra("winner",num2);

                    startActivity(endintent);
                }
                int size = choiceQueue.size();//임시
                for(int j=0; j<size; j++){
                    int q = choiceQueue.poll();
                    System.out.print(q+1);
                    System.out.print("/");
                    choiceQueue.offer(q);
                };
                System.out.println();
            }
        });
    }
}