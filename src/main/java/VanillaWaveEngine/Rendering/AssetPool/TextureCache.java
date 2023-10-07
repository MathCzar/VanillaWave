package VanillaWaveEngine.Rendering.AssetPool;

import VanillaWaveEngine.Rendering.Texture;

import java.util.*;

public class TextureCache {

    public String DEFAULT_TEXTURE_PATH;

    private Map<String, Texture> textureMap;

    public TextureCache(String DEFAULT_TEXTURE_PATH, Texture DEFAULT_TEXTURE) {
        textureMap = new HashMap<>();
        textureMap.put(DEFAULT_TEXTURE_PATH, DEFAULT_TEXTURE);
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
            texture = textureMap.get(DEFAULT_TEXTURE_PATH);
        }
        return texture;
    }
}
