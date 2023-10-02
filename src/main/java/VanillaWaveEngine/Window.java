package VanillaWaveEngine;

import java.lang.Thread;
import java.nio.IntBuffer;

import VanillaWaveEngine.Input.KeyboardListener;
import VanillaWaveEngine.Input.MouseListener;
import VanillaWaveEngine.Math.Matrix4f;
import VanillaWaveEngine.Rendering.Shader;

import org.lwjgl.Version;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final int width, height;
    private final String title;
    private boolean isFullscreen;
    public static long window;

    private Main.Main main;

    private float red, green, blue, alpha;

    private Matrix4f projection;

    Shader shader = new Shader("src/main/resources/shaders/mainVertex.glsl", "src/main/resources/shaders/mainFragment.glsl");

    public int frames, finalFrames;
    public double frameLimit = 144.0;
    public long time;

    public Window(int width, int height, String title, Main.Main main) {

        // Get LWJGL 3 Version
        System.out.println("The current version of LWJGL is " + Version.getVersion());

        // Sets the size of the start-up window
        this.width = width;
        this.height = height;

        // Sets the title of the window
        this.title = title;

        this.main = main;

        // Sets the rgba of the screen background
        red = 1f;
        green = 0f;
        blue = 0f;
        alpha = 1f;

        this.isFullscreen = false;

        // Set up projection matrix
        projection = Matrix4f.projection(90.0f, (float) 1920 / (float) 1080, 0.1f, 1000.0f);

    }

    public void run() {

        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init() {

        // Setup an error callback.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW.
        if ( !glfwInit() ) {

            throw new IllegalStateException("Unable to initialize GLFW");

        }

        // Configure the GLFW window
        VanillaWaveDefaultConfig();

        // Create the window
        window = glfwCreateWindow(this.width, this.height, this.title, glfwGetPrimaryMonitor(), NULL);

        try (MemoryStack stack = stackPush()) {

            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Sets the position of the window to the middle of the screen
            glfwSetWindowPos(window,
                    (vidmode.width() - pWidth.get(0)) / 4,
                    (vidmode.height() - pHeight.get(0)) / 4);

        }

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

    }

    public void loop() {

            // Measure speed
            frames++;
            if (System.currentTimeMillis() > time + 1000) {

                finalFrames = frames;
                time = System.currentTimeMillis();
                frames = 0;

            }

            // Set the clear color
            glClearColor(red, green, blue, alpha);

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Destroys the window and terminates the program without an error
            if (KeyboardListener.isKeyPressed(GLFW_KEY_ESCAPE)) {

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

    private void GLFWCallbacks() {

        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback); // Get mouse position
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback); // Get mouse scroll position
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback); // Get mouse button
        glfwSetKeyCallback(window, KeyboardListener::keyCallback); // Get keyboard button

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

    public static void swapBuffer() {

        // Refresh positions
        GLFW.glfwSwapBuffers(window);

    }

    public void terminate() {

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(main.windowObject.window);
        glfwDestroyWindow(main.windowObject.window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public int getFrames() {

        return finalFrames;

    }

}
