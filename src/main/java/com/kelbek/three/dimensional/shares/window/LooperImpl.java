package com.kelbek.three.dimensional.shares.window;

import org.lwjgl.opengl.GL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

@Service
public class LooperImpl implements Looper {
    @Autowired
    private long window;

    @Override
    public void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            // Set the clear color
            glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

            float[][] vertices = new float[][]{
                    {0.0f, 0.5f, 0.0f},
                    {-0.5f, -0.5f, 0.0f},
                    {0.5f, -0.5f, 0.0f}
            };

            glBegin(GL_TRIANGLES);
            glColor3f(1f, 1f, 1f);
            glVertex3fv(vertices[0]);
            glVertex3fv(vertices[1]);
            glVertex3fv(vertices[2]);
            glEnd();

            glfwSwapBuffers(window); // swap the color buffers
            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
}
