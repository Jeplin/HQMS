package com.jeplin.hackathonqm.RetroFit.DoctorNextPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Patient {

@SerializedName("name")
@Expose
private String name;
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
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("email")
@Expose
private String email;
@SerializedName("crno")
@Expose
private String crno;
@SerializedName("waiting_hall")
@Expose
private String waitingHall;
@SerializedName("token")
@Expose
private Integer token;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
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

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
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

public Integer getToken() {
return token;
}

public void setToken(Integer token) {
this.token = token;
}

}