package com.example.genai.shipment.resolution.app.tools;

import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Component
@Slf4j
public class NotificationTools {

    private final SnsClient snsClient;
    private final String topicArn;

    public NotificationTools(@Value("${app.aws.region}") String region,
                             @Value("${app.aws.sns.topic-arn}") String topicArn) {
        this.topicArn = topicArn;
        this.snsClient = SnsClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    /**
     * Tool that the LLM (Planner Agent) can call to send notifications.
     */
    @Tool("Send a notification to the SNS topic with shipment status or resolution update.")
    public String sendNotification(String shipmentId, String message) {
        log.info("Sending Notification for shipment : {}", shipmentId);
        try {
            String fullMessage = String.format("Shipment %s: %s", shipmentId, message);
            log.debug("Notification message : {}", fullMessage);
            PublishRequest request = PublishRequest.builder()
                    .topicArn(topicArn)
                    .message(fullMessage)
                    .subject("Shipment Update: " + shipmentId)
                    .build();
            PublishResponse response = snsClient.publish(request);
            log.info("SNS Notification sent. MessageId: {}", response.messageId());
            return "Notification sent successfully (MessageId: " + response.messageId() + ")";
        } catch (Exception e) {
            log.error("Failed to send SNS notification for shipment {}: {}", shipmentId, e.getMessage());
            return "Failed to send SNS notification: " + e.getMessage();
        }
    }
}
