package Main;

import VanillaWaveEngine.Camera;
import VanillaWaveEngine.Entity;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.*;

import java.util.ArrayList;
import java.util.List;

public class RenderHandler {

    public Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

    CubeMesh cube = new CubeMesh();
    FaceMesh face = new FaceMesh();

    TextItem FPSCounter;

    Texture woodTexture;
    Texture grassTexture;

    Texture font;

    //public Entity cube_render = new Entity(new Vector3f(2.5f, 2.5f, 2.5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render2 = new Entity(new Vector3f(5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render3 = new Entity(new Vector3f(0, 0, 5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity center = new Entity(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube, 2, "cube-model");
    public Entity orbit = new Entity(new Vector3f(10, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube, 2, "cube-model");
    public Entity orbit2 = new Entity(new Vector3f(0, 10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube, 2, "cube-model");
    public Entity orbit3 = new Entity(new Vector3f(0, 0, 10), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube, 3, "cube-model");
    public Entity orbit4 = new Entity(new Vector3f(0, 5, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube, 3, "cube-model");
    public Entity faceEntity = new Entity(new Vector3f(5, 5, 5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), face.faceMesh, 3, "face-model");

    private float temp, temp2, tempOrbitAngle = 0, tempOrbitX, tempOrbitZ, tempOrbitY, gameCycle = 0, tempOrbitX2, tempOrbitZ2, tempOrbitY2, tempOrbitAngle2 = 0;

    private int sphereResolution = 2;

    private Model cubeModel, faceModel;

    private Model fontModel;

    private final Scene scene;

    public RenderHandler(Scene scene) {

        this.scene = scene;

    }

    public void createMeshes() {

        cube.create();
        face.create();

    }

    private void createTextures() {

        woodTexture = scene.getTextureCache().createTexture("src/main/resources/textures/materials/wood.png");
        grassTexture = scene.getTextureCache().createTexture("src/main/resources/textures/materials/grassBlock.png");

    }

    private void createFontTextures() {

        font = scene.getFontCache().createFont("src/main/resources/textures/fonts/ExportedFont.png");

    }

    public void createMaterials() {

        createTextures();

        Material wood = new Material();
        Material grass = new Material();
        wood.setTexturePath(woodTexture.getTexturePath());
        grass.setTexturePath(grassTexture.getTexturePath());
        List<Material> materialList = new ArrayList<>();
        materialList.add(wood);
        materialList.add(grass);

        cubeModel = new Model("cube-model", materialList, cube.meshCube);
        faceModel = new Model("face-model", materialList, face.faceMesh);
        scene.addModel(cubeModel);
        scene.addModel(faceModel);

    }

    public void createFonts() {

        createFontTextures();

        Material fontMaterial = new Material();
        fontMaterial.setTexturePath(font.getTexturePath());
        List<Material> fontList = new ArrayList<>();
        fontList.add(fontMaterial);

        FPSCounter = new TextItem("", scene.getFontCache().getFont("src/main/resources/textures/fonts/ExportedFont.png"), 4, 8, 8, new Vector3f(-1.75f, 0.825f, -1.0f), new Vector3f(0, 0, 0), new Vector3f(0.5f, 0.5f, 0.5f));

        List<TextItem> textEntityList = new ArrayList<>();

        textEntityList.add(FPSCounter);

        fontModel = new Model("font", fontList, FPSCounter.getMesh(), textEntityList);

    }

    public void initializeEntities() {

        scene.addEntity(center);
        scene.addEntity(orbit);
        scene.addEntity(orbit2);
        scene.addEntity(orbit3);
        scene.addEntity(orbit4);
        scene.addEntity(faceEntity);

    }

    public void updateMatrix() {

        updateCube1();
        updateCube2();
        updateCube3();
        updateOrbit();
        spherePoints();

    }

    public void updateCube1() {

        temp += 0.01f;
        temp2 += 0.001f;

        //cube_render.updateXRotation((float) Math.sin(temp)*90);
        //cube_render.updateZPosition((float) Math.sin(temp));

    }

    public void updateCube2() {

        //cube_render2.updateXPosition(camera.getPosition().getX());

    }

    public void updateCube3() {

        //cube_render3.updateYPosition(camera.getPosition().getY());

    }

    public void updateOrbit() {

        if (gameCycle == 100) {

        tempOrbitAngle += 45f;

        if (tempOrbitAngle > 45) {

            tempOrbitAngle = -45f;

        }

        tempOrbitAngle2 -= 45f;

        if (tempOrbitAngle2 < -45f) {

            tempOrbitAngle2 = 45f;

        }

        //System.out.println("Angle:" + tempOrbitAngle);
        //System.out.println("Angle2:" + tempOrbitAngle2);

        tempOrbitX = (float) Math.cos(Math.toRadians(tempOrbitAngle))*2f;
        tempOrbitY = (float) Math.sin(Math.toRadians(tempOrbitAngle))*2f;
        tempOrbitZ = (float) Math.sin(Math.toRadians(tempOrbitAngle))*2f;

        tempOrbitX2 = (float) Math.cos(Math.toRadians(-tempOrbitAngle2))*2f;
        tempOrbitY2 = (float) Math.sin(Math.toRadians(tempOrbitAngle2))*2f;
        tempOrbitZ2 = (float) Math.sin(Math.toRadians(-tempOrbitAngle2))*2f;

        orbit.updateXPosition(tempOrbitX);
        //System.out.println("X:" + orbit.getPosition().getX());
        orbit.updateYPosition(tempOrbitY);
        //System.out.println("Y:" + orbit.getPosition().getY());
        orbit.updateZPosition(tempOrbitZ);

        orbit2.updateXPosition(tempOrbitX2);
        //System.out.println("X2:" + orbit2.getPosition().getX());
        orbit2.updateYPosition(tempOrbitY2);
        //System.out.println("Y2:" + orbit2.getPosition().getY());
        orbit2.updateZPosition(tempOrbitZ2);

        gameCycle = 0;

        }
        else if (gameCycle == 200) {

        for (int resolution = sphereResolution; resolution > 0; resolution--) {



        }

        }
        else {

        gameCycle++;

        }

    }

    public void spherePoints() {

//        orbit.updateXPosition(-0.5f);
//        orbit.updateYPosition(-0.5f);
//        orbit2.updateXPosition(0.5f);
//        orbit2.updateYPosition(0.5f);
//        orbit3.updateXPosition(0);
//        orbit3.updateYPosition(0);
//        orbit4.updateXPosition(0);
//        orbit4.updateYPosition(-0.5f);

    }

    public Model getTextModel() {

        return fontModel;

    }

}
