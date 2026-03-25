package com.revature.routing.entity;


import com.revature.agent.entity.Agent;
import com.revature.agent.entity.CallCategory;
import com.revature.agent.entity.InboundCallStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;


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

    @ManyToOne
    @JoinColumn(name = "assigned_agent_id")
    private Agent assignedAgent;

    @Column(nullable = false)
    private LocalDateTime recievedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InboundCallStatus status;

    public InboundCall() {

    }

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

    public LocalDateTime getRecievedAt() {
        return recievedAt;
    }

    public void setRecievedAt(LocalDateTime recievedAt) {
        this.recievedAt = recievedAt;
    }

    public InboundCallStatus getStatus() {
        return status;
    }

    public void setStatus(InboundCallStatus status) {
        this.status = status;
    }

    public Agent getAssignedAgent() {
        return assignedAgent;
    }

    public void setAssignedAgent(Agent assignedAgent) {
        this.assignedAgent = assignedAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InboundCall that = (InboundCall) o;
        return Objects.equals(id, that.id) && Objects.equals(callId, that.callId) && Objects.equals(callerNumber, that.callerNumber) && callCategory == that.callCategory && Objects.equals(assignedAgent, that.assignedAgent) && Objects.equals(recievedAt, that.recievedAt) && status == that.status;
    }

    @Override
    public String toString() {
        return "InboundCall{" +
                "id=" + id +
                ", callId='" + callId + '\'' +
                ", callerNumber='" + callerNumber + '\'' +
                ", callCategory=" + callCategory +
                ", assignedAgent=" + assignedAgent +
                ", recievedAt=" + recievedAt +
                ", status=" + status +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, callId, callerNumber, callCategory, assignedAgent, recievedAt, status);
    }
}
