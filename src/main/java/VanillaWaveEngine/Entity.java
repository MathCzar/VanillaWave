package VanillaWaveEngine;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.Mesh;

public class Entity {

    private float temp;

    public Vector3f position, rotation, scale;
    private Mesh mesh;

    public Entity(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {

        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;

    }

    public void updateXPosition(float xPosition) {

        position.setX(xPosition);

    }

    public void updateYPosition(float yPosition) {

        position.setY(yPosition);

    }

    public void updateZPosition(float zPosition) {

        position.setZ(zPosition);

    }

    public void updateXRotation(float xRotation) {

        rotation.setX(xRotation);

    }

    public void updateYRotation(float yRotation) {

        rotation.setY(yRotation);

    }

    public void updateZRotation(float zRotation) {

        rotation.setZ(zRotation);

    }

    public void updateXScale(float xScale) {

        scale.setX(xScale);

    }

    public void updateYScale(float yScale) {

        scale.setY(yScale);

    }

    public void updateZScale(float zScale) {

        scale.setZ(zScale);

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
