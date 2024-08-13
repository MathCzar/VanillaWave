package Main;

import GameTest.SolarSystem;
import VanillaWaveEngine.Camera;
import VanillaWaveEngine.Object;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RenderHandler {

    public Camera camera = new Camera(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f));

    CubeMesh cube = new CubeMesh();
    FaceMesh face = new FaceMesh();
    SkyMesh sky = new SkyMesh();
    SphereMesh sphere = new SphereMesh();
    SolarSystem system = new SolarSystem();
    TextItem FPSCounter;
    TextItem Xcoordinate, Ycoordinate, Zcoordinate;
    TextItem loadingScreen;

    Texture woodTexture;
    Texture grassTexture;

    Texture skyTexture;

    Texture font;

    //public Entity cube_render = new Entity(new Vector3f(2.5f, 2.5f, 2.5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render2 = new Entity(new Vector3f(5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render3 = new Entity(new Vector3f(0, 0, 5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Object center = new Object(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 10, "cube");
    //public Object orbit = new Object(new Vector3f(10, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 2, "cube");
    //public Object orbit2 = new Object(new Vector3f(0, 10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 2, "cube");
    //public Object orbit3 = new Object(new Vector3f(0, 0, 10), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 3, "cube");
    //public Object orbit4 = new Object(new Vector3f(0, 5, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 3, "cube");
    //public Object faceEntity = new Object(new Vector3f(5, 5, 5), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 2, "face");
    //public Object cube_render = new Object(new Vector3f(-2, -2, -2), new Vector3f(0, 0, 0),new Vector3f(1, 1, 1) , 3, "cube");
    //public Object cube_render2 = new Object(new Vector3f(5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 2, "cube");
    //public Object cube_render3 = new Object(new Vector3f(0, 0, 5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), 2, "cube");
    //public Object circle = new Object(new Vector3f(0, 2, 0), new Vector3f(0, 0, 0), new Vector3f(10, 10, 10), 2, "sphere");

    public Object skybox = new Object(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(2000, 2000, 2000), 5, "sky");

    private float temp, temp2, tempOrbitAngle = 0, tempOrbitX, tempOrbitZ, tempOrbitY, gameCycle = 0, tempOrbitX2, tempOrbitZ2, tempOrbitY2, tempOrbitAngle2 = 0, tempCubeAngle = 0;

    private int sphereResolution = 2;

    private Model cubeModel, faceModel, sphereModel;

    private Model skyModel;

    private Model fontModel;

    public final Scene scene;

    public RenderHandler(Scene scene) {

        this.scene = scene;

    }

    public void createMeshes() {

        cube.create();
        face.create();
        sky.create();
        sphere.constructMesh(2);

    }

    private void createTextures() {

        woodTexture = scene.getTextureCache().createTexture("src/main/resources/textures/materials/wood.png");
        grassTexture = scene.getTextureCache().createTexture("src/main/resources/textures/materials/grassBlock.png");

    }

    private void createFontTextures() {

        font = scene.getFontCache().createFont("src/main/resources/textures/fonts/ExportedFont.png");

    }

    private void createSkyboxTextures() {

        skyTexture = scene.getSkyCache().createSkybox("src/main/resources/textures/skyboxes/skybox.png");

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

        cubeModel = new Model("cube", materialList, cube.mesh);
        faceModel = new Model("face", materialList, face.mesh);
        sphereModel = new Model("sphere", materialList, sphere.getMesh());
        scene.addModel(cubeModel);
        scene.addModel(faceModel);
        scene.addModel(sphereModel);

    }

    public void createSkybox() {

        createSkyboxTextures();

        Material skyMaterial = new Material();

        skyMaterial.setTexturePath(skyTexture.getTexturePath());
        List<Material> skyMaterialList = new ArrayList<>();
        skyMaterialList.add(skyMaterial);

        List<Object> skyboxList = new ArrayList<>();

        skyboxList.add(skybox);

        skyModel = new Model("sky", skyMaterialList, skyboxList, sky.skyCube);

    }

    public void createFonts() {

        createFontTextures();

        Material fontMaterial = new Material();
        fontMaterial.setTexturePath(font.getTexturePath());
        List<Material> fontList = new ArrayList<>();
        fontList.add(fontMaterial);

        FPSCounter = new TextItem("", new Vector3f(-1.75f, 0.825f, -1.0f), new Vector3f(0, 0, 0), new Vector3f(0.5f, 0.5f, 0.5f));
        Xcoordinate = new TextItem("", new Vector3f(-1.75f, 0.75f, -1.0f), new Vector3f(0, 0, 0), new Vector3f(0.25f, 0.25f, 0.25f));
        Ycoordinate = new TextItem("", new Vector3f(-1.0f, 0.75f, -1.0f), new Vector3f(0, 0, 0), new Vector3f(0.25f, 0.25f, 0.25f));
        Zcoordinate = new TextItem("", new Vector3f(-0.25f, 0.75f, -1.0f), new Vector3f(0, 0, 0), new Vector3f(0.25f, 0.25f, 0.25f));
        //loadingScreen = new TextItem("Loading", new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

        List<TextItem> textEntityList = new ArrayList<>();

        textEntityList.add(FPSCounter);
        textEntityList.add(Xcoordinate);
        textEntityList.add(Ycoordinate);
        textEntityList.add(Zcoordinate);
        //textEntityList.add(loadingScreen);

        fontModel = new Model("font", fontList, textEntityList);

    }

    public void initializeEntities() {

        scene.addObject(center);
        //scene.addObject(orbit);
        //scene.addObject(orbit2);
        //scene.addObject(orbit3);
        //scene.addObject(orbit4);
        //scene.addObject(faceEntity);
        //scene.addObject(cube_render);
        //scene.addObject(cube_render2);
        //scene.addObject(cube_render3);
        //scene.addObject(circle);
        system.createSystem(137653, scene);

    }

    public void updateMatrix() {

        //updateCube1();
        //updateCube2();
        //updateCube3();
        //updateOrbit();
        //spherePoints();

    }
/*
    public void updateCube1() {

        temp += 0.01f;
        temp2 += 0.001f;

        cube_render.updateXRotation((float) Math.sin(temp)*90);
        cube_render.updateZPosition((float) Math.sin(temp));

    }

    public void updateCube2() {

        //cube_render2.updateXPosition(camera.getPosition().getX());
        tempCubeAngle += 0.01f;
        float tempCubeX = (float) Math.sin(tempCubeAngle) * 5f;
        float tempCubeY = (float) Math.cos(tempCubeAngle) * 5f;
        cube_render2.updateXPosition(tempCubeX);
        cube_render2.updateYPosition(tempCubeY);
        //System.out.println(cube_render2.getPosition().getX());


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
*/

    public Model getTextModel() {

        return fontModel;

    }

    public Model getSkyModel() {

        return skyModel;

    }

    public void createLoading() {



    }
}
