package com.example.smsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtPhone,edtMessage;
    private Button btnSendSms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPhone = findViewById(R.id.edt_phone);
        edtMessage = findViewById(R.id.edt_message);
        btnSendSms = findViewById(R.id.btn_send);

        btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS);
                if (permission== PackageManager.PERMISSION_GRANTED){
                    sendSms();

                }else {
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},111);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 111:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    sendSms();
                }else {
                    Toast.makeText(this,"We Need SMS Permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    private void sendSms() {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(edtPhone.getText().toString().trim(),null,edtMessage.getText().toString().trim(),null,null);
        Toast.makeText(this,"Message Sent",Toast.LENGTH_SHORT).show();
    }
}
