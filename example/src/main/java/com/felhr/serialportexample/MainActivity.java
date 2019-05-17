package com.felhr.serialportexample;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.lang.ref.WeakReference;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    /*
     * Notifications from UsbService will be received here.
     */
    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case UsbService.ACTION_USB_PERMISSION_GRANTED: // USB PERMISSION GRANTED
                    Toast.makeText(context, "USB Ready", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_PERMISSION_NOT_GRANTED: // USB PERMISSION NOT GRANTED
                    Toast.makeText(context, "USB Permission not granted", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_NO_USB: // NO USB CONNECTED
                    Toast.makeText(context, "No USB connected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_DISCONNECTED: // USB DISCONNECTED
                    Toast.makeText(context, "USB disconnected", Toast.LENGTH_SHORT).show();
                    break;
                case UsbService.ACTION_USB_NOT_SUPPORTED: // USB NOT SUPPORTED
                    Toast.makeText(context, "USB device not supported", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    public UsbService usbService;
    TextView display;
    public MyHandler mHandler;
    ImageView tombol;
    //Switch swit1,swit2;

    SharedPreferences sharedpref,sharedpref1 ;

    public static final int NOTIFICATION_ID = 1;
   // public static final String SWIT1 ="swit1";
    //public static final String SWIT2 ="swit2";

     boolean switOnOff = true;
     boolean switOnOff1 = true;

    private final ServiceConnection usbConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            usbService = null;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClicktombolListener();
        mHandler = new MyHandler(this);

        //loadData ();

       /* display1 = (TextView) findViewById(R.id.display1);
       // editText = (EditText) findViewById(R.id.editText1);
        Button sendButton = (Button) findViewById(R.id.LogAktivitas);
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!sendButton.getText().toString().equals("")) {
                    String data = "3";//editText.getText().toString();
                    if (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write(data.getBytes());
                    }
                }
            }
        });*/



        display = (TextView) findViewById(R.id.sugi);
        Switch swit1 = (Switch) findViewById(R.id.swit1);
        sharedpref1 = getSharedPreferences ("com.felhr.serialportexample", MODE_PRIVATE);
        swit1.setChecked (sharedpref1.getBoolean ("swito1",false));
        swit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!swit1.getText ( ).toString ( ).equals ("")) {
                    String data = new String ();
                        if (isChecked) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit1.getTextOn ( ).toString ( );
                            }

                            SharedPreferences.Editor editor = sharedpref1.edit ();
                            editor.putBoolean ("swito1" , true);
                            editor.commit ();

                            Toast.makeText (getApplicationContext (),"AKTIF", Toast.LENGTH_LONG).show ();
                            //saveData ();
                        }
                        else {
                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                 data = swit1.getTextOff ( ).toString ( );//editText.getText().toString();
                             }

                            SharedPreferences.Editor editor = getSharedPreferences ("com.felhr.serialportexample", MODE_PRIVATE).edit ();
                            editor.putBoolean ("swito1" , false);
                            editor.commit ();

                            Toast.makeText (getApplicationContext (),"NON-AKTIF", Toast.LENGTH_LONG).show ();
                            //saveData ();
                        }
                    if  (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write (data.getBytes ( ));
                    }
                }

            }
        });


        display = (TextView) findViewById(R.id.sugi);
        Switch swit2 = (Switch) findViewById (R.id.swit2);
        sharedpref = getSharedPreferences ("com.felhr.serialportexample1", MODE_PRIVATE);
        swit2.setChecked (sharedpref.getBoolean ("swito2",false));
        swit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (!swit2.getText ( ).toString ( ).equals ("")) {
                    String data = new String();
                        if (isChecked) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit2.getTextOn ( ).toString ( );
                            }

                            SharedPreferences.Editor editor = sharedpref.edit ();
                            editor.putBoolean ("swito2" , true);
                            editor.commit ();
                            Toast.makeText (getApplicationContext (),"AKTIF", Toast.LENGTH_LONG).show ();
                            //saveData ();
                        }
                        else {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit2.getTextOff ().toString ();//editText.getText().toString();
                            }
                            SharedPreferences.Editor editor = getSharedPreferences ("com.felhr.serialportexample1", MODE_PRIVATE).edit ();
                            editor.putBoolean ("swito2" , false);
                            editor.commit ();
                            Toast.makeText (getApplicationContext (),"NON-AKTIF", Toast.LENGTH_LONG).show ();
                            //saveData ();
                        }
                    if  (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write (data.getBytes ( ));
                    }

                }


            }


        });



    }

    public void swiitt2(View view) {
        display.setText (String.valueOf (switOnOff1));
    }

    public void switt1(View view) {
        display.setText (String.valueOf (switOnOff));
    }

    /*public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences ("savedata", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit ();

        editor.putBoolean (String.valueOf (false), switOnOff);
        editor.putBoolean (String.valueOf (true), switOnOff1);

        editor.apply();
    }*/

    /*public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences ("savedata", MODE_PRIVATE);
        switOnOff = sharedPreferences.getBoolean (SWIT1,false );
        switOnOff1 = sharedPreferences.getBoolean (SWIT2,true );

        //display.setText (String.valueOf (switOnOff));
        //display.setText (String.valueOf (switOnOff1));
    }*/


    public void OnClicktombolListener() {
        tombol = (ImageView) findViewById (R.id.LogAktivity);
        tombol.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Intent satu = new Intent (".LogAktivity");
                startActivity (satu);
            }
        });
    }



    public void sendNotification(String msg){
        Intent resultIntent = new Intent (this, NotifikasiFragment.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity (this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notification = (NotificationCompat.Builder) new NotificationCompat
                .Builder(this)
                .setSmallIcon(R.mipmap.ic_loncer)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_loncer))
                .setContentTitle(getResources().getString(R.string.content_title))
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound (defaultSoundUri)
                .setContentIntent (resultPendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICATION_ID, notification.build());
    }

    @Override
    public void onResume() {
        super.onResume();
        //loadData ();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onPause() {
        super.onPause();
        //loadData ();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
    }

    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras) {
        if (!UsbService.SERVICE_CONNECTED) {
            Intent startService = new Intent(this, service);
            if (extras != null && !extras.isEmpty()) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);
        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        registerReceiver(mUsbReceiver, filter);
    }



    /*
     * This handler will be passed to UsbService. Data received from serial port is displayed through this handler
     */
    public class MyHandler extends Handler {
        public final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<>(activity);
    }

        @Override
        public void handleMessage(Message msg) {



            switch (msg.what) {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:
                    String data = (String) msg.obj;
                    mActivity.get().display.append(data);
                    if(data.equals ("7")){
                        //nih pokonya oprek disini
                        sendNotification(String.valueOf ("Bimbingan"));
                    }else if(data.equals ("8")){
                        sendNotification (String.valueOf ("Perwalian"));
                    }else if (data.equals ("9")){
                        sendNotification (String.valueOf ("Konsultasi"));
                    }
                    break;
                case UsbService.CTS_CHANGE:
                    Toast.makeText(mActivity.get(), "CTS_CHANGE",Toast.LENGTH_LONG).show();
                    break;
                case UsbService.DSR_CHANGE:
                    Toast.makeText(mActivity.get(), "DSR_CHANGE",Toast.LENGTH_LONG).show();
                    break;
            }


        }



    }
}