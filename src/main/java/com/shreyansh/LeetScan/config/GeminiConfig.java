package com.shreyansh.LeetScan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;




@Configuration
public class GeminiConfig {

    @Value("${GEMINI_API_KEY}")
    private String apiKey;

    @Bean
    ChatModel geminiModel() {
        System.out.println("===== USING MY CUSTOM GEMINI CONFIG =====");
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-3.5-flash") // Use your specific version here
                .logRequestsAndResponses(true)
                .build();
    }





}
