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

            new Vertex(new Vector3f(-0.57735026f ,-0.57735026f ,0.57735026f )),new Vertex(new Vector3f(-0.6770032f ,-0.28867513f ,0.6770032f )),
            new Vertex(new Vector3f(-0.28867513f ,-0.6770032f ,0.6770032f )),new Vertex(new Vector3f(-0.3385016f ,-0.3385016f ,0.8779711f )),
            new Vertex(new Vector3f(0.0f ,-0.70710677f ,0.70710677f )),new Vertex(new Vector3f(0.0f ,-0.35355338f ,0.9354144f )),
            new Vertex(new Vector3f(0.28867513f ,-0.6770032f ,0.6770032f )),new Vertex(new Vector3f(0.3385016f ,-0.3385016f ,0.8779711f )),
            new Vertex(new Vector3f(0.57735026f ,-0.57735026f ,0.57735026f )), new Vertex(new Vector3f(0.6770032f ,-0.28867513f ,0.6770032f )),







            new Vertex(new Vector3f(-0.70710677f ,0.0f ,0.70710677f )),new Vertex(new Vector3f(-0.6770032f ,0.28867513f ,0.6770032f )),
            new Vertex(new Vector3f(-0.35355338f ,0.0f ,0.9354144f )),new Vertex(new Vector3f(-0.3385016f ,0.3385016f ,0.8779711f )),
            new Vertex(new Vector3f(0.0f ,0.0f ,1.0f )),new Vertex(new Vector3f(0.0f ,0.35355338f ,0.9354144f )),
            new Vertex(new Vector3f(0.35355338f ,0.0f ,0.9354144f )),new Vertex(new Vector3f(0.3385016f ,0.3385016f ,0.8779711f )),
            new Vertex(new Vector3f(0.70710677f ,0.0f ,0.70710677f )),new Vertex(new Vector3f(0.6770032f ,0.28867513f ,0.6770032f )),







            new Vertex(new Vector3f(-0.57735026f ,0.57735026f ,0.57735026f )),
            new Vertex(new Vector3f(-0.28867513f ,0.6770032f ,0.6770032f )),
            new Vertex(new Vector3f(0.0f ,0.70710677f ,0.70710677f )),
            new Vertex(new Vector3f(0.28867513f ,0.6770032f ,0.6770032f )),
            new Vertex(new Vector3f(0.57735026f ,0.57735026f ,0.57735026f )),




    }, new Vertex[] {

            //Back face
            new Vertex(new Vector2f(0.0f, 0.0f)),
            new Vertex(new Vector2f(0.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 1.0f)),
            new Vertex(new Vector2f(1.0f, 0.0f))

    },
    new float[] {

            0

    },
    new int[] {

            0,
            1,
            2,
            1,
            2,
            3,
            2,
            3,
            4,

            3,
            4,
            5,
            4,
            5,
            6,

            5,
            6,
            7,
            6,
            7,
            8,
            7,
            8,
            9,

            10,
            11,
            12,
            11,
            12,
            13,
            12,
            13,
            14,
            13,
            14,
            15,


    });

}
