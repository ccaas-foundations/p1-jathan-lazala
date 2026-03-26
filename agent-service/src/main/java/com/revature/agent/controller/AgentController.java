package com.revature.agent.controller;

import com.revature.agent.dto.AgentStatusUpdateRequest;
import com.revature.agent.dto.InboundCallResponse;
import com.revature.agent.entity.Agent;
import com.revature.agent.entity.AgentStatus;
import com.revature.agent.entity.CallCategory;
import com.revature.agent.service.AgentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> getAgents(
            @RequestParam(required = false) AgentStatus status,
            @RequestParam(required = false) CallCategory category) {
        if (status == AgentStatus.AVAILABLE && category != null) {
            return agentService.getAvailableAgentsByCategory(category);
        }
        if (status != null || category != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "For available agents use GET /agents/available?category=... or GET /agents?status=AVAILABLE&category=...");
        }
        return agentService.getAllAgents();
    }

    @GetMapping("/available")
    public List<Agent> getAvailableByCategory(@RequestParam CallCategory category) {
        return agentService.getAvailableAgentsByCategory(category);
    }

    @GetMapping("/{id}/calls")
    public List<InboundCallResponse> getCallsForAgent(@PathVariable Long id) {
        return agentService.getCallsForAgent(id);
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id);
    }

    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.createAgent(agent);
    }

    @PutMapping("/{id}/status")
    public Agent updateStatus(@PathVariable Long id, @RequestBody AgentStatusUpdateRequest body) {
        if (body == null || body.getStatus() == null || body.getStatus().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "status is required");
        }
        AgentStatus newStatus;
        try {
            newStatus = AgentStatus.valueOf(body.getStatus().trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status value");
        }
        return agentService.updateStatus(id, newStatus);
    }
}
