package com.example.genai.shipment.resolution.app.config;

import com.example.genai.shipment.resolution.app.tools.NotificationTools;
import com.example.genai.shipment.resolution.app.tools.ShipmentTools;
import com.example.genai.shipment.resolution.app.agent.PlannerAgent;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    private final ChatModel chatModel;
    private final ShipmentTools shipmentTools;
    private final NotificationTools notificationTools;

    public AgentConfig(ChatModel chatModel, ShipmentTools shipmentTools, NotificationTools notificationTools) {
        this.chatModel = chatModel;
        this.shipmentTools = shipmentTools;
        this.notificationTools = notificationTools;
    }

    @Bean
    public PlannerAgent plannerAgent() {
        return AiServices.builder(PlannerAgent.class)
                .chatModel(chatModel)
                .tools(shipmentTools, notificationTools)
                .build();
    }
}
