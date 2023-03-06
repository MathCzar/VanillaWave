package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Math.Vertex;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh {

    // Vertex is each part of the triangle
    public Vertex[] vertices;

    // Indices is the order in which to draw each vertex
    private int[] indices;

    // VAO - Vertex Array Object
    // PBO - Position Buffer Object
    // IBO - Indices Buffer Object
    private int VAO, PBO, IBO;

    public Mesh(Vertex[] vertices, int[] indices) {

        this.vertices = vertices;
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

        // Generates a PBO
        PBO = GL15.glGenBuffers();

        // Sends data to an array
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, PBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, positionBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0); // Gets the data for implementing a shader
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        // Allocates necessary memory
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();

        // Generates an IBO
        IBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, IBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

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
