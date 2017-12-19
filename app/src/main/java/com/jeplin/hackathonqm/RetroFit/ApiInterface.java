package com.jeplin.hackathonqm.RetroFit;

import com.jeplin.hackathonqm.RetroFit.DoctorNextPojo.DoctorNextData;
import com.jeplin.hackathonqm.RetroFit.DoctorPojo.DoctorData;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jeplin on 23-09-2017.
 */

public interface ApiInterface {



//    @GET("{crno}/{device_id}")
//    Call<ArrayList<PatientData>> getPatientData(@Path("crno")String CRNO, @Path("device_id")String DEVICE_ID);

    @GET("get-patient/{crno}/{device_id}")
    Call<PatientData> getPatientData(@Path("crno")String CRNO, @Path("device_id")String DEVICE_ID);


    @GET("is-doctor-valid/{doctor_phone}")
    Call<DoctorData> getDoctorData(@Path("doctor_phone")String DOCTOR_PHONE);

    @GET("token-status/{doctor_phone}")
    Call<DoctorNextData> getDoctorNextData(@Path("doctor_phone")String DOCTOR_PHONE);

}
