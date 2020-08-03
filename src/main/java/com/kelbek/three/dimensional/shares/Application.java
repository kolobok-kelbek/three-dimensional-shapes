package com.kelbek.three.dimensional.shares;

import com.kelbek.three.dimensional.shares.window.Looper;
import lombok.extern.slf4j.Slf4j;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Objects;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

@SpringBootApplication
@Slf4j
class Application implements CommandLineRunner {

    // The window handle
    @Autowired
    private long window;

    @Autowired
    private Looper looper;

    public void run(String... args) {
        if(!GLFW.glfwInit()) {
            throw new RuntimeException("Cannot initialize OPenGL");
        }

        log.info("Run with LWJGL " + Version.getVersion());

        looper.loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }
}