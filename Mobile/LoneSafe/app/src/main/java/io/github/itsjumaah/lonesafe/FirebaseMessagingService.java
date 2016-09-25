package io.github.itsjumaah.lonesafe;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/*
 * Created by ashneelkumar on 5/08/16.
 */
public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        showNotification(remoteMessage.getData().get("message"));
    }


    private void showNotification(String message) {

        int requestID = (int) System.currentTimeMillis();
        int notifyID = 1;

     //   NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent notifyIntent = new Intent(this,CheckinNotification.class);
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //THIS MIGHT BE THE PROBLEM <--------------
        notifyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
       // notifyIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//single top to avoid...

       // notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
      //  TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
      //  stackBuilder.addParentStack(Launcher.class);
      //  stackBuilder.addNextIntent(notifyIntent);

        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(this, requestID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT |
                        PendingIntent.FLAG_ONE_SHOT);



        //Take Notification Sound
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       // Vibrator vibrator = (Vibrator) this .getSystemService(Context.VIBRATOR_SERVICE);
       // vibrator.vibrate(2000);


        //Generate the Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setPriority(2)
                .setLights(Color.CYAN,100,3000)
               // .setLights(0xFF0000FF,100,3000)
                .setVibrate(new long[] { 1000, 1000, 1000 }) //{ delay, vibrate, sleep, vibrate, sleep } pattern
                .setContentTitle("Check In")
                .setContentText(message)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setSound(defaultSoundUri);

        builder.setContentIntent(notifyPendingIntent);

        // .setAutoCancel(true)

                //.setColor() //colors in logo area when small icon used ?
                // .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)) //? see if it works

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(notifyID,builder.build());


    }
}

