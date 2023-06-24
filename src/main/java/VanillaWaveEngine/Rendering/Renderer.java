package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Camera;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Entity;
import VanillaWaveEngine.Window;
import org.lwjgl.opengl.*;

import java.util.*;

import static org.lwjgl.opengl.GL30.*;

public class Renderer {

    private Shader shader;
    private Window window;

    public Renderer(Window window, Shader shader) {

        this.shader = shader;
        this.window = window;

    }

    public void renderMesh(Camera camera, Scene scene) {

        //if ( window.isResized() ) {
        //    glViewport(0, 0, window.getWidth(), window.getHeight());
        //    window.setResized(false);
        //}

        Collection<Model> models = scene.getModelMap().values();
        TextureCache textureCache = scene.getTextureCache();

        for (Model model : models) {

            List<Entity> entities = model.getEntitiesList();

            for (Material material : model.getMaterialList()) {

                for (Mesh mesh : material.getMeshList()) {

                    for (Entity entity : entities) {

                        GL30.glBindVertexArray(mesh.getVAO());
                        GL30.glEnableVertexAttribArray(0);
                        GL30.glEnableVertexAttribArray(1);
                        GL30.glEnableVertexAttribArray(2);
                        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIBO());
                        Texture texture = textureCache.getTexture(material.getTexturePath());
                        GL13.glActiveTexture(GL13.GL_TEXTURE0);
                        texture.bind(entity);
                        shader.bind();
                        shader.setUniform("model", Matrix4f.transform(entity.getPosition(), entity.getRotation(), entity.getScale()));
                        shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
                        shader.setUniform("projection", window.getProjectionMatrix());
                        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
                        shader.unbind();
                        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                        GL30.glDisableVertexAttribArray(0);
                        GL30.glDisableVertexAttribArray(1);
                        GL30.glDisableVertexAttribArray(2);
                        GL30.glBindVertexArray(0);

                    }
                }
            }
        }

    }

}