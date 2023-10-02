package VanillaWaveEngine.Rendering;

import VanillaWaveEngine.Entity;

import java.util.*;

public class Model {

    private final String id;
    private List<Entity> entitiesList;
    private List<TextItem> textList;
    private List<Material> materialList;
    private final Mesh mesh;

    public Model(String id, List<Material> materialList, Mesh mesh) {
        this.id = id;
        entitiesList = new ArrayList<>();
        this.materialList = materialList;
        this.mesh = mesh;
    }

    public Model(String id, List<Material> materialList, Mesh mesh, List<TextItem> textList) {
        this.id = id;
        this.textList = textList;
        this.materialList = materialList;
        this.mesh = mesh;
    }

    public List<Entity> getEntitiesList() {
        return entitiesList;
    }

    public List<TextItem> getTextList() {
        return textList;
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
