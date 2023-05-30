package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Camera;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Entity;
import VanillaWaveEngine.Window;
import org.lwjgl.opengl.*;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private Texture texture;
    private Shader shader;
    private Window window;

    public Renderer(Window window, Shader shader) {

        this.shader = shader;
        this.window = window;

    }

    public void renderMesh(Entity entity, Camera camera) {

        //if ( window.isResized() ) {
        //    glViewport(0, 0, window.getWidth(), window.getHeight());
        //    window.setResized(false);
        //}
        GL30.glBindVertexArray(entity.getMesh().getVAO());
        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, entity.getMesh().getIBO());
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D, entity.getMesh().getTexture().getTextureId());
        shader.bind();
        shader.setUniform("model", Matrix4f.transform(entity.getPosition(), entity.getRotation(), entity.getScale()));
        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
        shader.setUniform("projection", window.getProjectionMatrix());
        shader.setUniform("txtSampler", 0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, entity.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
        shader.unbind();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);

    }

}