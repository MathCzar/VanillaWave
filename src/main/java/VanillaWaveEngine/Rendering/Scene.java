package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Entity;

import java.util.*;

public class Scene {

    private Map<String, Model> modelMap;
    private TextureCache textureCache;
    private String DEFAULT_TEXTURE_PATH = "src/main/resources/textures/ERROR.png";

    public Scene() {
        modelMap = new HashMap<>();

        textureCache = new TextureCache(DEFAULT_TEXTURE_PATH, new Texture(DEFAULT_TEXTURE_PATH));
    }

    public void addEntity(Entity entity) {
        String modelID = entity.getModelID();
        Model model = modelMap.get(modelID);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelID + "]");
        }
        model.getEntitiesList().add(entity);
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

}
