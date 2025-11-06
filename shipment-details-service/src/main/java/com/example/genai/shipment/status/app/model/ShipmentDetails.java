package com.example.genai.shipment.status.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDetails {

    private String shipmentId;
    private String shipmentDescription;
    private String expectedDeliveryDate;
    private String actualDeliveryDate;
    private String deliveryCharges;
    private String status;
    private boolean isFragile;
    private String currentLocation;
    private String delayReason;
    private boolean isNotificationNeeded;
}
