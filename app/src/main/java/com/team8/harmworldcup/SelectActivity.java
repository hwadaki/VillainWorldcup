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
import java.util.Stack;

public class SelectActivity extends AppCompatActivity {
    TextView choiceText1, choiceText2;
    Button choiceBtn1, choiceBtn2, backButton;

    Stack<Integer> history_stack = new Stack<>();
    static Queue<Integer> choiceQueue = new LinkedList<>();
    Integer challenge = 0; //진행한 총 대결 수

    int[] array_start = new int[8];
    int[] array_4gang, array_34; //시작, 4강, 결승 배열 기록
    int[] array_2gang = new int[2]; //34위전

    int num1,num2; //현재 대결하는 대상


    public int[] randomChoice(int n){
        int[] originalArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[] selectedVillains = new int[n];
        Random rand = new Random();
        choiceQueue.clear();

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
        backButton = findViewById(R.id.backButton);
        choiceText1 = findViewById(R.id.choiceText1);
        choiceText2 = findViewById(R.id.choiceText2);

        String[] villains_name = getResources().getStringArray(R.array.villain); //{"test1","test2","test3","test4","test5","test6","test7","test8","test9","test10"};
        String[] youtube_id = getResources().getStringArray(R.array.youtube_id); //사진의 경우 "None" 문자열

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
        history_stack.clear();

        num1 = choiceQueue.poll();
        num2 = choiceQueue.poll();

        choiceText1.setText(villains_name[num1]);
        choiceText2.setText(villains_name[num2]);

        choiceBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                challenge += 1;
                if(challenge<maxNum){
                    System.out.println(maxNum-challenge);
                    if (maxNum-challenge==1) { //안내 문구용 조건문
                        Toast.makeText(getApplicationContext(), "이제 결승전입니다.", Toast.LENGTH_SHORT).show();
                    } else if(maxNum-challenge==2){
                        Toast.makeText(getApplicationContext(), "이제 3,4위전입니다.", Toast.LENGTH_SHORT).show();
                    } else if(maxNum-challenge==4){
                        Toast.makeText(getApplicationContext(), "이제 준결승전입니다.", Toast.LENGTH_SHORT).show();
                    }

                    if(maxNum-challenge==1){ //3,4위전
                        choiceQueue.offer(array_2gang[0]); //다음 결승전 위해서 대결자들 추가
                        choiceQueue.offer(array_2gang[1]);

                    } else if(maxNum-challenge==3 || maxNum-challenge==2){ //준결승전
                        array_2gang[(maxNum-challenge-1)%2] = num1; //결승에 갈 것 따로 임시저장
                        choiceQueue.offer(num2); //34위전꺼 먼저 추가

                    } else {
                        choiceQueue.offer(num1); //1번 추가
                    }

                    history_stack.push(num1); //이전 대결 상대 스택에 저장
                    history_stack.push(num2);

                    num1 = choiceQueue.poll();
                    num2 = choiceQueue.poll();

                    choiceText1.setText(villains_name[num1]);
                    choiceText2.setText(villains_name[num2]);

                } else{
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
                    if (maxNum-challenge==1) { //안내 문구용 조건문
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

                    } else if(maxNum-challenge==3 || maxNum-challenge==2){ //준결승전
                        array_2gang[(maxNum-challenge-1)%2] = num2;
                        choiceQueue.offer(num1); //34위전꺼 먼저 추가

                    } else {
                        choiceQueue.offer(num2); //2번 추가
                    }

                    history_stack.push(num1);
                    history_stack.push(num2);

                    num1 = choiceQueue.poll();
                    num2 = choiceQueue.poll();

                    choiceText1.setText(villains_name[num1]);
                    choiceText2.setText(villains_name[num2]);
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int s = choiceQueue.size();//임시
                for(int j=0; j<s; j++){
                    int q = choiceQueue.poll();
                    System.out.print(q+1);
                    System.out.print("/");
                    choiceQueue.offer(q);
                };
                System.out.println();

                if (history_stack.size()>=2 && challenge>0){ //스택 비어있지 않은 경우만(2개 이상)
                    int size = choiceQueue.size();
                    for(int j=0; j<size-1; j++){
                        int q = choiceQueue.poll();
                        choiceQueue.offer(q);
                    };
                    choiceQueue.poll(); //맨 끝에꺼 제거

                    choiceQueue.offer(num1);
                    choiceQueue.offer(num2);
                    size = choiceQueue.size();//맨 끝에꺼 제거
                    for(int j=0; j<size-2; j++){
                        int q = choiceQueue.poll();
                        choiceQueue.offer(q);
                    };

                    num2 = history_stack.pop();
                    num1 = history_stack.pop();

                    challenge -= 1;

                    choiceText1.setText(villains_name[num1]);
                    choiceText2.setText(villains_name[num2]);

                } else{
                    Toast.makeText(getApplicationContext(), "더 이상 뒤로갈 수 없습니다(처음)", Toast.LENGTH_SHORT).show();
                };

                s = choiceQueue.size();//임시
                for(int j=0; j<s; j++){
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