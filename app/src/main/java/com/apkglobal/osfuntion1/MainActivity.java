package com.apkglobal.osfuntion1;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
/*Declaration of java object for xml*/
    Button share,web,camera,call,wifi,bluetooth;
    Boolean b=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        share = findViewById(R.id.share);
        web = findViewById(R.id.web);
        camera = findViewById(R.id.camera);
        call = findViewById(R.id.call);
        wifi = findViewById(R.id.wifi);
        bluetooth = findViewById(R.id.bluetooh);

        call.setOnClickListener(this);
        share.setOnClickListener(this);
        web.setOnClickListener(this);
        camera.setOnClickListener(this);
        wifi.setOnClickListener(this);
        bluetooth.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        int item = view.getId();
        switch(item)
        {
            case R.id.share:
               /*for share to application */
            Intent share = new Intent(Intent.ACTION_SEND);
            /*to attach the data with intent*/
            share.putExtra(Intent.EXTRA_TEXT, "please download application"+"\n"+
                    "https://play.google.com/store/apps/details?id=com.appsquadz.app.fileexplorerproject&hl=en");
            /*to define datatype*/
                share.setType("text/plain");
                /*to share the data to another application*/
                startActivity(Intent.createChooser(share,"Share App Via"));
                break;
            case R.id.web:
              /*  OPEN THE WEB SITE*/
                Intent web = new Intent(Intent.ACTION_VIEW);
                /*TO ATTACH THE URL*/
                web.setData(Uri.parse("http://www.bsfshshillong.org.in/"));
                startActivity(web);

                break;
            case R.id.call:
                /*Intent callIntent = new Intent(Intent.ACTION_CALL);
                //callIntent.setData(Uri.parse("tel:"+8802177690));//change the number.
                startActivity(callIntent);*/
                callatruntime();


                break;
            case R.id.camera:
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(camera);
                break;
            case R.id.wifi:
                WifiManager wm = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
                wm.setWifiEnabled(true);
                if(b==false)
                {
                    wm.setWifiEnabled(true);
                    b=true;
                }
                else
                {
                    wm.setWifiEnabled(false);
                    b=false;
                }
                break;
            case R.id.bluetooh:
                BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
                /*to enable the bluetooth*/
                if(b==false)
                {
                    ba.enable();
                    b=true;
                }
                else
                {
                    ba.disable();
                    b=false;
                }

                break;

        }
    }


    private void callatruntime() {
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_DENIED)
        {
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 1);
        }
        else
        {
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:9835463057"));
            startActivity(call);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                callatruntime();
            }
            else
            {
                Toast.makeText(this,"User denied", Toast.LENGTH_LONG).show();
            }
        }
        }
    }

