package com.jeplin.hackathonqm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jeplin.hackathonqm.RetroFit.PatientData;
import com.jeplin.hackathonqm.RetroFit.UserService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientLogin extends AppCompatActivity {
    JSONObject patientObject;

    EditText txt_CRNO;

    TextView text_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        txt_CRNO=(EditText)findViewById(R.id.txtEdit_crno);
        txt_CRNO.setText("");
        text_test=(TextView)findViewById(R.id.test_text);


        //getJsonData();
    }
//    public void getJsonData(){
//        String jsonPatientStr="{\"status\":true,\"data\":{\"patient_name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"patient_phone\":9914466774,\"patient_email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"patient_token\":5,\"token_status\":1,\"token_total\":10}}";
//        String jsonDoctorPhoneStr="{\"status\":true,\"otp\":1234,\"data\":{\"name\":\"Dr.Summet Verma\",\"phone\":9914477885,\"department\":\"Medicine\",\"floor\":2,\"room_no\":45,\"token_total\":50}}";
//        String jsonPhoneNumberStr="{\"status\":true,\"otp\":1234,\"data\":{\"patient\":{\"name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"phone\":9914466774,\"email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"token\":5},\"token\":{\"status\":1,\"total\":10}}}";
//
//        try {
//            patientObject=new JSONObject(jsonPatientStr);
//
//            //Log.d("Data"," Data :  "+patientObject.getJSONObject("data"));
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//
//            Log.d("ErrorCatch","Error");
//        }
//    }
    public void submitBtnClicked(View v){
        // Fetch Device Token from shared folder

        SharedPreferences sharedPreferences=getSharedPreferences("device_token",MODE_PRIVATE);
        String DEVICE_TOKEN=sharedPreferences.getString("device","");
        Log.d("Device--","DeviceToken --"+DEVICE_TOKEN);

//API data connection---
        String strCRNO=txt_CRNO.getText().toString();
        strCRNO=strCRNO.replaceAll(" ","");

        APIdataConnectio(strCRNO,DEVICE_TOKEN);

//        if (strCRNO.equals("") || strCRNO.equals(" ") || strCRNO==null)
//        {
//            Toast.makeText(this,"Please Enter Your CRNo.",Toast.LENGTH_LONG).show();
//            return;
//        }
//        txt_CRNO.setText("");
//        try {
//            JSONObject dataObject=patientObject.getJSONObject("data");
//
//            String dataCRNO=dataObject.getString("crno").toString();
//            dataCRNO=dataCRNO.replaceAll(" ","");
//            Log.d("Submit","Datat Match" + strCRNO +"---"+dataCRNO);
//
//            if (dataCRNO.equals(strCRNO)){
//                Log.d("Success","Match"+dataCRNO);
//                Intent intent=new Intent(this,PatientView.class);
//                startActivity(intent);
//            }
//            else{
//                Log.d("NotMatch","Data not match");
//                Toast.makeText(this,"CR.NO not Found.Please check and try again.",Toast.LENGTH_LONG).show();
//            }
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this,"Unable to Fetch CRNo from Server.",Toast.LENGTH_LONG).show();
//        }

    }
    public void APIdataConnectio(final String CRNO, final String DEVICE_TOKEN){
       // Toast.makeText(this,"Called clicked",Toast.LENGTH_SHORT).show();

        UserService.getApiClient().getPatientData(CRNO,DEVICE_TOKEN).enqueue(new Callback<PatientData>() {
            @Override
            public void onResponse(Call<PatientData> call, Response<PatientData> response) {
                if (response.isSuccessful()){
                    Toast.makeText(PatientLogin.this,"Data Available",Toast.LENGTH_SHORT).show();

                    text_test.setText(response.body().getData().getDoctorName().toString());

                    String dataCRNO=response.body().getData().getCrno().toString();
                    dataCRNO=dataCRNO.replaceAll(" ","");

                    if (CRNO.equals(dataCRNO)){
                        Intent intent=new Intent(PatientLogin.this,PatientView.class);
                        intent.putExtra("device_id",DEVICE_TOKEN);
                        intent.putExtra("patient_crno",CRNO);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(PatientLogin.this,"Invalid CRNO.Please check and Enter valid CRNO.",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(PatientLogin.this,"Data Not available",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PatientData> call, Throwable t) {
                Toast.makeText(PatientLogin.this,"Unable to Fetch Data from server.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
