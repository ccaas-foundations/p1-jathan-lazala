package com.revature.routing.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class InboundCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String callId;

    @Column(nullable = false)
    private String callerNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CallCategory callCategory;

    @Column(nullable = false)
    private Long assignedAgentId;

    @Column(nullable = false)
    private LocalDateTime receivedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InboundCallStatus status;

    public InboundCall() {
    }

    @PrePersist
    public void prePersist() {
        if (receivedAt == null) {
            receivedAt = LocalDateTime.now();
        }
        if (status == null) {
            status = InboundCallStatus.ASSIGNED;
        }
    }

    public Long getId() {
        return id;
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

    public InboundCallStatus getStatus() {
        return status;
    }

    public void setStatus(InboundCallStatus status) {
        this.status = status;
    }
}
