package com.jeplin.hackathonqm.RetroFit.DoctorPojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

@SerializedName("name")
@Expose
private String name;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("department")
@Expose
private String department;
@SerializedName("floor")
@Expose
private Integer floor;
@SerializedName("room_no")
@Expose
private String roomNo;
@SerializedName("token_total")
@Expose
private Integer tokenTotal;

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
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

public String getRoomNo() {
return roomNo;
}

public void setRoomNo(String roomNo) {
this.roomNo = roomNo;
}

public Integer getTokenTotal() {
return tokenTotal;
}

public void setTokenTotal(Integer tokenTotal) {
this.tokenTotal = tokenTotal;
}

}