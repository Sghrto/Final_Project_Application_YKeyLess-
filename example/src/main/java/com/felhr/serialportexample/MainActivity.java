package com.felhr.serialportexample;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
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


    ToggleButton toggleButton,toggleButton2;

    public static final int NOTIFICATION_ID = 1;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OnClicktombolListener();
        mHandler = new MyHandler(this);

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
        swit1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!swit1.getText ( ).toString ( ).equals ("")) {
                    String data = new String ();
                        if (bChecked) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit1.getTextOn ( ).toString ( );
                            }
                            Toast.makeText (getApplicationContext (),"AKTIF", Toast.LENGTH_LONG).show ();
                        }
                        else {
                             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                 data = swit1.getTextOff ( ).toString ( );//editText.getText().toString();
                             }
                            Toast.makeText (getApplicationContext (),"NON-AKTIF", Toast.LENGTH_LONG).show ();
                        }

                    if  (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write (data.getBytes ( ));
                    }
                }
            }

            

        });



        display = (TextView) findViewById(R.id.sugi);
        Switch swit2 = (Switch) findViewById (R.id.swit2);
        swit2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (!swit2.getText ( ).toString ( ).equals ("")) {
                    String data = new String();
                        if (bChecked) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit2.getTextOn ( ).toString ( );
                            }
                            Toast.makeText (getApplicationContext (),"AKTIF", Toast.LENGTH_LONG).show ();
                        }
                        else {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                                data = swit2.getTextOff ().toString ();//editText.getText().toString();
                            }
                            Toast.makeText (getApplicationContext (),"NON-AKTIF", Toast.LENGTH_LONG).show ();
                        }
                    if  (usbService != null) { // if UsbService was correctly binded, Send data
                        usbService.write (data.getBytes ( ));
                    }
                }
            }

        });

    }



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
                .setSmallIcon(R.drawable.ic_notification_white_48px)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification_white_48px))
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
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onPause() {
        super.onPause();
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
                        sendNotification(String.valueOf ("ketemuan"));
                    }else if(data.equals ("8")){
                        sendNotification (String.valueOf (data));
                    }else if (data.equals ("9")){
                        sendNotification (String.valueOf (data));
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