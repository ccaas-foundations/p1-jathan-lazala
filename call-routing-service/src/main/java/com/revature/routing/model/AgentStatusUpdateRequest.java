package com.revature.routing.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AgentStatusUpdateRequest {

    private final String status;

    @JsonCreator
    public AgentStatusUpdateRequest(@JsonProperty("status") String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
