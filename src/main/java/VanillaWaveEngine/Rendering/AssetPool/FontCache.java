package VanillaWaveEngine.Rendering.AssetPool;

import VanillaWaveEngine.Rendering.Texture;

import java.util.HashMap;
import java.util.Map;

public class FontCache {

    public String DEFAULT_FONT_PATH;

    private Map<String, Texture> fontMap;

    public FontCache(String DEFAULT_FONT_PATH, Texture DEFAULT_FONT) {
        fontMap = new HashMap<>();
        fontMap.put(DEFAULT_FONT_PATH, DEFAULT_FONT);
    }

    public Texture createFont(String fontPath) {
        return fontMap.computeIfAbsent(fontPath, Texture::new);
    }

    public Texture getFont(String fontPath) {
        Texture font = null;
        if (fontPath != null) {
            font = fontMap.get(fontPath);
        }
        if (font == null) {
            font = fontMap.get(DEFAULT_FONT_PATH);
        }
        return font;
    }
}
