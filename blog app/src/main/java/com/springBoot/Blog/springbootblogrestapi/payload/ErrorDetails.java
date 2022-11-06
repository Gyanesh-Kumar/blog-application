package com.springBoot.Blog.springbootblogrestapi.payload;

import java.util.Date;

public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;

    //constructor
    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    //getters
    public Date getTimestamp() {

        return timestamp;
    }

    public String getMessage() {

        return message;
    }

    public String getDetails() {

        return details;
    }
}
