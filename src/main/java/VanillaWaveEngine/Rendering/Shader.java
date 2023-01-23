package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.FileUtilities;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Math.Vector2f;
import VanillaWaveEngine.Math.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

public class Shader {

    // Stores the content of the file
    private String vertexFile, fragmentFile;

    private int vertexID, fragmentID, programID;

    public Shader(String vertexPath, String fragmentPath) {

        // Loads the shaders as a string
        vertexFile = FileUtilities.loadAsString(vertexPath);
        fragmentFile = FileUtilities.loadAsString(fragmentPath);

    }

    public void create() {

        programID = GL20.glCreateProgram();

        vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);

        GL20.glShaderSource(vertexID, vertexFile);
        GL20.glCompileShader(vertexID);

        // If the shader did not compile
        if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

            System.err.println("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));

            return;

        }

        // Links the shader to the program
        GL20.glAttachShader(programID, vertexID);

        fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);

        GL20.glShaderSource(fragmentID, fragmentFile);
        GL20.glCompileShader(fragmentID);

        // If the shader did not compile
        if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {

            System.err.println("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));

            return;

        }

        // Links the shader to the program
        GL20.glAttachShader(programID, fragmentID);

        // Checks for errors
        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {

            System.err.println("Program Linking: " + GL20.glGetProgramInfoLog(programID));

        }

        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {

            System.err.println("Program Validation: " + GL20.glGetProgramInfoLog(programID));

        }

        // Shaders no longer needed
        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);

    }

    // Looks for the index of the uniform
    public int getUniformLocation(String name) {

        return GL20.glGetUniformLocation(programID, name);

    }

    public void setUniform(String name, float value) {

        GL20.glUniform1f(getUniformLocation(name), value);

    }

    public void setUniform(String name, int value) {

        GL20.glUniform1i(getUniformLocation(name), value);

    }

    public void setUniform(String name, boolean value) {

        GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);

    }

    public void setUniform(String name, Vector2f value) {

        GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());

    }

    public void setUniform(String name, Vector3f value) {

        GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());

    }

    public void setUniform(String name, Matrix4f value) {

        FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
        matrix.put(value.getAll()).flip();
        GL20.glUniformMatrix4fv(getUniformLocation(name), true, matrix);

    }

    // Start drawing shader
    public void bind() {

        GL20.glUseProgram(programID);

    }

    // End drawing shader
    public void unbind() {

        GL20.glUseProgram(0);

    }

    // Destroys the shaders
    public void destroy() {

        GL20.glDetachShader(programID, vertexID);
        GL20.glDetachShader(programID, fragmentID);
        GL20.glDeleteShader(vertexID);
        GL20.glDeleteShader(fragmentID);
        GL20.glDeleteProgram(programID);

    }

}
