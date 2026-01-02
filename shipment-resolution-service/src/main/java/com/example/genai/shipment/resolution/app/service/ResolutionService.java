package com.example.genai.shipment.resolution.app.service;

import com.example.genai.shipment.resolution.app.agent.PlannerAgent;
import com.example.genai.shipment.resolution.app.model.ResolutionResult;
import com.example.genai.shipment.resolution.app.model.ShipmentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResolutionService {

    private final PlannerAgent plannerAgent;

    public ResolutionService(PlannerAgent plannerAgent) {
        this.plannerAgent = plannerAgent;
    }

    public ResolutionResult processEvent(ShipmentEvent event) {
        log.info("Processing Event : {}", event);
        long start = System.currentTimeMillis();
        String summary = plannerAgent.planResolution(event.shipmentId());
        long duration = System.currentTimeMillis() - start;
        log.debug("Event : {}, resolved!!! Summary : {}, Duration: {}", event, summary, duration);
        return new ResolutionResult(
                event.shipmentId(),
                summary,
                "ShipmentTools may have been invoked!!!");
    }
}


