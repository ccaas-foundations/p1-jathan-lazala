package com.revature.agent.service;

import com.revature.agent.dto.InboundCallResponse;
import com.revature.agent.entity.Agent;
import com.revature.agent.entity.AgentStatus;
import com.revature.agent.entity.CallCategory;
import com.revature.agent.repository.AgentRepository;
import com.revature.agent.specification.AgentSpecifications;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final RestTemplate restTemplate;

    @Value("${call-routing-service.base-url}")
    private String callRoutingBaseUrl;

    public AgentService(AgentRepository agentRepository, RestTemplate restTemplate) {
        this.agentRepository = agentRepository;
        this.restTemplate = restTemplate;
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id) {
        return agentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agent not found"));
    }

    public List<Agent> getAvailableAgentsByCategory(CallCategory category) {
        return agentRepository.findAll(AgentSpecifications.availableForCategory(category));
    }

    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    public Agent updateStatus(Long id, AgentStatus status) {
        Agent agent = getAgentById(id);
        agent.setStatus(status);
        return agentRepository.save(agent);
    }

    public List<InboundCallResponse> getCallsForAgent(Long agentId) {
        getAgentById(agentId);
        String url = callRoutingBaseUrl + "/calls?agent-id=" + agentId;
        InboundCallResponse[] calls = restTemplate.getForObject(url, InboundCallResponse[].class);
        if (calls == null) {
            return List.of();
        }
        return Arrays.asList(calls);
    }
}
