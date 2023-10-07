package VanillaWaveEngine.Rendering.AssetPool;

import VanillaWaveEngine.Rendering.Texture;

import java.util.HashMap;
import java.util.Map;

public class SkyCache {

    public String DEFAULT_FONT_PATH;

    private Map<String, Texture> skyMap;

    public SkyCache(String DEFAULT_SKY_PATH, Texture DEFAULT_FONT) {
        skyMap = new HashMap<>();
        skyMap.put(DEFAULT_SKY_PATH, DEFAULT_FONT);
    }

    public Texture createSkybox(String skyPath) {
        return skyMap.computeIfAbsent(skyPath, Texture::new);
    }

    public Texture getSkybox(String skyPath) {
        Texture sky = null;
        if (skyPath != null) {
            sky = skyMap.get(skyPath);
        }
        if (sky == null) {
            sky = skyMap.get(DEFAULT_FONT_PATH);
        }
        return sky;
    }
}
