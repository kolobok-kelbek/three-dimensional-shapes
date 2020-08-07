package com.kelbek.three.dimensional.shares.window;

import org.springframework.stereotype.Service;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

@Service
public class RendererImpl implements Renderer {

    private final float[][] vertices = new float[][]{
            {0.0f, 0.5f, 0.0f},
            {-0.5f, -0.5f, 0.0f},
            {0.5f, -0.5f, 0.0f}
    };

    @Override
    public void render() {
        glPushMatrix();
        glRotatef(0f,10f, 50f, 0.3f);
        glBegin(GL_TRIANGLES);
        glColor3f(1f, 1f, 1f);
        glVertex3fv(vertices[0]);
        glVertex3fv(vertices[1]);
        glVertex3fv(vertices[2]);
        glEnd();
        glPopMatrix();
    }
}
