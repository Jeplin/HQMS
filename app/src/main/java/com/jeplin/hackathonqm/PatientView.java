package com.jeplin.hackathonqm;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class PatientView extends AppCompatActivity {

    TextView patientName,departmentName,doctorName,patientNumber,patientEmail,floorNumber,roomNumber,waitingHall,yourToken,currentToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        this.setTitle("Patient Portal");



        declareVariable();
        getPatientData();
    }
    public void declareVariable(){
        patientName=(TextView)findViewById(R.id.txt_patientname);
        departmentName=(TextView)findViewById(R.id.txt_department);
        doctorName=(TextView)findViewById(R.id.txt_doctor);
        patientNumber=(TextView)findViewById(R.id.txt_patientNumber);
        patientEmail=(TextView)findViewById(R.id.txt_patientEmail);
        floorNumber=(TextView)findViewById(R.id.txt_FloorNumber);
        roomNumber=(TextView)findViewById(R.id.txt_RoomNumber);
        waitingHall=(TextView)findViewById(R.id.txt_waitHall);
        yourToken=(TextView)findViewById(R.id.txt_your_token);
        currentToken=(TextView)findViewById(R.id.txt_current_token);


        emptyVariable();
    }
    public void emptyVariable(){
        patientName.setText("");

        departmentName.setText("");

        doctorName.setText("");

        patientNumber.setText("");

        patientEmail.setText("");

        floorNumber.setText("");

        roomNumber.setText("");

        waitingHall.setText("");

        yourToken.setText("");

        currentToken.setText("");
    }

    public void getPatientData(){
//        String jsonPatientStr="{\"status\":true,\"data\":{\"patient_name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"patient_phone\":9914466774,\"patient_email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"patient_token\":5,\"token_status\":1,\"token_total\":10}}";
//        String jsonDoctorPhoneStr="{\"status\":true,\"otp\":1234,\"data\":{\"name\":\"Dr.Summet Verma\",\"phone\":9914477885,\"department\":\"Medicine\",\"floor\":2,\"room_no\":45,\"token_total\":50}}";
//        String jsonPhoneNumberStr="{\"status\":true,\"otp\":1234,\"data\":{\"patient\":{\"name\":\"ABC\",\"department\":\"Medicine\",\"floor\":2,\"room_number\":45,\"doctor_name\":\"Dr. Summit Verma\",\"phone\":9914466774,\"email\":\"hjhjhj@gmail.com\",\"crno\":545454545,\"waiting_hall\":\"XZ\",\"token\":5},\"token\":{\"status\":1,\"total\":10}}}";
//
//        try {
//            JSONObject jsonObject=new JSONObject(jsonPatientStr);
//
//            JSONObject dataObj=jsonObject.getJSONObject("data");
//            Log.d("data1","Data ---"+dataObj);
//            setUIData(dataObj);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.d("Catch","Error 1");
//        }
        Intent intent=getIntent();
        String patient_crno=intent.getStringExtra("patient_crno");
        String device_id=intent.getStringExtra("device_id");


        APIdataConnectio(patient_crno,device_id);
    }
    public void APIdataConnectio(final String CRNO, String DEVICE_TOKEN){
       // Toast.makeText(this,"Called clicked",Toast.LENGTH_SHORT).show();

        UserService.getApiClient().getPatientData(CRNO,DEVICE_TOKEN).enqueue(new Callback<PatientData>() {
            @Override
            public void onResponse(Call<PatientData> call, Response<PatientData> response) {
                if (response.isSuccessful()){
                    //Toast.makeText(PatientView.this,"Data Available",Toast.LENGTH_SHORT).show();

                    //text_test.setText(response.body().getData().getDoctorName().toString());

                    //String dataCRNO=response.body().getData().getCrno().toString();
                    //dataCRNO=dataCRNO.replaceAll(" ","");
                    PatientData jsonObject=response.body();
                    setUIData(jsonObject);
                }
                else{
                    Toast.makeText(PatientView.this,"Data Not available",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PatientData> call, Throwable t) {
                Toast.makeText(PatientView.this,"Unable to Fetch Data from server.",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setUIData(PatientData jsonObject){

        Log.d("SetUI","Set Ui Function");
       // JSONObject dataObject=jsonObject;

//        try {
            patientName.setText("Hi!  "+jsonObject.getData().getPatientName().toString());
            departmentName.setText(jsonObject.getData().getDepartment().toString());
            doctorName.setText(jsonObject.getData().getDoctorName().toString());
            patientNumber.setText(jsonObject.getData().getPatientPhone().toString());
            patientEmail.setText(jsonObject.getData().getPatientEmail().toString());
            floorNumber.setText(jsonObject.getData().getFloor().toString());
            roomNumber.setText(jsonObject.getData().getRoomNumber().toString());
            waitingHall.setText(jsonObject.getData().getWaitingHall().toString());
            yourToken.setText(jsonObject.getData().getPatientToken().toString());
            currentToken.setText(jsonObject.getData().getTokenStatus().toString());

//        } catch (JSONException e) {
//            e.printStackTrace();
//            Toast.makeText(this,"Something went wrong.Please try again",Toast.LENGTH_LONG).show();
//
//            finish();
//        }
    }
    public void refreshBtnMethod(View v){
        Log.d("Refresh","Refresh btn clicked");
        emptyVariable();
        getPatientData();
        Toast.makeText(this,"Refreshing Data",Toast.LENGTH_LONG).show();
    }
    public void backBtnClicked(View v){
        Log.d("Back","Back btn clicked");

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
