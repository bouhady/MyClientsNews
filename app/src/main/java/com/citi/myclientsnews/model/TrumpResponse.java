package com.citi.myclientsnews.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yb34982 on 21/03/2018.
 */

public class TrumpResponse {

    @SerializedName("ResponseText")
    @Expose
    private String responseText;

    public TrumpResponse(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

}