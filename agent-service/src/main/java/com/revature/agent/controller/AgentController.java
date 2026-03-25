package com.revature.agent.controller;


import com.revature.agent.entity.Agent;
import com.revature.agent.entity.CallCategory;
import com.revature.agent.service.AgentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agents")
public class AgentController {
    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    public List<Agent> getAgents() {
    }

    @GetMapping
    public Agent getAgentById(Long id) {

    }

    @GetMapping
    public List<Agent> getAvailableAgentsByCategory(CallCategory category) {

    }
}
