package com.example.genai.shipment.resolution.app.model;

public record ShipmentStatus(
        String shipmentId,
        String currentLocation,
        String expectedDeliveryDate,
        String status
) {}
