package com.kelbek.three.dimensional.shares;

import com.kelbek.three.dimensional.shares.engine.Engine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
class Application implements CommandLineRunner {
    @Autowired
    private Engine engine;

    public void run(String... args) {
        try {
            engine.run();
        } catch (Exception excp) {
            excp.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }
}