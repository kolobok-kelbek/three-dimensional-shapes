package com.kelbek.three.dimensional.shares.project;

import com.kelbek.three.dimensional.shares.engine.*;
import org.joml.Vector2f;
import org.joml.Vector3f;
import com.kelbek.three.dimensional.shares.engine.graph.*;
import org.joml.Vector4f;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.lwjgl.glfw.GLFW.*;

@Service
public class Logic implements Logical {

    private static final float MOUSE_SENSITIVITY = 0.2f;

    private final Vector3f cameraInc;

    @Autowired
    private Renderer renderer;

    @Autowired
    private Camera camera;

    private Model[] models;

    private Vector3f ambientLight;

    private PointLight pointLight;

    private boolean polygonMode = false;

    private static final float CAMERA_POS_STEP = 0.05f;

    public Logic() {
        cameraInc = new Vector3f(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void init(Window window) throws Exception  {
        renderer.init();

        models = new Model[]{
                createModel(new Vector3f(0, 0, -5), new Vector4f(0.6f, 0.2f, 0.2f, 0f), Models.OCTAHEDRON),
                createModel(new Vector3f(-2, 2, -5), new Vector4f(0.3f, 0.2f, 0.2f, 0.0f)),
                createModel(new Vector3f(2, 1, -3), new Vector4f(0.2f, 0.2f, 0.6f, 0.0f), Models.TETRAHEDRON),
        };

        pointLight = createPointLight(new Vector3f(0, 2, -2f));
    }

    private PointLight createPointLight(Vector3f lightPosition) {
        ambientLight = new Vector3f(1f, 1f, 1f);
        Vector3f lightColour = new Vector3f(1, 1, 1);
        float lightIntensity = 1.0f;
        PointLight pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.1f, 0.5f, 1.0f);
        pointLight.setAttenuation(att);

        return pointLight;
    }

    private Model createModel(Vector3f position, Vector4f color) throws Exception {
        return createModel(position, color, Models.HEXAHEDRON);
    }

    private Model createModel(Vector3f position, Vector4f color, Models modelType) throws Exception {
        float reflectance = 0.1f;

        Mesh mesh = ObjLoader.loadMesh("/models/" + modelType.toString() + ".obj");
        Material material = new Material(color, reflectance);

        mesh.setMaterial(material);
        Model model = new Model(mesh);
        model.setScale(0.5f);
        model.setPosition(position.x, position.y, position.z);

        return model;
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);

        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }

        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            cameraInc.y = 1;
        }

        float lightPos = pointLight.getPosition().z;

        if (window.isKeyPressed(GLFW_KEY_N)) {
            this.pointLight.getPosition().z = lightPos + 0.1f;
        } else if (window.isKeyPressed(GLFW_KEY_M)) {
            this.pointLight.getPosition().z = lightPos - 0.1f;
        }

        if (window.isKeyPressed(GLFW_KEY_P)) {
            window.enablePolygonMode();
        } else if (window.isKeyPressed(GLFW_KEY_O)) {
            window.disablePolygonMode();
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP, cameraInc.y * CAMERA_POS_STEP, cameraInc.z * CAMERA_POS_STEP);

        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY, rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, models, ambientLight, pointLight);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (Model model : models) {
            model.getMesh().cleanUp();
        }
    }

}
