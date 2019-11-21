package com.example.administrator.final_project;

/**
 * Created by Lenovo on 2017-06-09.
 */
import android.app.ActivityManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Application 설치가 끝나고 최초로 실행될 때 수행되는 코드
 */
public class MyApplication extends Application {

    private BeaconManager beaconManager;

    /**
     Application을 설치할 때 실행됨.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        beaconManager = new BeaconManager(getApplicationContext());

        // Application 설치가 끝나면 Beacon Monitoring Service를 시작한다.
        // Application을 종료하더라도 Service가 계속 실행된다.
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startMonitoring(new Region(
                        "monitored region",

                        //  비콘의 아이디
                        UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"),

                        //  비콘의 Major ID
                        0x1234,

                        // 비콘의 Minor ID
                        0xFA01));

                /**
                 * 비콘에게 아이디, Major, Minor를 보낼 것이다.
                 */
            }
        });

        // Android 단말이 Beacon 의 송신 범위에 들어가거나, 나왔을 때를 체크
        beaconManager.setMonitoringListener(new BeaconManager.MonitoringListener() {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list) {

                // list.get(0).getRssi()은 수신강도
                // 0에 가까울수록 거리가 가까움
                // showNotification은 아래 메소드를 호출
                showNotification("주변에 성범죄자 있음", "안전지역으로 이동하세요." /** +list.get(0).getRssi() */);

                // 이미 앱이 실행중이면 Notification만 주는 기능
                if( !isAlreadyRunActivity() ) {
                    Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("executeType", "beacon");
                    getApplicationContext().startActivity(intent);
                } else {
                    showNotification("주변에 성범죄자 있음", "안전지역으로 이동하세요.");
                }
            }

            @Override
            public void onExitedRegion(Region region) {
                showNotification("주변에 성범죄자 없음", "안전지역 입니다.");
            }
        });
    }


    /**
     * Notification으로 Beacon 의 신호가 연결되거나 끊겼음을 알림.
     *
     * @param title
     * @param message
     */
    public void showNotification(String title, String message) {

        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivities(
                this, 0, new Intent[]{notifyIntent}
                , PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setTicker("현재 알림 기능이 고장났습니다.")  // 쿠폰툴
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .build();

        notification.defaults |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    /**
     *
     * @return
     */
    private boolean isAlreadyRunActivity() {

        ActivityManager activity_manager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> task_info =
                activity_manager.getRunningTasks(9999);

        String activityName = "";
        for( int i = 0; i < task_info.size(); i++ ) {
            activityName = task_info.get(i).topActivity.getPackageName();

            if( activityName.startsWith("com.example.lenovo.mybeacon2") ) {
                return true;
            }
        }
        return false;
    }
}