package Main;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;

public class CubeMesh {

    public void create() {

        meshCube.create();

    }

    public Mesh meshCube = new Mesh(new Vertex[] {

            //Back face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f)),

            //Front face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f)),

            //Right face
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f)),

            //Left face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f)),

            //Top face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.5f)),

            //Bottom face
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.5f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.5f)),


    }, new Vertex[] {

            //Back face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            //Front face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            //Right face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            //Left face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            //Top face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            //Bottom face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f))

    },
    new float[] {

            0

    },
    new int[] {

            //Back face
            0, 1, 3,
            3, 1, 2,

            //Front face
            4, 5, 7,
            7, 5, 6,

            //Right face
            8, 9, 11,
            11, 9, 10,

            //Left face
            12, 13, 15,
            15, 13, 14,

            //Top face
            16, 17, 19,
            19, 17, 18,

            //Bottom face
            20, 21, 23,
            23, 21, 22

    });

}
