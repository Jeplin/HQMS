package com.jeplin.hackathonqm.RetroFit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("patient_name")
@Expose
private String patientName;
@SerializedName("department")
@Expose
private String department;
@SerializedName("floor")
@Expose
private Integer floor;
@SerializedName("room_number")
@Expose
private String roomNumber;
@SerializedName("doctor_name")
@Expose
private String doctorName;
@SerializedName("patient_phone")
@Expose
private String patientPhone;
@SerializedName("patient_email")
@Expose
private String patientEmail;
@SerializedName("crno")
@Expose
private String crno;
@SerializedName("waiting_hall")
@Expose
private String waitingHall;
@SerializedName("patient_token")
@Expose
private Integer patientToken;
@SerializedName("token_status")
@Expose
private Integer tokenStatus;
@SerializedName("token_total")
@Expose
private Integer tokenTotal;

public String getPatientName() {
return patientName;
}

public void setPatientName(String patientName) {
this.patientName = patientName;
}

public String getDepartment() {
return department;
}

public void setDepartment(String department) {
this.department = department;
}

public Integer getFloor() {
return floor;
}

public void setFloor(Integer floor) {
this.floor = floor;
}

public String getRoomNumber() {
return roomNumber;
}

public void setRoomNumber(String roomNumber) {
this.roomNumber = roomNumber;
}

public String getDoctorName() {
return doctorName;
}

public void setDoctorName(String doctorName) {
this.doctorName = doctorName;
}

public String getPatientPhone() {
return patientPhone;
}

public void setPatientPhone(String patientPhone) {
this.patientPhone = patientPhone;
}

public String getPatientEmail() {
return patientEmail;
}

public void setPatientEmail(String patientEmail) {
this.patientEmail = patientEmail;
}

public String getCrno() {
return crno;
}

public void setCrno(String crno) {
this.crno = crno;
}

public String getWaitingHall() {
return waitingHall;
}

public void setWaitingHall(String waitingHall) {
this.waitingHall = waitingHall;
}

public Integer getPatientToken() {
return patientToken;
}

public void setPatientToken(Integer patientToken) {
this.patientToken = patientToken;
}

public Integer getTokenStatus() {
return tokenStatus;
}

public void setTokenStatus(Integer tokenStatus) {
this.tokenStatus = tokenStatus;
}

public Integer getTokenTotal() {
return tokenTotal;
}

public void setTokenTotal(Integer tokenTotal) {
this.tokenTotal = tokenTotal;
}

}