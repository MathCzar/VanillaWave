package Main;

import VanillaWaveEngine.*;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.Renderer;
import VanillaWaveEngine.Rendering.Shader;
import VanillaWaveEngine.Rendering.Texture;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main implements Runnable {

    public Window windowObject;

    public Thread game;

    public int width = 1920, height = 1080;
    public String title = "Window Thing";

    private float temp, temp2, tempOrbitAngle = 0, tempOrbitX, tempOrbitZ, tempOrbitY, gameCycle = 0, tempOrbitX2, tempOrbitZ2, tempOrbitY2, tempOrbitAngle2 = 0;

    private int sphereResolution = 2;

    private Renderer renderer;
    private Shader shader;

    CubeMesh cube = new CubeMesh();

    public Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

    //public Entity cube_render = new Entity(new Vector3f(2.5f, 2.5f, 2.5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render2 = new Entity(new Vector3f(5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    //public Entity cube_render3 = new Entity(new Vector3f(0, 0, 5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity center = new Entity(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity orbit = new Entity(new Vector3f(10, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity orbit2 = new Entity(new Vector3f(0, 10, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity orbit3 = new Entity(new Vector3f(0, 0, 10), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity orbit4 = new Entity(new Vector3f(0, 5, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);

    public static void main(String[] args) {

        // Create a dedicated thread to the game
        new Main().startThread();
        Main main = new Main();

    }

    // Runnable runs this function once
    public void run() {

        // Initialize the window and the objects
        init();

        // Cycle through the game
        loop();

        windowObject.terminate();

    }

    private void init() {

        // Create the window
        windowObject = get();

        shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

        // Sets up the renderer to use the shader
        renderer = new Renderer(windowObject, shader);

        // Creates the shader before the program renders the shader
        shader.create();

        // Creates the mesh before the program renders the mesh
        cube.create();

        glEnable(GL_DEPTH_TEST);

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(windowObject.window) ) {

            windowObject.loop();

            // Updates the object position and rotation
            //updateCube1();
            //updateCube2();
            //updateCube3();
            //updateOrbit();
            //spherePoints();

            // Updates the position of the camera everytime the while loop is run
            camera.update();

            // Renders the square created in the mesh
            render();
            glViewport(0, 0, width, height);

        }

    }

    public Window get() {

        // Makes sure there is only one window created
        if(windowObject == null) {

            windowObject = new Window(width, height, title);
            windowObject.init();

        }

        return windowObject;

    }

    public void startThread() {

        game = new Thread();
        game.start();
        run();

    }

    public void render() {

        //renderer.renderMesh(cube_render, camera);
        //renderer.renderMesh(cube_render2, camera);
        //renderer.renderMesh(cube_render3, camera);
        renderer.renderMesh(center, camera);
        renderer.renderMesh(orbit, camera);
        renderer.renderMesh(orbit2, camera);
        renderer.renderMesh(orbit3, camera);
        renderer.renderMesh(orbit4, camera);
        Window.swapBuffer();

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

            System.out.println("Angle:" + tempOrbitAngle);
            System.out.println("Angle2:" + tempOrbitAngle2);

            tempOrbitX = (float) Math.cos(Math.toRadians(tempOrbitAngle))*2f;
            tempOrbitY = (float) Math.sin(Math.toRadians(tempOrbitAngle))*2f;
            tempOrbitZ = (float) Math.sin(Math.toRadians(tempOrbitAngle))*2f;

            tempOrbitX2 = (float) Math.cos(Math.toRadians(-tempOrbitAngle2))*2f;
            tempOrbitY2 = (float) Math.sin(Math.toRadians(tempOrbitAngle2))*2f;
            tempOrbitZ2 = (float) Math.sin(Math.toRadians(-tempOrbitAngle2))*2f;

            orbit.updateXPosition(tempOrbitX);
            System.out.println("X:" + orbit.getPosition().getX());
            orbit.updateYPosition(tempOrbitY);
            System.out.println("Y:" + orbit.getPosition().getY());
            orbit.updateZPosition(tempOrbitZ);

            orbit2.updateXPosition(tempOrbitX2);
            System.out.println("X2:" + orbit2.getPosition().getX());
            orbit2.updateYPosition(tempOrbitY2);
            System.out.println("Y2:" + orbit2.getPosition().getY());
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

//    public void spherePoints() {
//
//        orbit.updateXPosition(-0.5f);
//        orbit.updateYPosition(-0.5f);
//        orbit2.updateXPosition(0.5f);
//        orbit2.updateYPosition(0.5f);
//        orbit3.updateXPosition(0);
//        orbit3.updateYPosition(0);
//        orbit4.updateXPosition(0);
//        orbit4.updateYPosition(-0.5f);
//
//    }

}
