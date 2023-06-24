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

    private Renderer renderer;
    private Shader shader;

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
        render.createMaterials();

        // Creates the mesh before the program renders the mesh
        render.createMeshes();
        render.initializeEntities();
        shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

        // Sets up the renderer to use the shader
        renderer = new Renderer(windowObject, shader);

        // Creates the shader before the program renders the shader
        shader.create();

        glEnable(GL_DEPTH_TEST);

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(windowObject.window) ) {

            windowObject.loop();

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

        renderer.renderMesh(render.camera, scene);
        Window.swapBuffer();

    }

}
