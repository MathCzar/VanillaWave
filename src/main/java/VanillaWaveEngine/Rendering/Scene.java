package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Object;
import VanillaWaveEngine.Rendering.AssetPool.FontCache;
import VanillaWaveEngine.Rendering.AssetPool.SkyCache;
import VanillaWaveEngine.Rendering.AssetPool.TextureCache;

import java.util.*;

public class Scene {

    private Map<String, Model> modelMap;
    private TextureCache textureCache;
    private FontCache fontCache;
    private SkyCache skyCache;
    private String DEFAULT_TEXTURE_PATH = "src/main/resources/textures/ERROR.png";
    private Texture DEFAULT_TEXTURE = new Texture(DEFAULT_TEXTURE_PATH);

    public Scene() {

        modelMap = new HashMap<>();

        textureCache = new TextureCache(DEFAULT_TEXTURE_PATH, DEFAULT_TEXTURE);

        fontCache = new FontCache(DEFAULT_TEXTURE_PATH, DEFAULT_TEXTURE);

        skyCache = new SkyCache(DEFAULT_TEXTURE_PATH, DEFAULT_TEXTURE);

    }

    public void addObject(Object object) {
        String modelID = object.getModelID();
        Model model = modelMap.get(modelID);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelID + "]");
        }
        model.getObjectList().add(object);
    }

    public void addModel(Model model) {
        modelMap.put(model.getId(), model);
    }

    public Map<String, Model> getModelMap() {
        return modelMap;
    }

    public TextureCache getTextureCache() {
        return textureCache;
    }
    public FontCache getFontCache() {
        return fontCache;
    }
    public SkyCache getSkyCache() {
        return skyCache;
    }

}
