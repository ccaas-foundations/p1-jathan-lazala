package com.revature.routing.listener;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.revature.routing.model.CallEvent;
import com.revature.routing.service.RoutingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class CallEventListener {

    private static final Logger log = LoggerFactory.getLogger(CallEventListener.class);

    private final XmlMapper xmlMapper;
    private final RoutingService routingService;

    public CallEventListener(XmlMapper xmlMapper, RoutingService routingService) {
        this.xmlMapper = xmlMapper;
        this.routingService = routingService;
    }

    @JmsListener(destination = "${app.queue-name}")
    public void receiveMessage(String xmlMessage) {
        try {
            CallEvent callEvent = xmlMapper.readValue(xmlMessage, CallEvent.class);
            routingService.routeCall(callEvent);
        } catch (Exception e) {
            log.error("Failed to process message: {}", xmlMessage, e);
        }
    }
}