package VanillaWaveEngine;

import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.Mesh;

public class Object {

    private Vector3f position, rotation, scale;
    private Mesh mesh;

    public Object(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
    }

    public void update() {



    }

    public Vector3f getPosition() {

        return position;

    }

    public Vector3f getRotation() {

        return rotation;

    }

    public Vector3f getScale() {

        return scale;

    }

    public Mesh getMesh() {

        return mesh;

    }

}
