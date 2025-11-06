package com.example.genai.shipment.resolution.app.tools;

import com.example.genai.shipment.resolution.app.model.ShipmentDetails;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ShipmentTools {

    private final WebClient webClient;

    @Value("${app.api.shipment-status-service.url}")
    private String statusServiceUrl;

    public ShipmentTools(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    /**
     * Tool method exposed to LLM for function calling.
     */
    @Tool("Fetch the latest shipment status details given a shipment ID.")
    public ShipmentDetails getShipmentStatus(String shipmentId) {
        log.info("Fetching Shipment status for shipment with id : {}", shipmentId);
        try {
            String url = statusServiceUrl + "?shipmentId=" + shipmentId;
            log.info("Shipment Status API URL : {}", url);
            return webClient.post()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(ShipmentDetails.class)
                    .doOnSuccess(response -> {
                        log.info("Response form Shipment Status API : {}", response);
                    })
                    .onErrorResume(ex -> {
                        System.err.println("Error fetching shipment: " + ex.getMessage());
                        return Mono.empty();
                    })
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch shipment data for " + shipmentId, e);
        }
    }
}
