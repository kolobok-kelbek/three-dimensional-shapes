package com.kelbek.three.dimensional.shares.input;

import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

@Service
public class InputRegistrarImpl implements InputRegistrator {
    @Autowired
    private long window;

    @Autowired
    List<GLFWKeyCallbackI> handlers;

    @Override
    public void registration() {
        handlers.forEach(handler -> glfwSetKeyCallback(window, handler));
    }
}
