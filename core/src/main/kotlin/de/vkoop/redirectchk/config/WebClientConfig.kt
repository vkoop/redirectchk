package de.vkoop.redirectchk.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Scope("prototype")
    @Bean
    fun createWebClient(): WebClient {
        return WebClient.builder()
                //TODO move to configuration
                .defaultHeader("Accept-Language", "de")
                .build()
    }
}