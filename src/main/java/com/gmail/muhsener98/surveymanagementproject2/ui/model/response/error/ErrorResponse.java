package com.gmail.muhsener98.surveymanagementproject2.ui.model.response.error;

import java.io.Serializable;
import java.util.Date;

public class ErrorResponse  implements Serializable {

    private String message;
    private Date timestamp;


    public ErrorResponse(String message, Date timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
