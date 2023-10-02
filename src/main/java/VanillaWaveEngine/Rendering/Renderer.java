package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Camera;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Entity;
import VanillaWaveEngine.Window;
import org.lwjgl.opengl.*;

import java.util.*;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;

public class Renderer {

    private final Shader shader;
    private final Window window;

    public Renderer(Window window, Shader shader) {

        this.shader = shader;
        this.window = window;

    }

    public void renderMesh(Camera camera, Scene scene) {

        Collection<Model> models = scene.getModelMap().values();
        TextureCache textureCache = scene.getTextureCache();

        GL11.glEnable(GL_DEPTH_TEST);

        for (Model model : models) {

            List<Entity> entities = model.getEntitiesList();

            for (Material material : model.getMaterialList()) {

                for (Entity entity : entities) {

                    GL30.glBindVertexArray(model.getMesh().getVAO());
                    GL30.glEnableVertexAttribArray(0);
                    GL30.glEnableVertexAttribArray(1);
                    GL30.glEnableVertexAttribArray(2);
                    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getMesh().getIBO());
                    Texture texture = textureCache.getTexture(material.getTexturePath());
                    GL13.glActiveTexture(GL13.GL_TEXTURE0);
                    texture.bind(entity);
                    shader.bind();
                    shader.setUniform("model", Matrix4f.transform(entity.getPosition(), entity.getRotation(), entity.getScale()));
                    shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
                    shader.setUniform("projection", window.getProjectionMatrix());
                    GL11.glDrawElements(GL11.GL_TRIANGLES, model.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
                    shader.unbind();
                    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                    GL30.glDisableVertexAttribArray(0);
                    GL30.glDisableVertexAttribArray(1);
                    GL30.glDisableVertexAttribArray(2);
                    GL30.glBindVertexArray(0);

                }

            }
        }

        GL11.glDisable(GL11.GL_DEPTH_TEST);

    }

    public void renderText(Scene scene, Model model) {

        GL11.glEnable(GL11.GL_BLEND);
        GL14.glBlendEquation(GL14.GL_FUNC_ADD);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_CULL_FACE);

        FontCache fontCache = scene.getFontCache();

        List<TextItem> texts = model.getTextList();

        for (Material material : model.getMaterialList()) {

            for (TextItem text : texts) {

                GL30.glBindVertexArray(text.getMesh().getVAO());
                GL30.glEnableVertexAttribArray(0);
                GL30.glEnableVertexAttribArray(1);
                GL30.glEnableVertexAttribArray(2);
                GL30.glEnableVertexAttribArray(3);
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, text.getMesh().getIBO());
                Texture font = fontCache.getFont(material.getTexturePath());
                GL13.glActiveTexture(GL13.GL_TEXTURE0);
                font.bindText(text);
                shader.bind();
                shader.setUniform("model", Matrix4f.transform(text.getPosition(), text.getRotation(), text.getScale()));
                shader.setUniform("projection", window.getProjectionMatrix());
                GL11.glDrawElements(GL11.GL_TRIANGLES, text.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
                shader.unbind();
                GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
                GL30.glDisableVertexAttribArray(0);
                GL30.glDisableVertexAttribArray(1);
                GL30.glDisableVertexAttribArray(2);
                GL30.glDisableVertexAttribArray(3);
                GL30.glBindVertexArray(0);

            }

        }

        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_BLEND);

    }

}