package Main;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;

public class CubeMesh {

    public void create() {

        mesh.create();

    }

    public Mesh mesh = new Mesh(new Vertex[] {

            //Back face
            new Vertex(new Vector3f(-0.5f,  0.5f, -0.5f)),

            new Vertex(new Vector3f(-0.5f,  0.0f, -0.5f)),
            new Vertex(new Vector3f(0.0f,  0.0f, -0.5f)),
            new Vertex(new Vector3f(0.0f,  0.5f, -0.5f)),

            new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)),
            new Vertex(new Vector3f(0.0f, -0.5f, -0.5f)),
            new Vertex(new Vector3f( 0.5f,  -0.5f, -0.5f)),
            new Vertex(new Vector3f(0.5f,  0.0f, -0.5f)),
            new Vertex(new Vector3f( 0.5f, 0.5f, -0.5f)),

    }, new Vertex[] {

            //Back face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),



    },
    new float[] {

            0

    },
    new int[] {

            //Back face
            0, 3, 1,
            1, 2, 3,

            2, 7, 5,
            5, 6, 7,

            //3, 4, 6,
            //6, 7, 4,
//
            //4, 5, 7,
            //7, 8, 5

    });

}
