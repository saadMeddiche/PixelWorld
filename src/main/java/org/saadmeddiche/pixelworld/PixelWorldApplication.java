package org.saadmeddiche.pixelworld;

import org.saadmeddiche.pixelworld.world.PixelWorld;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PixelWorldApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PixelWorldApplication.class)
                .headless(false)
                .run(args);
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public PixelWorld mainWorld() {
        return new PixelWorld(500,500, applicationName + " (Main)");
    }

    @Bean
    public PixelWorld secondaryWorld() {
        return new PixelWorld(500,500, applicationName + " (Secondary)");
    }

}