package Main;

import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;

public class CubeMesh {

    public void create() {

        meshCube.create();

    }

    public Mesh meshCube = new Mesh(new Vertex[] {

            //vertexes
            /* 0 */ new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f)),
            /* 1 */ new Vertex(new Vector3f(-0.5f, 0.5f, -0.5f)),
            /* 2 */ new Vertex(new Vector3f(0.5f, 0.5f, -0.5f)),
            /* 3 */ new Vertex(new Vector3f(0.5f, -0.5f, -0.5f)),
            /* 4 */ new Vertex(new Vector3f(-0.5f, -0.5f, 0.5f)),
            /* 5 */ new Vertex(new Vector3f(-0.5f, 0.5f, 0.5f)),
            /* 6 */ new Vertex(new Vector3f(0.5f, 0.5f, 0.5f)),
            /* 7 */ new Vertex(new Vector3f(0.5f, -0.5f, 0.5f)),


    }, new int[] {

            //front face
            0, 1, 2,
            0, 3, 2,

            //back face
            4, 5, 6,
            4, 7, 6,

            //right face
            7, 6, 2,
            7, 3, 2,

            //left face
            4, 5, 1,
            4, 0, 1,

            //top face
            1, 5, 6,
            1, 2, 6,

            // bottom face
            0, 4, 7,
            0, 3, 7

    });

}
