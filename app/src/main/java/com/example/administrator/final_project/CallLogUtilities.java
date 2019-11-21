package com.example.administrator.final_project;

/**
 * Created by Administrator on 2017-06-04.
 */
import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.CallLog.Calls;

public class CallLogUtilities {

    public static void addCallToLog(ContentResolver contentResolver, String number, long duration, int type, long time) {

        ContentValues values = new ContentValues();

        values.put(Calls.NUMBER, number);

        values.put(Calls.DATE, time);

        values.put(Calls.DURATION, duration);

        values.put(Calls.TYPE, type);

        values.put(Calls.NEW, 1);

        values.put(Calls.CACHED_NAME, "");

        values.put(Calls.CACHED_NUMBER_TYPE, 0);

        values.put(Calls.CACHED_NUMBER_LABEL, "");

        contentResolver.insert(Calls.CONTENT_URI, values);

    } //페이크콜 예약을 위한 변수 지정 및 삽입

}
