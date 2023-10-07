package Main;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;

public class SkyMesh {

    public void create() {

        skyCube.create();

    }

    public Mesh skyCube = new Mesh(new Vertex[] {

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
            new Vertex(new Vector2f(0.0f, 0.34f)),
            new Vertex(new Vector2f(0.0f, 0.66f)),
            new Vertex(new Vector2f(0.25f, 0.66f)),
            new Vertex(new Vector2f(0.25f, 0.34f)),

            //Front face
            new Vertex(new Vector2f(0.25f, 0.34f)),
            new Vertex(new Vector2f(0.25f, 0.66f)),
            new Vertex(new Vector2f(0.5f, 0.66f)),
            new Vertex(new Vector2f(0.5f, 0.34f)),

            //Right face
            new Vertex(new Vector2f(0.5f, 0.34f)),
            new Vertex(new Vector2f(0.5f, 0.66f)),
            new Vertex(new Vector2f(0.75f, 0.66f)),
            new Vertex(new Vector2f(0.75f, 0.34f)),

            //Left face
            new Vertex(new Vector2f(0.75f, 0.34f)),
            new Vertex(new Vector2f(0.75f, 0.66f)),
            new Vertex(new Vector2f(1.0f, 0.66f)),
            new Vertex(new Vector2f(1.0f, 0.34f)),

            //Top face
            new Vertex(new Vector2f(0.25f, 0.0f)),
            new Vertex(new Vector2f(0.25f, 0.34f)),
            new Vertex(new Vector2f(0.5f, 0.34f)),
            new Vertex(new Vector2f(0.5f, 0.0f)),

            //Bottom face
            new Vertex(new Vector2f(0.25f, 0.66f)),
            new Vertex(new Vector2f(0.25f, 1.0f)),
            new Vertex(new Vector2f(0.5f, 1.0f)),
            new Vertex(new Vector2f(0.5f, 0.66f))

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
