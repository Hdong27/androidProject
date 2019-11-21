package com.example.administrator.final_project;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-05-27.
 */
public class sos_messageActivity extends Activity {
Button Btn1, Btn2, Btn3, Btn4, Btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sos_message);
        setTitle("긴급문자신고");

        Btn1 = (Button) findViewById(R.id.mBtn1);
        Btn2 = (Button) findViewById(R.id.mBtn2);
        Btn3 = (Button) findViewById(R.id.mBtn3);
        Btn4 = (Button) findViewById(R.id.mBtn4);
        Btn5 = (Button) findViewById(R.id.mBtn5);

        Btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS("01077208498", "살려주세요.");

            }  //아래 코딩된 함수를 이용해 발신

        });

        Btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS("01077208498", "누군가에게 쫓기고 있어요.");

            }

        });

        Btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS("01077208498", "집에 누군가 들어오려고 해요.");

            }

        });

        Btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS("01077208498", "위급한 상황입니다.");

            }

        });

        Btn5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendSMS("01077208498", "폭행당하고 있어요.");

            }

        });
    }

    private void sendSMS(String phoneNumber, String message) {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        registerReceiver(new BroadcastReceiver(){
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber,null,message,sentPI, deliveredPI);

        //메시지 발신 관련 함수를 지정
    }

}
