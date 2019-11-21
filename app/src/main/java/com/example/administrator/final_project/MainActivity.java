package com.example.administrator.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btn1, btn2, btn4, btn6, btn7, btn5;
    Button newsbtn, select;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("범죄예방 앱");

        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn6 = (ImageButton) findViewById(R.id.btn6);
        btn2 = (ImageButton) findViewById(R.id.btn2);
        final ImageButton btn3 = (ImageButton) findViewById(R.id.btn3);
        btn4 = (ImageButton) findViewById(R.id.btn4);
        btn7 = (ImageButton) findViewById(R.id.btn7);
        btn5 = (ImageButton) findViewById(R.id.btn5);
        newsbtn = (Button) findViewById(R.id.newsBtn);
        select = (Button) findViewById(R.id.select);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn1:
                        startActivity(new Intent("android.intent.action.CALL",
                                Uri.parse("tel:010-7720-8498")));
                        break;
                }

            }
        });  //바로 전화가 가는 액션

        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("112에 연결하겠습니다.");
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent("android.intent.action.CALL",
                                        Uri.parse("tel:010-7720-8498")));

                            }
                        });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        }); //전화를 거는 액션, 토스트 메시지를 이용 한 번 더 확인

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, messageActivity.class);
                startActivity(intent);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScheduleCallActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, sos_messageActivity.class);
                startActivity(intent);

            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });


        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GpsActivity.class);
                startActivity(intent);
            }
        }); //해당 자바 클래스로 이동시킴
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.mogef_android1.app");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });//선택시 지정된 uri를 불러옴
        newsbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://m.news.naver.com/main.nhn?mode=LSD&sid1=102");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });  //선택시 지정된 uri를 불러옴
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 1, 0, "개인정보");
        menu.add(0, 2, 0, "초기화면");

        return true;
    }
 //메뉴를 만듬
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                Intent intent = new Intent(this, infoActivity.class);
                startActivity(intent);
                return true;
            }
            case 2: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
}
 //메뉴에 액션을 추가
}
