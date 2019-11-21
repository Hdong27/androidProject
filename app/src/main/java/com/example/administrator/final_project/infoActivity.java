package com.example.administrator.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Lenovo on 2017-06-22.
 */
public class infoActivity extends AppCompatActivity{
    myDBHelper myHelper;
    EditText infoName, infoSex, infoAge, infoNumber, infoAddress;
    Button OKBtn;
    SQLiteDatabase sqlDB;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.people_info);
        setTitle("개인정보");

        infoName = (EditText) findViewById(R.id.infoName);
        infoSex = (EditText) findViewById(R.id.infoSex);
        infoAge = (EditText) findViewById(R.id.infoAge);
        infoNumber = (EditText) findViewById(R.id.infoNumber);
        infoAddress = (EditText) findViewById(R.id.infoAddress);
        OKBtn = (Button) findViewById(R.id.OK_Btn);

        //데이터 저장을 위한 변수 불러오기

        myHelper = new myDBHelper(this);

        //DB연결
        OKBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES('"+infoName.getText().toString()+"','"+infoSex.getText().toString()+"','"+infoAge.getText().toString()+"','"+infoNumber.getText().toString()+"','"+infoAddress.getText().toString()+"'");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "저장되었습니다", Toast.LENGTH_SHORT).show();
                OKBtn.callOnClick();
                //텍스트에 입력된 값들을 저장
                sqlDB.close();

                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);
                //입력된 DB를 불러오기
                String strName = null, strSex=null, strAge=null, strNumber=null, strAddress=null;

                while(cursor.moveToNext()){
                    strName += cursor.getString(0);
                    strSex += cursor.getString(1);
                    strAge += cursor.getString(2);
                    strNumber += cursor.getString(3);
                    strAddress += cursor.getString(4);
                } //불러온 DB를 저장

                infoName.setText(strName);
                infoSex.setText(strSex);
                infoAge.setText(strAge);
                infoNumber.setText(strNumber);
                infoAddress.setText(strAddress);
                //저장된 데이터들을 삽입
                cursor.close();
            }
        });

    }


    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  groupTBL ( infoName CHAR(20) PRIMARY KEY, infoSex CHAR(5),infoAge INTEGER, infoNumber INTEGER, infoAddress CHAR(20));");
        }

        @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS groupTBL");
                onCreate(db);
        }  //데이터 저장 및 불러오기 관련 소스
    }
}
