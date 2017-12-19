package com.jeplin.hackathonqm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorOTP extends AppCompatActivity {
    String otpData;
    String doctorNumber;
    EditText otpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_otp);

        otpText=(EditText)findViewById(R.id.txtEdit_OTP);

        Intent intent=getIntent();
        otpData=intent.getStringExtra("otp").toString();
        doctorNumber=intent.getStringExtra("doctorNumber").toString();
        otpData=otpData.replaceAll(" ","");
        doctorNumber=doctorNumber.replaceAll(" ","");
        Toast.makeText(this,"Your OTP code is "+otpData,Toast.LENGTH_SHORT).show();
    }

    public void submitBtnClicked(View v){

        String strOTP=otpText.getText().toString();
        strOTP=strOTP.replaceAll(" ","");
        otpText.setText("");

        if (strOTP.equals("") || strOTP.equals(" ") || strOTP==null){
            Toast.makeText(this,"Enter OTP Code",Toast.LENGTH_SHORT).show();
            return;
        }
        if (strOTP.equals(otpData)){
            Log.d("Match","Success");
            Intent intent=new Intent(this,DoctorView.class);
            intent.putExtra("doctor_id",doctorNumber);
            startActivity(intent);
            finish();


            SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("doctorNumber",doctorNumber);
            editor.putBoolean("loginFlag",true);
            editor.apply();


        }
        else{
            Toast.makeText(this,"Invalid OTP Enter",Toast.LENGTH_SHORT).show();
        }


    }
}
