package VanillaWaveEngine;

import VanillaWaveEngine.Input.KeyboardListener;
import VanillaWaveEngine.Input.MouseListener;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Matrix4f;
import org.lwjgl.glfw.GLFW;

public class Camera {

    private final Matrix4f viewMatrix = new Matrix4f();

    private Vector3f position, rotation;
    private float moveSpeed = 0.05f, mouseSensitivity = 0.15f;

    private float newMouseX, newMouseY;
    private float oldMouseX, oldMouseY;

    public Camera(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
    }

    public void update() {

        newMouseX = MouseListener.getX();
        newMouseY = MouseListener.getY();

        float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
        float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_A)) {

            position = Vector3f.add(position, new Vector3f(-z, 0, x));

        }

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_D)) {

            position = Vector3f.add(position, new Vector3f(z, 0, -x));

        }

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_W)) {

            position = Vector3f.add(position, new Vector3f(-x, 0, -z));

        }

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_S)) {

            position = Vector3f.add(position, new Vector3f(x, 0, z));

        }

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {

            position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));

        }

        if(KeyboardListener.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {

            position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));

        }

        float dx = (newMouseX - oldMouseX);
        float dy = (newMouseY - oldMouseY);

        rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0));

        if (rotation.getX() > 90.0f) {

            rotation.setX(90.0f);

        }
        else if (rotation.getX() < -90.0f) {

            rotation.setX(-90.0f);

        }

        oldMouseX = newMouseX;
        oldMouseY = newMouseY;

    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public Matrix4f getViewMatrix() {

        return viewMatrix;

    }

}
