package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Entity;
import VanillaWaveEngine.Window;
import org.lwjgl.system.MemoryStack;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.*;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private int textureId;
    private String texturePath;

    private int width, height;

    public Texture(int width, int height, ByteBuffer buf) {
        this.texturePath = "";
        generateTexture(width, height, buf);
    }

    public Texture(String texturePath) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            this.texturePath = texturePath;
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            // Get the absolute path to find the resource file
            File file = new File(texturePath);
            String absolutePath = file.getAbsolutePath();
            //URL res = Texture.class.getResource(texturePath);
            //File file = Paths.get(res.toURI()).toFile();
            //String absolutePath = file.getAbsolutePath();

            ByteBuffer buf = stbi_load(absolutePath, w, h, channels, 4);
            if (buf == null) {
                throw new RuntimeException("Image file [" + texturePath + "] couldn't loaded: " + stbi_failure_reason());
            }

            this.width = w.get();
            this.height = h.get();

            generateTexture(width, height, buf);

            stbi_image_free(buf);
        //} catch (URISyntaxException e) {
        //    e.printStackTrace();
        }
    }

    public void bind(Entity entity) {
        glBindTexture(GL_TEXTURE_2D, entity.getTextureID());
    }

    public void bindText(TextItem text) {
        glBindTexture(GL_TEXTURE_2D, 4);
    }

    public void cleanup() {
        glDeleteTextures(textureId);
    }

    private void generateTexture(int width, int height, ByteBuffer buf) {
        textureId = glGenTextures();

        glBindTexture(GL_TEXTURE_2D, textureId);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, buf);
        glGenerateMipmap(GL_TEXTURE_2D);
    }

    public String getTexturePath() {
        return texturePath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}