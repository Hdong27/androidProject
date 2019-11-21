package com.example.administrator.final_project;

/**
 * Created by Administrator on 2017-06-04.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class LaunchAppViaDialReceiver extends BroadcastReceiver {

    SharedPreferences appSettings;

    @Override
    public void onReceive(Context context, Intent intent) {

        appSettings = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);

        String numberToDial = appSettings.getString("numberToDial", "111");

        String phoneNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);

        if (phoneNumber.equals(numberToDial)) {

            setResultData(null);

            Intent appIntent = new Intent(context, ScheduleCallActivity.class);

            appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(appIntent);

        }  //지정해 놓은 fakecall의 실행을 위한 구문

    }

}