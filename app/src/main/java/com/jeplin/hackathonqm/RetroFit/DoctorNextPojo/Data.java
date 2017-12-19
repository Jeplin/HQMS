package com.jeplin.hackathonqm.RetroFit.DoctorNextPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("patient")
@Expose
private Patient patient;
@SerializedName("token")
@Expose
private Token token;

public Patient getPatient() {
return patient;
}

public void setPatient(Patient patient) {
this.patient = patient;
}

public Token getToken() {
return token;
}

public void setToken(Token token) {
this.token = token;
}

}