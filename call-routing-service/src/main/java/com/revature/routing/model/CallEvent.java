package com.revature.routing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "callEvent")
public class CallEvent {

    @JacksonXmlProperty(localName = "callId")
    private String callId;

    @JacksonXmlProperty(localName = "callerNumber")
    private String callerNumber;

    @JacksonXmlProperty(localName = "callCategory")
    private String callCategory;

    @JacksonXmlProperty(localName = "callerName")
    private String callerName;

    @JacksonXmlProperty(localName = "timestamp")
    private String timestamp;

    public CallEvent() {
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public String getCallCategory() {
        return callCategory;
    }

    public void setCallCategory(String callCategory) {
        this.callCategory = callCategory;
    }

    public String getCallerName() {
        return callerName;
    }

    public void setCallerName(String callerName) {
        this.callerName = callerName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
