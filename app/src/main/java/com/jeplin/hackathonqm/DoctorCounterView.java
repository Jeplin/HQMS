package com.jeplin.hackathonqm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jeplin.hackathonqm.RetroFit.DoctorNextPojo.DoctorNextData;
import com.jeplin.hackathonqm.RetroFit.DoctorPojo.DoctorData;
import com.jeplin.hackathonqm.RetroFit.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorCounterView extends AppCompatActivity {
    //JSONObject jsonObject;

    TextView doctorName,curretAppointmentStatus,patientName,patientDepartment,patientNumber,patientEmail,patientRoom,patientFloor;
    Button BTN_NEXT,BTN_SKIP,BTN_EXIT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_counter_view);

        declareVariable();
        getDoctorData();
    }
    public void declareVariable(){
        doctorName=(TextView)findViewById(R.id.txt_hi_doctor);
        curretAppointmentStatus=(TextView)findViewById(R.id.txt_curr_token);
        patientName=(TextView)findViewById(R.id.txt_patient);
        patientDepartment=(TextView)findViewById(R.id.txt_department);
        patientNumber=(TextView)findViewById(R.id.txt_mobile);
        patientEmail=(TextView)findViewById(R.id.txt_email);
        patientRoom=(TextView)findViewById(R.id.txt_Room_no);
        patientFloor=(TextView)findViewById(R.id.txt_floor_no);
        //txtResponse=(EditText)findViewById(R.id.txt_Response);

        BTN_NEXT=(Button)findViewById(R.id.btn_next);
        //BTN_NEXT.setEnabled(true);

        BTN_SKIP=(Button)findViewById(R.id.btn_skip);
        //BTN_SKIP.setEnabled(true);
        BTN_EXIT=(Button)findViewById(R.id.btn_EXIT);
        //BTN_EXIT.setEnabled(true);

        emptyAllVariable();
    }
    public void emptyAllVariable(){
        doctorName.setText("");
        curretAppointmentStatus.setText("");
        patientName.setText("");
        patientDepartment.setText("");
        patientNumber.setText("");
        patientEmail.setText("");
        patientRoom.setText("");
        patientFloor.setText("");
//        txtResponse.setText("");
    }
    public void getDoctorData(){

        SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);

        String doctorNumber=sharedPreferences.getString("doctorNumber","");

        ApiConnectionData(doctorNumber);


    }
    public void ApiConnectionData(String DOCTOR_NUMBER){
        UserService.getApiClient().getDoctorNextData(DOCTOR_NUMBER).enqueue(new Callback<DoctorNextData>() {
            @Override
            public void onResponse(Call<DoctorNextData> call, Response<DoctorNextData> response) {
                if (response.isSuccessful()){

                    String status=response.body().getData().getToken().getStatus().toString();
                    String total=response.body().getData().getToken().getTotal().toString();

                    if (status.equals(total)){
                        BTN_NEXT.setEnabled(false);
                    }

                    setDataInUI(response.body());

                }
                else{
                    Toast.makeText(DoctorCounterView.this,"Data Not Available",Toast.LENGTH_SHORT).show();
                    setNADataToUI();
                }
            }

            @Override
            public void onFailure(Call<DoctorNextData> call, Throwable t) {
                Toast.makeText(DoctorCounterView.this,"Unable to fetch data from Server.",Toast.LENGTH_SHORT).show();
                setNADataToUI();

            }
        });
    }
    public void setDataInUI(DoctorNextData jsonData){
            //Display Doctor name
            //JSONObject patientObj=patientData.getJSONObject("patient");
            doctorName.setText("Hi!  "+jsonData.getData().getPatient().getDoctorName().toString());
            //Display Patient
            patientName.setText(jsonData.getData().getPatient().getName().toString());
            patientDepartment.setText(jsonData.getData().getPatient().getDepartment().toString());
            patientNumber.setText(jsonData.getData().getPatient().getPhone().toString());
            patientEmail.setText(jsonData.getData().getPatient().getEmail().toString());
            patientRoom.setText(jsonData.getData().getPatient().getRoomNumber().toString());
            patientFloor.setText(jsonData.getData().getPatient().getFloor().toString());
            //JSONObject tokenData=patientData.getJSONObject("token");
            String tokenStr=jsonData.getData().getToken().getStatus().toString()+" / "+jsonData.getData().getToken().getTotal().toString();
            curretAppointmentStatus.setText(tokenStr);

    }
    public void setNADataToUI(){
        doctorName.setText("Hi! NA ");
        //Display Patient
        patientName.setText("NA");
        patientDepartment.setText("NA");
        patientNumber.setText("NA");
        patientEmail.setText("NA");
        patientRoom.setText("NA");
        patientFloor.setText("NA");
        curretAppointmentStatus.setText("NA");


        //JSONObject tokenData=patientData.getJSONObject("token");
        //String tokenStr=jsonData.getData().getToken().getStatus().toString()+" / "+jsonData.getData().getToken().getTotal().toString();

    }
    public void nextBtnMethod(View v){
        Log.d("Next","Next Button Clicked");

        Toast.makeText(this,"Next Patient!",Toast.LENGTH_SHORT).show();
            getDoctorData();
//        txtResponse.setText("");

    }
    public void skipBtnMethod(View v){
        getDoctorData();
//        txtResponse.setText("");
    }
    public void exitBtnMethod(View v){
        Intent exitIntent=new Intent(this,MainActivity.class);
        startActivity(exitIntent);

        SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("loginFlag",false);
        editor.apply();
    }
}
