package com.jeplin.hackathonqm.RetroFit.DoctorPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorData {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("otp")
@Expose
private Integer otp;
@SerializedName("data")
@Expose
private Data data;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public Integer getOtp() {
return otp;
}

public void setOtp(Integer otp) {
this.otp = otp;
}

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}

}