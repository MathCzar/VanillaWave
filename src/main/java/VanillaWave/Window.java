package VanillaWave;

import java.lang.Thread;

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

    private Window() {

        System.out.println("The current version of LWJGL is " + Version.getVersion());

        // Sets the size of the start-up window
        this.width = 1920;
        this.height = 1080;

        // Sets the title of the window
        this.title = "Window Thing";

        // Sets the rgba of the screen background
        red = 1;
        green = 1;
        blue = 1;
        alpha = 1;

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

        // Configure GLFW
        glfwDefaultWindowHints(); // The window will have the default hints
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // The window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // The window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE); // The window will be maximized

        // Create the window
        window = glfwCreateWindow(this.width/2, this.height/2, this.title, NULL, NULL);

        // Sets the position of the window to the middle of the screen
        glfwSetWindowPos(window, this.width/4, this.height/4);

        if ( window == NULL ) {

            throw new RuntimeException("Failed to create the GLFW window");

        }

        // Sets up a callback
        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetKeyCallback(window, KeyboardListener::keyCallback);

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

    }

    private void loop() {

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( !glfwWindowShouldClose(window) ) {

            // Set the clear color
            glClearColor(red, green, blue, alpha);

            // clear the framebuffer
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // swap the color buffers
            glfwSwapBuffers(window);

            if (fadeToBlack) {

                red = Math.max(red - 0.01f, 0);
                green = Math.max(red - 0.01f, 0);
                blue = Math.max(red - 0.01f, 0);

            }

            if (KeyboardListener.isKeyPressed(GLFW_KEY_SPACE)) {

                fadeToBlack = true;

            }

            if (KeyboardListener.isKeyPressed(GLFW_KEY_ESCAPE)) {

                glfwDestroyWindow(window);

                glfwTerminate();
                System.exit(0);

            }

            // Makes the window toggleable to be fullscreen or not
            if (KeyboardListener.isKeyPressed(GLFW_KEY_F11)) {

                if (isFullscreen) {

                    isFullscreen = false;
                    glfwSetWindowMonitor(window, NULL, 1920/4, 1080/4, 1920/2, 1080/2, GLFW_DONT_CARE);

                    // Makes the thread sleep to prevent the fullscreen bugging out
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ex){

                        throw new RuntimeException("The thread could not sleep");

                    }
                }
                else if (!isFullscreen) {

                    isFullscreen = true;
                    glfwSetWindowMonitor(window, glfwGetPrimaryMonitor(), 0, 0, 1920, 1080, GLFW_DONT_CARE);

                    // Makes the thread sleep to prevent the fullscreen bugging out
                    try {
                        Thread.sleep(500);
                    }
                    catch (InterruptedException ex){

                        throw new RuntimeException("The thread could not sleep");

                    }

                }
                else {

                    throw new RuntimeException("The window variable is neither true nor false");

                }

            }

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

}
