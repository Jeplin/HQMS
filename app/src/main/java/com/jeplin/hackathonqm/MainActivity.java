package com.jeplin.hackathonqm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView txtDate,txtTime,txtTimeAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().getDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.action_bar);

//        ViewGroup actionBarLayout = (ViewGroup) getLayoutInflater().inflate(
//                R.layout.action_bar,
//                null);
//
//        // Set up your ActionBar
////        ActionBar actionBar = getActionBar();
//        android.app.ActionBar actionBar=getActionBar();
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setCustomView(actionBarLayout);



//        Calendar cal = Calendar.getInstance();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        cal.add(Calendar.DATE, -1);
//        Log.d("Date",dateFormat.format(cal.getTime()));

        txtDate=(TextView)findViewById(R.id.txt_date);
        txtTime=(TextView)findViewById(R.id.txt_time);
        txtTimeAM=(TextView)findViewById(R.id.txt_timeAM);
        getTime();

        Timer t = new Timer();
//Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {
                                      //Called each time when 1000 milliseconds (1 second) (the period parameter)

                                      runOnUiThread(new Runnable() {

                                          @Override
                                          public void run() {
                                              getTime();
                                          }

                                      });
                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                0,
//Set the amount of time between each execution (in milliseconds)
                1000);

    }
    public void getTime(){
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dfDate = new SimpleDateFormat("MMM d,yyyy");
        String formattedDate = dfDate.format(c.getTime());
        txtDate.setText(formattedDate);
        SimpleDateFormat dfTime = new SimpleDateFormat("HH:mm");
        String formattedTime = dfTime.format(c.getTime());
        txtTime.setText(formattedTime);
        SimpleDateFormat dfAM = new SimpleDateFormat("a");
        String formattedAM = dfAM.format(c.getTime());
        txtTimeAM.setText(formattedAM);
    }

    public void doctorBtnClicked(View v){

        SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);

        if (sharedPreferences!=null){

            String DOCTOR_ID=sharedPreferences.getString("doctorNumber","");
            Boolean loginFlag=sharedPreferences.getBoolean("loginFlag",false);

            if (loginFlag){
                Intent intent=new Intent(this,DoctorView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("doctor_id",DOCTOR_ID);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, DoctorLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
        else{
            Log.d("Doctor", "Doctor Button Clicked");
            Intent intent = new Intent(this, DoctorLogin.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void patientBtnClicked(View v){
        Log.d("Patient","Patient Button Clicked");
        Intent intent=new Intent(this,PatientLogin.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
