package com.revature.routing.controller;

import com.revature.routing.entity.InboundCall;
import com.revature.routing.service.RoutingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calls")
public class CallController {

    private final RoutingService routingService;

    public CallController(RoutingService routingService) {
        this.routingService = routingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InboundCall> getCalls(
            @RequestParam(name = "agent-id", required = false) Long agentId
    ) {
        if (agentId != null) {
            return routingService.getCallsByAgentId(agentId);
        }
        return routingService.getAllCalls();
    }
}