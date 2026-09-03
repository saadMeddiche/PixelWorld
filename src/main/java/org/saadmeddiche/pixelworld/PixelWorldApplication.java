package org.saadmeddiche.pixelworld;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.swing.*;

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

        var mainWorld = PixelWorld.create(500,500, applicationName + " (Main)");

        mainWorld.frame.setResizable(false);
        mainWorld.frame.setLocationRelativeTo(null);

        return mainWorld;

    }

    @Bean
    public PixelWorld secondaryWorld() {

        var secondaryWorld = PixelWorld.create(500,500, applicationName + " (Secondary)");

        secondaryWorld.frame.setResizable(true);
        secondaryWorld.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        return secondaryWorld;

    }

}