package VanillaWaveEngine;

import java.lang.Thread;

import VanillaWaveEngine.Input.KeyboardListener;
import VanillaWaveEngine.Input.MouseListener;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Rendering.Renderer;
import VanillaWaveEngine.Rendering.Shader;
import Main.CubeMesh;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final int width, height;
    private final String title;
    private boolean isFullscreen;
    private static Window windowPresent = null;
    private long window;

    private float red, green, blue, alpha;

    private float temp, tempOrbit, temp2;

    private Renderer renderer;
    private Matrix4f projection;

    public int frames;
    public double frameLimit = 60.0;
    public long time;

    CubeMesh cube = new CubeMesh();

    public Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

    public Entity cube_render = new Entity(new Vector3f(2.5f, 2.5f, 2.5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity cube_render2 = new Entity(new Vector3f(5f, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity cube_render3 = new Entity(new Vector3f(0, 0, 5f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity center = new Entity(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);
    public Entity orbit = new Entity(new Vector3f(0, 1, 0), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), cube.meshCube);

    public Window() {

        System.out.println("The current version of LWJGL is " + Version.getVersion());

        // Sets the size of the start-up window
        this.width = 1920;
        this.height = 1080;

        // Sets the title of the window
        this.title = "Window Thing";

        // Sets the rgba of the screen background
        red = 1f;
        green = 0f;
        blue = 0f;
        alpha = 1f;

        this.isFullscreen = false;

    }

    public static Window get() {

        // Makes sure there is only 1 window created
        if(Window.windowPresent == null) {

            Window.windowPresent = new Window();

        }

        return Window.windowPresent;

    }

    public void run() {

        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    private void init() {

        // Setup an error callback.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW.
        if ( !glfwInit() ) {

            throw new IllegalStateException("Unable to initialize GLFW");

        }

        // Configure the GLFW window
        VanillaWaveDefaultConfig();

        // Create the window
        window = glfwCreateWindow(width, height, title, glfwGetPrimaryMonitor(), NULL);

        // Sets the position of the window to the middle of the screen
        glfwSetWindowPos(window, width/4, height/4);

        if ( window == NULL ) {

            throw new RuntimeException("Failed to create the GLFW window");

        }

        // Sets up a callback
        GLFWCallbacks();

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Set up time
        time = System.currentTimeMillis();

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        projection = Matrix4f.projection(90.0f, (float) width / (float) height, 0.1f, 1000.0f);

        Shader shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

        // Sets up the renderer to use the shader
        renderer = new Renderer(this, shader);

        // Creates the shader before the program renders the shader
        shader.create();

        // Creates the mesh before the program renders the mesh
        cube.create();

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Measure speed
            frames++;
            if (System.currentTimeMillis() > time + 1000) {

                System.out.println(frames);
                time = System.currentTimeMillis();
                frames = 0;

            }

            // Updates the position of the camera everytime the while loop is run
            camera.update();

            // Updates the object position and rotation
            updateCube1();
            updateCube2();
            updateCube3();
            updateOrbit();

            // Renders the square created in the mesh
            render();

            // Set the clear color
            glClearColor(red, green, blue, alpha);

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Destroys the window and terminates the program without an error
            if (KeyboardListener.isKeyPressed(GLFW_KEY_ESCAPE)) {

                Shader shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

                shader.destroy();

                glfwDestroyWindow(window);

                glfwTerminate();
                System.exit(0);

            }

            // Makes the window toggleable to be fullscreen or not
            if (KeyboardListener.isKeyPressed(GLFW_KEY_F11)) {

                glfwSetWindowMonitor(
                        (window),
                        (isFullscreen ? NULL : glfwGetPrimaryMonitor()),
                        (isFullscreen ? this.width/4 : 0),
                        (isFullscreen ? this.height/4 : 0),
                        (isFullscreen ? this.width/2 : this.width),
                        (isFullscreen ? this.height/2 : this.height),
                        (GLFW_DONT_CARE));

                // Enable v-sync
                glfwSwapInterval(1);

                if (isFullscreen) {

                    isFullscreen = false;



                }
                else if (!isFullscreen) {

                    isFullscreen = true;

                }
                else {

                    throw new RuntimeException("The window variable is neither true nor false");

                }

                // Makes the thread sleep to prevent the fullscreen bugging out
                try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex){

                    throw new RuntimeException("The thread could not sleep");

                }

            }
            if (isFullscreen) {

                double lastTime = glfwGetTime();
                while (glfwGetTime() < lastTime + 1.0/frameLimit) {

                }

            }

            // Toggleable mouse lock
            glfwSetInputMode(window, GLFW_CURSOR, (MouseListener.buttonPressedDown(GLFW_MOUSE_BUTTON_LEFT) ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL));

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();


        }
    }

    public void render() {

        renderer.renderMesh(cube_render, camera);
        renderer.renderMesh(cube_render2, camera);
        renderer.renderMesh(cube_render3, camera);
        renderer.renderMesh(center, camera);
        renderer.renderMesh(orbit, camera);
        GLFW.glfwSwapBuffers(window);

    }

    private void GLFWCallbacks() {

        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetKeyCallback(window, KeyboardListener::keyCallback);

    }

    private void VanillaWaveDefaultConfig() {

        glfwDefaultWindowHints(); // The window will have the default hints
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // The window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // The window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE); // The window will be maximized

    }

    public Matrix4f getProjectionMatrix() {

        return projection;

    }

    public void updateCube1() {

        temp += 0.01f;
        temp2 += 0.001f;

        cube_render.updateXRotation((float) Math.sin(temp)*90);
        cube_render.updateZPosition((float) Math.sin(temp));

    }

    public void updateCube2() {

        cube_render2.updateXPosition(camera.getPosition().getX());

    }

    public void updateCube3() {

        cube_render3.updateYPosition(camera.getPosition().getY());

    }

    public void updateOrbit() {

        tempOrbit += 1f;

        orbit.updateXRotation(tempOrbit);


    }

}
