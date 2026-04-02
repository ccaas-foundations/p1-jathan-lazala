package com.revature.routing.service;

import com.revature.routing.entity.CallCategory;
import com.revature.routing.entity.InboundCall;
import com.revature.routing.entity.InboundCallStatus;
import com.revature.routing.model.AgentDto;
import com.revature.routing.model.AgentStatusUpdateRequest;
import com.revature.routing.model.CallEvent;
import com.revature.routing.repository.InboundCallRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.List;

/** Queue event - available agent - save */
@Service
public class RoutingService {

    private static final Logger log = LoggerFactory.getLogger(RoutingService.class);

    private final InboundCallRepository inboundCallRepository;
    private final RestTemplate restTemplate;

    @Value("${agent-service.base-url}")
    private String agentServiceBaseUrl;

    public RoutingService(InboundCallRepository inboundCallRepository, RestTemplate restTemplate) {
        this.inboundCallRepository = inboundCallRepository;
        this.restTemplate = restTemplate;
    }

    /** Handle one call event end-to-end. */
    @Transactional
    public void routeCall(CallEvent event) {
        CallCategory category = parseCategory(event.getCallCategory());

        String url = agentServiceBaseUrl + "/agents/available?category=" + category;
        AgentDto[] agents = restTemplate.getForObject(url, AgentDto[].class);

        if (agents == null || agents.length == 0) {
            log.warn("No available agent found for category {}", category);
            return;
        }

        AgentDto assignedAgent = agents[0];

        InboundCall call = new InboundCall();
        call.setCallId(event.getCallId());
        call.setCallerNumber(event.getCallerNumber());
        call.setCallCategory(category);
        call.setAssignedAgentId(assignedAgent.getId());
        call.setStatus(InboundCallStatus.ASSIGNED);
        LocalDateTime receivedAt = parseEventTimestamp(event.getTimestamp());
        if (receivedAt != null) {
            call.setReceivedAt(receivedAt);
        }

        inboundCallRepository.save(call);

        String updateUrl = agentServiceBaseUrl + "/agents/" + assignedAgent.getId() + "/status";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AgentStatusUpdateRequest> body = new HttpEntity<>(
                new AgentStatusUpdateRequest("ON_CALL"),
                headers
        );
        restTemplate.put(updateUrl, body);
    }

    public List<InboundCall> getAllCalls() {
        return inboundCallRepository.findAll();
    }

    public List<InboundCall> getCallsByAgentId(Long agentId) {
        return inboundCallRepository.findByAssignedAgentId(agentId);
    }

    private CallCategory parseCategory(String rawCategory) {
        if (rawCategory == null) {
            return CallCategory.GENERAL;
        }

        try {
            return CallCategory.valueOf(rawCategory.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return CallCategory.GENERAL;
        }
    }

    private LocalDateTime parseEventTimestamp(String raw) {
        if (raw == null || raw.isBlank()) {
            return null;
        }
        String trimmed = raw.trim();
        try {
            return LocalDateTime.ofInstant(Instant.parse(trimmed), ZoneOffset.UTC);
        } catch (DateTimeParseException ignored) {
            // fall through
        }
        try {
            return LocalDateTime.parse(trimmed);
        } catch (DateTimeParseException ex) {
            log.warn("Could not parse call event timestamp '{}', using server time instead", trimmed);
            return null;
        }
    }
}
