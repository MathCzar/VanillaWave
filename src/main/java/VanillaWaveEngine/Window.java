package VanillaWaveEngine;

import java.lang.Thread;

import VanillaWaveEngine.Input.KeyboardListener;
import VanillaWaveEngine.Input.MouseListener;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Math.Vertex;
import VanillaWaveEngine.Rendering.Mesh;
import VanillaWaveEngine.Rendering.Renderer;
import VanillaWaveEngine.Rendering.Shader;
import org.lwjgl.*;
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
    private boolean fadeToBlack;

    private Renderer renderer;
    private Matrix4f projection;

    Camera camera = new Camera(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

    public Mesh mesh = new Mesh(new Vertex[] {

            new Vertex(new Vector3f(-0.5f, 0.5f, 0.0f)),
            new Vertex(new Vector3f(0.5f, 0.5f, 0.0f)),
            new Vertex(new Vector3f(0.5f, -0.5f, 0.0f)),
            new Vertex(new Vector3f(-0.5f, -0.5f, 0.0f))

    }, new int[] {

            0, 1, 2,
            0, 3, 2

    });

    public Object object = new Object(new Vector3f(0, 0, 1), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1), mesh);

    private Window() {

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
        GLFWConfig();

        // Create the window
        window = glfwCreateWindow(this.width/2, this.height/2, this.title, NULL, NULL);

        // Sets the position of the window to the middle of the screen
        glfwSetWindowPos(window, this.width/4, this.height/4);

        if ( window == NULL ) {

            throw new RuntimeException("Failed to create the GLFW window");

        }

        // Sets up a callback
        GLFWCallbacks();

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

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

        projection = Matrix4f.projection(70.0f, (float) width / (float) height, 0.1f, 1000.0f);

        Shader shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

        // Sets up the renderer to use the shader
        renderer = new Renderer(this, shader);

        // Creates the shader before the program renders the shader
        shader.create();

        // Creates the mesh before the program renders the mesh
        mesh.create();

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Updates the position of the camera everytime the while loop is run
            camera.update();

            // Updates the object position and rotation
            object.update();

            // Renders the square created in the mesh
            render();

            // Set the clear color
            glClearColor(red, green, blue, alpha);

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            if (fadeToBlack) {

                red = Math.max(red - 0.01f, 0);
                green = Math.max(green - 0.01f, 0);
                blue = Math.max(blue - 0.01f, 0);

            }

            if (KeyboardListener.isKeyPressed(GLFW_KEY_P)) {

                fadeToBlack = true;

            }

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
                        (isFullscreen ? width/4 : 0),
                        (isFullscreen ? height/4 : 0),
                        (isFullscreen ? width/2 : width),
                        (isFullscreen ? height/2 : height),
                        (GLFW_DONT_CARE));

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

            // Toggleable mouse lock

            glfwSetInputMode(window, GLFW_CURSOR, ( MouseListener.buttonPressedDown(GLFW_MOUSE_BUTTON_LEFT) ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL));


            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public void render() {

        renderer.renderMesh(object, camera);
        GLFW.glfwSwapBuffers(window);

    }

    private void GLFWCallbacks() {

        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetKeyCallback(window, KeyboardListener::keyCallback);

    }

    private void GLFWConfig() {

        glfwDefaultWindowHints(); // The window will have the default hints
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // The window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // The window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE); // The window will be maximized

    }

    public Matrix4f getProjectionMatrix() {

        return projection;

    }

}
