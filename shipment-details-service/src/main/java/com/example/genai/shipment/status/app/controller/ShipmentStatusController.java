package com.example.genai.shipment.status.app.controller;

import com.example.genai.shipment.status.app.model.ShipmentDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class ShipmentStatusController {

    // Static predefined list of 10 shipments
    private static final List<ShipmentDetails> SHIPMENT_DATA = Arrays.asList(
            new ShipmentDetails("SHIP001", "Electronics", "2025-12-01", "2025-07-27", "20.50", "DELAYED" , true, "Rotterdam", "", true),
            new ShipmentDetails("SHIP002", "Books", "2025-12-02", "2025-07-28", "5.00", "DELAYED" , false, "Amsterdam", "", true),
            new ShipmentDetails("SHIP003", "Furniture", "2025-11-03", null, "100.00", "ON TIME" , true, "Stuttgart", "", true),
            new ShipmentDetails("SHIP004", "Clothes", "2025-11-04", null, "15.00", "ON TIME" , false, "Mumbai", "", true),
            new ShipmentDetails("SHIP005", "Shoes", "2025-12-05", null, "12.00", "ON TIME" , false, "Chennai", "", true),
            new ShipmentDetails("SHIP006", "Toys", "2025-10-26", null, "8.00", "ON TIME" , false, "Shanghai", "", true),
            new ShipmentDetails("SHIP007", "Medicines", "2025-12-07", null, "10.00", "ON TIME" , true, "Rotterdam", "", true),
            new ShipmentDetails("SHIP008", "Kitchenware", "2025-11-08", null, "18.00", "DELAYED" , false, "Shanghai", "", true),
            new ShipmentDetails("SHIP009", "Groceries", "2025-12-09", null, "7.50", "ON TIME" , false, "London", "", true),
            new ShipmentDetails("SHIP010", "Laptop", "2025-11-10", null, "30.00", "ON TIME" , true, "Rotterdam", "", true)
    );

    @PostMapping("/api/shipment/status")
    public ResponseEntity<ShipmentDetails> handleRequest(@RequestParam String shipmentId) {
        log.info("Request Received for shipmentId: {}", shipmentId);
        ShipmentDetails shipmentDetails;

        try {
            shipmentDetails = createApiResponse(shipmentId);
            return ResponseEntity.ok(shipmentDetails);
        } catch (Exception e) {
            log.error("Error occurred while processing shipmentId {}: {}", shipmentId, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ShipmentDetails());
        } finally {
            log.info("Request processing complete for shipmentId: {}", shipmentId);
        }
    }

    private ShipmentDetails createApiResponse(String shipmentId) {
        Optional<ShipmentDetails> match = SHIPMENT_DATA.stream()
                .filter(s -> s.getShipmentId().equalsIgnoreCase(shipmentId))
                .findFirst();

        if (match.isPresent()) {
            return match.get();
        } else {
            throw new IllegalArgumentException("Shipment ID not found in predefined list.");
        }
    }
}
