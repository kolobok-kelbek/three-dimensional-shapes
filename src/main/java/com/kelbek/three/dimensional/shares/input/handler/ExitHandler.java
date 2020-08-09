package com.kelbek.three.dimensional.shares.input.handler;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.springframework.stereotype.Component;

import static org.lwjgl.glfw.GLFW.*;

@Component
public class ExitHandler implements GLFWKeyCallbackI {
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE ) {
            glfwSetWindowShouldClose(window, true);
        }
    }
}
