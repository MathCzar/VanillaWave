package Main;

import VanillaWaveEngine.*;
import VanillaWaveEngine.Rendering.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main implements Runnable {

    public Window windowObject;

    public Thread game;

    public int width = 1920, height = 1080;
    public String title = "Window Thing";

    private Scene scene;

    private Renderer objRenderer, txtRenderer;
    private Shader objShader, txtShader;

    public RenderHandler render;

    public static void main(String[] args) {

        // Create a dedicated thread to the game
        new Main().startThread();

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

        scene = new Scene();

        render = new RenderHandler(scene);
        render.createMeshes();
        render.createMaterials();
        render.createFonts();

        render.initializeEntities();

        // Creates the mesh before the program renders the mesh
        objShader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");
        txtShader = new Shader("src/main/resources/shaders/textVertex.glsl", "src/main/resources/shaders/textFragment.glsl");
        //skyShader = new Shader("src/main/resources/shaders/skyVertex.glsl", "src/main/resources/shaders/skyFragment.glsl");

        // Sets up the renderer to use the shader
        objRenderer = new Renderer(windowObject, objShader);
        txtRenderer = new Renderer(windowObject, txtShader);

        // Creates the shader before the program renders the shader
        objShader.create();
        txtShader.create();

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(windowObject.window) ) {

            // Updates the window
            windowObject.loop();

            // Updates FPS text
            render.FPSCounter.setText(String.valueOf(windowObject.getFrames()));

            // Updates the object position and rotation
            render.updateMatrix();

            // Updates the position of the camera everytime the while loop is run
            render.camera.update();

            // Renders the square created in the mesh
            render();
            glViewport(0, 0, width, height);

        }

    }

    public Window get() {

        // Makes sure there is only one window created
        if(windowObject == null) {

            windowObject = new Window(width, height, title, this);
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

        //render skybox, then objects, then GUI
        //skyRenderer.renderMesh(scene, render.getSkyModel());
        objRenderer.renderMesh(render.camera, scene);
        txtRenderer.renderText(scene, render.getTextModel());
        Window.swapBuffer();

    }

}
