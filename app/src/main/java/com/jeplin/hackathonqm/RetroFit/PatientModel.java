package com.jeplin.hackathonqm.RetroFit;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

/**
 * Created by jeplin on 23-09-2017.
 */

public class PatientModel {
    @SerializedName("status")
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    @SerializedName("data")
    private JSONObject data;




}
