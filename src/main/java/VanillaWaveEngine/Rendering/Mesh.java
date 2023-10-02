package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Math.Vertex;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {

    //private String texturePath;
    //private Texture texture;

    // Vertex is each part of the triangle
    public Vertex[] vertices;

    // Indices is the order in which to draw each vertex
    private int[] indices;

    // Texture Coordinates to determine what to show
    private Vertex[] textureCoords;

    // Normals determine the orientation of a polygon's surface
    private float[] normals;

    // VAO - Vertex Array Object
    // PBO - Position Buffer Object
    // IBO - Indices Buffer Object
    // TBO - Texture Buffer Object
    // VBO - Vertex Buffer Object
    private int VAO, PBO, TBO, IBO, VBO;

    public Mesh(Vertex[] vertices, Vertex[] textureCoords, float[] normals, int[] indices) {

        this.vertices = vertices;
        this.textureCoords = textureCoords;
        this.normals = normals;
        this.indices = indices;

    }

    public void create() {

        // Generates a VAO
        VAO = GL30.glGenVertexArrays();

        // Sends VAO to an array
        GL30.glBindVertexArray(VAO);

        // Allocates necessary memory
        FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);

        // Formats the data
        float[] positionData = new float[vertices.length * 3];
        for (int i = 0; i < vertices.length; i++) {

            // Sorts all x, y, and z into one array for the gpu
            positionData[i * 3] = vertices[i].getPosition().getX();
            positionData[i * 3 + 1] = vertices[i].getPosition().getY();
            positionData[i * 3 + 2] = vertices[i].getPosition().getZ();

        }
        positionBuffer.put(positionData).flip();

        // Vertex normals VBO
        //VBO = glGenBuffers();
        //vboIdList.add(VBO);
        //vecNormalsBuffer = MemoryUtil.memAllocFloat(normals.length);
        //vecNormalsBuffer.put(normals).flip();
        //glBindBuffer(GL_ARRAY_BUFFER, VBO);
        //glBufferData(GL_ARRAY_BUFFER, vecNormalsBuffer, GL_STATIC_DRAW);
        //glVertexAttribPointer(2, 3, GL_FLOAT, false, 0, 0);

        // Generates a PBO
        PBO = GL15.glGenBuffers();

        // Sends data to an array
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, PBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); // Gets the data for implementing a shader
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        // Formats the TBO
        FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(textureCoords.length * 2);
        float[] textureData = new float[textureCoords.length * 2];
        for (int i = 0; i < textureCoords.length; i++) {
            textureData[i * 2] = textureCoords[i].getTextureCoords().getX();
            textureData[i * 2 + 1] = textureCoords[i].getTextureCoords().getY();
        }
        textureBuffer.put(textureData).flip();

        // Generates a TBO
        TBO = GL15.glGenBuffers();

        // Sends data to an array
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, TBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, textureBuffer, GL_STATIC_DRAW);
        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0); // Gets the data for implementing a shader
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        // Allocates necessary memory
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        // Generates an IBO
        IBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, IBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);

    }

    public Vertex[] getVertices() {

        return vertices;

    }

    public int[] getIndices() {

        return indices;

    }

    public int getVAO() {

        return VAO;

    }

    public int getPBO() {

        return PBO;

    }

    public int getIBO() {

        return IBO;

    }

}
