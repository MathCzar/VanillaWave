package VanillaWaveEngine.Rendering;

import java.util.*;

public class TextureCache {

    public static final String DEFAULT_TEXTURE = "resources/textures/grassBlock.png";

    private Map<String, Texture> textureMap;

    public TextureCache() {
        textureMap = new HashMap<>();
        textureMap.put(DEFAULT_TEXTURE, new Texture(DEFAULT_TEXTURE));
    }

    public Texture createTexture(String texturePath) {
        return textureMap.computeIfAbsent(texturePath, Texture::new);
    }

    public Texture getTexture(String texturePath) {
        Texture texture = null;
        if (texturePath != null) {
            texture = textureMap.get(texturePath);
        }
        if (texture == null) {
            texture = textureMap.get(DEFAULT_TEXTURE);
        }
        return texture;
    }
}
