package VanillaWaveEngine.Math;

public class Vertex {

    private Vector3f position;
    private Vector2f textureCoords;

    public Vertex(Vector3f position) {

        this.position = position;

    }

    public Vertex(Vector2f textureCoords) {

        this.textureCoords = textureCoords;

    }

    public Vector3f getPosition() {

        return position;

    }

    public Vector2f getTextureCoords() {

        return textureCoords;

    }

}
