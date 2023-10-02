package VanillaWaveEngine;

import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.Mesh;

public class Entity {

    public Vector3f position, rotation, scale;
    private final int textureID, materialID;
    private final String modelID;
    private Mesh mesh;


    public Entity(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh, int materialID, String modelID) {

        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.mesh = mesh;
        this.textureID = materialID;
        this.materialID = materialID;
        this.modelID = modelID;

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

    public int getTextureID() {

        return textureID;

    }

    public int getMaterialID() {

        return materialID;

    }

    public String getModelID() {

        return modelID;

    }

}
