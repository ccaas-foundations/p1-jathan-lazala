package com.revature.routing.controller;

import com.revature.routing.entity.InboundCall;
import com.revature.routing.service.RoutingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


 //querying routed calls stored in this service's database..
 
@RestController
@RequestMapping("/calls")
public class CallController {

    private final RoutingService routingService;

    public CallController(RoutingService routingService) {
        this.routingService = routingService;
    }

    /** Optional agent-id; omit for all calls. */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InboundCall> getCalls(
            @RequestParam(name = "agent-id", required = false) Long agentId
    ) {
        if (agentId != null) {
            return routingService.getCallsByAgentId(agentId);
        }
        List<InboundCall> listOfInboundCalls = routingService.getAllCalls();
        System.out.print(listOfInboundCalls);
        return listOfInboundCalls;

    }
}