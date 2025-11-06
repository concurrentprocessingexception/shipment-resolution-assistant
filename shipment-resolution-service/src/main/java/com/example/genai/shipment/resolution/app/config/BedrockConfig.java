package com.example.genai.shipment.resolution.app.config;


import dev.langchain4j.model.bedrock.BedrockChatModel;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;

@Configuration
public class BedrockConfig {

    @Value("${bedrock.model}")
    private String modelId;

    @Value("${bedrock.region}")
    private String region;

    @Bean
    public ChatModel bedrockChatModel() {
        return BedrockChatModel.builder()
                .modelId(modelId)
                .region(Region.of(region))
                .build();
    }
}
