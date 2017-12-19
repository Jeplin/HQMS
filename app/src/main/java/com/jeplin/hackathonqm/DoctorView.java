package com.jeplin.hackathonqm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jeplin.hackathonqm.RetroFit.DoctorPojo.DoctorData;
import com.jeplin.hackathonqm.RetroFit.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorView extends AppCompatActivity {

    JSONObject jsonObject;
    TextView doctorName,departmentName,mobileNumber,floorNumber,roomNumber,totalToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_view);

        this.setTitle("Doctor Portal");


        declareVariable();
        getDoctorData();
    }
    public void declareVariable(){
        doctorName=(TextView)findViewById(R.id.txt_doctorname);
        departmentName=(TextView)findViewById(R.id.txt_department);
        mobileNumber=(TextView)findViewById(R.id.txt_mobile);
        floorNumber=(TextView)findViewById(R.id.txt_floor);
        roomNumber=(TextView)findViewById(R.id.txt_room);
        totalToken=(TextView)findViewById(R.id.txt_tot_token);


        emptyAllVariable();
    }
    public void emptyAllVariable(){
        doctorName.setText("");
        departmentName.setText("");
        mobileNumber.setText("");
        floorNumber.setText("");
        roomNumber.setText("");
        totalToken.setText("");
    }
    public void getDoctorData(){

        SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);

        String doctorNumber=sharedPreferences.getString("doctorNumber","");

        ApiConnectionData(doctorNumber);

//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString("doctorNumber",doctorNumber);
//        editor.putBoolean("loginFlag",true);
//        editor.apply();

//        Intent intent=getIntent();
//        String DOCTOR_ID=intent.getStringExtra("doctor_id");
//
//        String jsonPatientStr="{\"status\":true,\"data\":{\"patient_name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"patient_phone\":9914466774,\"patient_email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"patient_token\":5,\"token_status\":1,\"token_total\":10}}";
//        String jsonDoctorPhoneStr="{\"status\":true,\"otp\":1234,\"data\":{\"name\":\"Dr.Summet Verma\",\"phone\":9914477885,\"department\":\"Medicine\",\"floor\":2,\"room_no\":45,\"token_total\":50}}";
//        String jsonPhoneNumberStr="{\"status\":true,\"otp\":1234,\"data\":{\"patient\":{\"name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"phone\":9914466774,\"email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"token\":5},\"token\":{\"status\":1,\"total\":10}}}";
//
//        try {
//            jsonObject=new JSONObject(jsonDoctorPhoneStr);
//
//            JSONObject dataObj=jsonObject.getJSONObject("data");
//            Log.d("data1","Data ---"+dataObj);
//            setDataInUI(dataObj);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("Catch","Error 1");
//        }
    }
    public void ApiConnectionData(String DOCTOR_NUMBER){
        UserService.getApiClient().getDoctorData(DOCTOR_NUMBER).enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(Call<DoctorData> call, Response<DoctorData> response) {
                if(response.isSuccessful()){


                    //String dataPhoneNumber=response.body().getData().getPhone().toString();
                    //String dataOTP=response.body().getOtp().toString();

                    setDataInUI(response.body());
                }
                else{
                    Toast.makeText(DoctorView.this,"Data Not Available",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DoctorData> call, Throwable t) {
                Toast.makeText(DoctorView.this,"Unable to fetch data from server.",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setDataInUI(DoctorData jsonObj){

            doctorName.setText("Hi!  "+jsonObj.getData().getName().toString());
            departmentName.setText(jsonObj.getData().getDepartment().toString());
            mobileNumber.setText(jsonObj.getData().getPhone().toString());
            floorNumber.setText(jsonObj.getData().getFloor().toString());
            roomNumber.setText(jsonObj.getData().getRoomNo().toString());
            totalToken.setText(jsonObj.getData().getTokenTotal().toString());

    }
    public void startBtnClicked(View v){
        Log.d("Start","Start Btn Clicked");
        Intent intent=new Intent(this,DoctorCounterView.class);
        //intent.putExtra("doctorData",jsonObject);
        startActivity(intent);
    }
    public void exitMethod(View v){
        Intent intent=new Intent(this,MainActivity.class);

        startActivity(intent);

        SharedPreferences sharedPreferences=getSharedPreferences("doctorLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("loginFlag",false);
        editor.apply();
    }

}
