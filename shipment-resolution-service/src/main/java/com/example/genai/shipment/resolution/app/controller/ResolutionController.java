package com.example.genai.shipment.resolution.app.controller;

import com.example.genai.shipment.resolution.app.model.ResolutionResult;
import com.example.genai.shipment.resolution.app.model.ShipmentEvent;
import com.example.genai.shipment.resolution.app.service.ResolutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resolve")
@Slf4j
public class ResolutionController {

    private final ResolutionService resolutionService;

    public ResolutionController(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @PostMapping
    public ResolutionResult resolveShipment(@RequestBody ShipmentEvent event) {
        log.info("Event received for resolution : {}", event);
        ResolutionResult result = null;
        try {
            result = resolutionService.processEvent(event);
            log.info("Event Resolved!!!");
        } catch (Exception e) {
            log.error("Error while resolving the event!!!");
            log.error("Error : {}", e.getMessage());
            result = new ResolutionResult(event.shipmentId(), "Error could not be resolved!!!", "Please try again!!!");
        }
        return result;
    }
}
