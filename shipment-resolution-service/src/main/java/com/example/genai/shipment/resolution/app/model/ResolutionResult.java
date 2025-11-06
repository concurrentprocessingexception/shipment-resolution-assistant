package com.example.genai.shipment.resolution.app.model;

public record ResolutionResult(
        String shipmentId,
        String summary,
        String actionTaken
) {}