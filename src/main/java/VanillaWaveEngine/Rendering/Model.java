package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Object;

import java.util.*;

public class Model {

    private final String id;
    private List<Object> entitiesList;
    private List<TextItem> textList;
    private List<Object> skyList;
    private List<Material> materialList;
    private final Mesh mesh;

    public Model(String id, List<Material> materialList, Mesh mesh) {
        this.id = id;
        entitiesList = new ArrayList<>();
        this.materialList = materialList;
        this.mesh = mesh;
    }

    public Model(String id, List<Material> materialList, List<TextItem> textList) {
        this.id = id;
        this.textList = textList;
        this.materialList = materialList;
        this.mesh = null;
    }

    public Model(String id, List<Material> materialList, List<Object> skyList, Mesh mesh) {
        this.id = id;
        this.skyList = skyList;
        this.materialList = materialList;
        this.mesh = mesh;
    }

    public List<Object> getObjectList() {
        return entitiesList;
    }

    public List<TextItem> getTextList() {
        return textList;
    }

    public List<Object> getSkyboxList() {
        return skyList;
    }

    public String getId() {
        return id;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public Mesh getMesh(){

        return mesh;

    }

}
