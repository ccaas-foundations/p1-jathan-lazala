package com.revature.agent.dto;

import com.revature.agent.entity.CallCategory;

import java.time.LocalDateTime;

public class InboundCallResponse {

    private Long id;
    private String callId;
    private String callerNumber;
    private CallCategory callCategory;
    private Long assignedAgentId;
    private LocalDateTime receivedAt;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CallCategory getCallCategory() {
        return callCategory;
    }

    public void setCallCategory(CallCategory callCategory) {
        this.callCategory = callCategory;
    }

    public Long getAssignedAgentId() {
        return assignedAgentId;
    }

    public void setAssignedAgentId(Long assignedAgentId) {
        this.assignedAgentId = assignedAgentId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
