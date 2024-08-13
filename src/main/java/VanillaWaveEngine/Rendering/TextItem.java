package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;

public class TextItem {

    private static final float ZPOS = 0.0f;

    private static final int VERTICES_PER_QUAD = 4;

    private String text;

    private Mesh textMesh;

    private Vertex[] vertices = new Vertex[] {};
    private Vertex[] textCoords = new Vertex[] {};
    private float[] normals = new float[] {0, 0, 1};
    private int[] indices = new int[] {};

    private Vector3f position, rotation, scale;

    public TextItem(String text, Vector3f position, Vector3f rotation, Vector3f scale) {
        this.text = text;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        buildMesh();
    }

    private void buildMesh() {

        //create/clean templates to add elements to the arrays
        vertices = new Vertex[] {};
        textCoords = new Vertex[] {};
        indices = new int[] {};

        //System.out.println("Frames: " + text);

        //Sends string to array to be processed
        char[] stringToChar = text.toCharArray();

        for (int i = 0; i < stringToChar.length; i++) {
            //Vertices
            vertices = addVectorToArray(vertices, new Vertex (new Vector3f((float) i/3, 0.0f, ZPOS)));
            vertices = addVectorToArray(vertices, new Vertex (new Vector3f((float) i/3, (float) 1/3, ZPOS)));
            vertices = addVectorToArray(vertices, new Vertex (new Vector3f((float) (i + 1)/3, (float) 1/3, ZPOS)));
            vertices = addVectorToArray(vertices, new Vertex (new Vector3f((float) (i + 1)/3, 0.0f, ZPOS)));

            //Indices
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i));
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i) + 1);
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i) + 2);
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i) + 2);
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i) + 3);
            indices = addIntToArray(indices, (VERTICES_PER_QUAD * i));

            switch(stringToChar[i]) {
                case ' ':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.0f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.125f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.125f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.0f)));
                    break;

                case ':':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.5f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.5f)));
                    break;

                case '-':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.125f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.20f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f,  0.20f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f,  0.125f)));
                    break;

                case '.':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.125f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.125f)));
                    break;

                case 'X':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 1.0f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 1.0f)));
                    break;

                case 'Y':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 1.0f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 1.0f)));
                    break;

                case 'Z':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 1.0f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.875f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 1.0f)));
                    break;

                case '0':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.375f)));
                    break;

                case '1':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.375f)));
                    break;
                case '2':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.375f)));
                    break;
                case '3':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.375f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.5f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.5f, 0.375f)));
                    break;
                case '4':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.5f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.5f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.375f)));
                    break;
                case '5':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.625f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.375f)));
                    break;
                case '6':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.75f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.375f)));
                    break;
                case '7':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.875f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(1.0f, 0.25f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(1.0f, 0.375f)));
                    break;
                case '8':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.5f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.0f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.5f)));
                    break;
                case '9':
                    //Texture Coordinates
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.5f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.125f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.375f)));
                    textCoords = addVectorToArray(textCoords, new Vertex(new Vector2f(0.25f, 0.5f)));
                    break;
                default:
                    System.out.println("error");
            }
        }

        this.textMesh = new Mesh(vertices, textCoords, normals, indices);

        //this.textMesh = new Mesh(new Vertex[] {
//
        //        //Front face
        //        new Vertex(new Vector3f(0, 0.0f, ZPOS)),
        //        new Vertex(new Vector3f(0, 1.0f, ZPOS)),
        //        new Vertex(new Vector3f((1), 1.0f,  ZPOS)),
        //        new Vertex(new Vector3f((1), 0.0f,  ZPOS)),
//
//
        //}, new Vertex[] {
//
        //        //Front fac
        //        new Vertex(new Vector2f(0.625f, 0.125f)),
        //        new Vertex(new Vector2f(0.625f, 0.20f)),
        //        new Vertex(new Vector2f(0.75f,  0.20f)),
        //        new Vertex(new Vector2f(0.75f,  0.125f))
//
//
//
        //}, new float[] {
//
        //        0,
        //        0,
        //        1
//
        //}, new int[] {
//
        //        //Front face
        //        0, 1, 2,
        //        2, 3, 0,
//
//
        //});

        textMesh.create();

    }

    public Mesh getMesh() {

        return this.textMesh;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        buildMesh();
    }

    private Vertex[] addVectorToArray(Vertex[] newArray, Vertex elementToAdd) {

        Vertex[] destArray = new Vertex[newArray.length+1];

        for(int i = 0; i < newArray.length; i++) {
            destArray[i] = newArray[i];
        }

        destArray[destArray.length - 1] = elementToAdd;
        return destArray;

    }

    private int[] addIntToArray(int[] newIntArray, int elementToAdd) {

        int[] destArray = new int[newIntArray.length + 1];

        for(int i = 0; i < newIntArray.length; i++) {
            destArray[i] = newIntArray[i];
        }

        destArray[destArray.length - 1] = elementToAdd;
        return destArray;

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

}
