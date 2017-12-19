package com.jeplin.hackathonqm.RetroFit.DoctorNextPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorNextData {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("otp")
@Expose
private String otp;
@SerializedName("data")
@Expose
private Data data;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getOtp() {
return otp;
}

public void setOtp(String otp) {
this.otp = otp;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

}