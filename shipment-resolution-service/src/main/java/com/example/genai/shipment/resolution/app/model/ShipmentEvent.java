package com.example.genai.shipment.resolution.app.model;

public record ShipmentEvent(
        String shipmentId,
        String eventType,
        String description
) {}