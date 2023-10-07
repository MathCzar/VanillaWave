package Main;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;

public class FaceMesh {

    public void create() {

        faceMesh.create();

    }

    public Mesh faceMesh = new Mesh(new Vertex[] {

            //Front face
            new Vertex(new Vector3f(-0.5f,  0.5f,  0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f,  0.0f)),
            new Vertex(new Vector3f( 0.5f, -0.5f,  0.0f)),
            new Vertex(new Vector3f( 0.5f,  0.5f,  0.0f)),




    }, new Vertex[] {

            //Front face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f)),

    },
    new float[] {

            0,

            0,

            1

    },
    new int[] {

            //Front face
            0, 1, 3,
            3, 1, 2

    });

}
