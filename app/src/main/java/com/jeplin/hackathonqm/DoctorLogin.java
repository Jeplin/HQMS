package com.jeplin.hackathonqm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jeplin.hackathonqm.RetroFit.DoctorPojo.DoctorData;
import com.jeplin.hackathonqm.RetroFit.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorLogin extends AppCompatActivity {
    JSONObject jsonObject;
    EditText doctorNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        doctorNumber=(EditText)findViewById(R.id.txtEdit_doctor);


    }

    public void nextBtnClicked(View v){
        if (doctorNumber.getText().toString().equals("") || doctorNumber.getText().toString().equals(" ") || doctorNumber.getText().toString()==null){
            Toast.makeText(this,"Enter Registered Mobile Number.",Toast.LENGTH_SHORT).show();
            return;
        }else {
            getDoctorData();
        }
        doctorNumber.setText("");

        //API Function
//        APIdataConnectio();

//        String strDoctorNumber=doctorNumber.getText().toString();
//        strDoctorNumber=strDoctorNumber.replaceAll(" ","");
//        doctorNumber.setText("");
//        if (strDoctorNumber.equals("") || strDoctorNumber.equals(" ") || strDoctorNumber==null){
//            Toast.makeText(this,"Enter Your Registered Mobile Number",Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            JSONObject dataObject=jsonObject.getJSONObject("data");
//            String dataDoctorN0=dataObject.getString("phone").toString();
//            dataDoctorN0=dataDoctorN0.replaceAll(" ","");
//            Log.d("Data","Datat --"+strDoctorNumber+"---"+dataDoctorN0);
//            if (dataDoctorN0.equals(strDoctorNumber)){
//                Log.d("Match","Login Successfull");
//                Intent intent=new Intent(this,DoctorOTP.class);
//                intent.putExtra("otp",jsonObject.getString("otp").toString());
//                intent.putExtra("doctorNumber",dataDoctorN0);
//                startActivity(intent);
//            }
//            else{
//                Log.d("NotMatch","Not Match Found");
//                Toast.makeText(this,"User Not Found.Please Enter valide mobile number.",Toast.LENGTH_SHORT).show();
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this,"Unable to fetch data from server.",Toast.LENGTH_SHORT).show();
//        }
    }
    public void getDoctorData(){
//        String jsonPatientStr="{\"status\":true,\"data\":{\"patient_name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"patient_phone\":9914466774,\"patient_email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"patient_token\":5,\"token_status\":1,\"token_total\":10}}";
//        String jsonDoctorPhoneStr="{\"status\":true,\"otp\":1234,\"data\":{\"name\":\"Dr.Summet Verma\",\"phone\":9914477885,\"department\":\"Medicine\",\"floor\":2,\"room_no\":45,\"token_total\":50}}";
//        String jsonPhoneNumberStr="{\"status\":true,\"otp\":1234,\"data\":{\"patient\":{\"name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"phone\":9914466774,\"email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"token\":5},\"token\":{\"status\":1,\"total\":10}}}";
//
//
//        try {
//            jsonObject=new JSONObject(jsonDoctorPhoneStr);
//
//
//            Log.d("data1","Data ---"+jsonObject);
//            //setDataInUI(dataObj);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("Catch","Error 1");
//            Toast.makeText(this,"Something went wrong.Unable to fetch Doctor Data.",Toast.LENGTH_SHORT).show();
//        }
        final String txtPhoneNumber=doctorNumber.getText().toString();

        //Toast.makeText(this,"Number ---"+txtPhoneNumber,Toast.LENGTH_SHORT).show();

        UserService.getApiClient().getDoctorData(txtPhoneNumber).enqueue(new Callback<DoctorData>() {
            @Override
            public void onResponse(Call<DoctorData> call, Response<DoctorData> response) {
                if(response.isSuccessful()){


                    String dataPhoneNumber=response.body().getData().getPhone().toString();
                    String dataOTP=response.body().getOtp().toString();

                    if (dataPhoneNumber.equals(txtPhoneNumber)){
                        Intent intent=new Intent(DoctorLogin.this,DoctorOTP.class);
                        intent.putExtra("otp",dataOTP);
                        intent.putExtra("doctorNumber",dataPhoneNumber);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(DoctorLogin.this,"Invalid Phone Number.Please check and Enter valid registered phone number.".toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(DoctorLogin.this,"Data Not Available",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DoctorData> call, Throwable t) {
                Toast.makeText(DoctorLogin.this,"Unable to fetch data from server.",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
