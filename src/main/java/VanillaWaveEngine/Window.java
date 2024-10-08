package VanillaWaveEngine;

import java.io.File;
import java.lang.Thread;
import java.nio.ByteBuffer;
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
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

    private final int windowWidth, windowHeight;
    private int width, height;
    private final String title, windowIcon;
    private boolean isFullscreen, playing, hasResized;
    public static long window;

    private final Main.Main main;

    private final float red, green, blue, alpha;

    private final Matrix4f projection;

    //private long audioDevice, audioContext;

    //Shader shader = new Shader("/resources/shaders/mainVertex.glsl", "/resources/shaders/mainFragment.glsl");

    public int frames, finalFrames;
    public double frameLimit = 144.0;
    public long time;

    public Window(int width, int height, String title, String windowIcon, Main.Main main) {

        // Get LWJGL 3 Version
        System.out.println("The current version of LWJGL is " + Version.getVersion());

        // Sets the size of the start-up window
        this.windowWidth = width;
        this.windowHeight = height;
        this.width = width;
        this.height = height;

        // Sets the title of the window
        this.title = title;

        // Set the icon's image path
        this.windowIcon = windowIcon;

        // Sets the final class
        this.main = main;

        // Sets the rgba of the screen background
        red = 1f;
        green = 0f;
        blue = 0f;
        alpha = 1f;

        this.isFullscreen = true;
        this.playing = false;

        // Set up projection matrix
        projection = Matrix4f.projection(90.0f, (float) 1920 / (float) 1080, 0.01f, 100000.0f);

    }

    public void run() {

        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Destroy audio context
        //alcDestroyContext(audioContext);
        //alcCloseDevice(audioDevice);

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
        window = glfwCreateWindow(this.windowWidth, this.windowHeight, this.title, glfwGetPrimaryMonitor(), NULL);

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

        try (MemoryStack stack = MemoryStack.stackPush()) {

            // Check to see if the file exists
            //FileUtilities.loadAsString(windowIcon);

            // Get the absolute path to find the resource file
            File file = new File(windowIcon);
            String absolutePath = file.getAbsolutePath();
            //System.out.println(absolutePath);

            // Allocate memory to image
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            // Create a byte buffer and check to see if it could
            ByteBuffer image = stbi_load(absolutePath, w, h, channels, 4);
            if (image == null) {
                throw new RuntimeException("Image file [" + windowIcon + "] not loaded: " + stbi_failure_reason());
            }

            // Create the image and the buffer
            GLFWImage iconImage = GLFWImage.malloc();
            GLFWImage.Buffer iconBuffer = GLFWImage.malloc(1);

            // Set the image
            iconImage.set(w.get(), h.get(), image);

            // Set the buffer
            iconBuffer.put(0, iconImage);

            // Set the icon
            glfwSetWindowIcon(window, iconBuffer);

            // Free up the image
            stbi_image_free(image);

        }

        setLocalCallbacks();

        // Initialize OpenAL
        //String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
        //audioDevice = alcOpenDevice(defaultDeviceName);

        //int attributes[] = {0};

        //audioContext = alcCreateContext(audioDevice, attributes);

        //alcMakeContextCurrent(audioContext);

        //ALCCapabilities alcCapabilities = ALC.createCapabilities(audioDevice);

        //ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

        //if (!alCapabilities.OpenAL10) {

            //assert false : "Audio Library not supported";

        //}

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

            main.skyShader.destroy();
            main.txtShader.destroy();
            main.objShader.destroy();

            glfwDestroyWindow(window);

            glfwTerminate();

            main.soundMgr.cleanup();

            System.exit(0);

        }

        if (KeyboardListener.isKeyPressed(GLFW_KEY_P)) {

            main.playerSoundSource.play();

        }

        // If it is not fullscreen and has resized, change the viewport so the player can see the screen they resized to
        if (hasResized && !isFullscreen) {

            glViewport(0, 0, this.width, this.height);

            hasResized = false;

        }

        // Makes the window toggleable to be fullscreen or not
        if (KeyboardListener.isKeyPressed(GLFW_KEY_F11)) {

            // Set the window's position, scale, and refresh rate
            glfwSetWindowMonitor(
                    (window),
                    (isFullscreen ? NULL : glfwGetPrimaryMonitor()),
                    (isFullscreen ? this.windowWidth /4 : 0),
                    (isFullscreen ? this.windowHeight/4 : 0),
                    (isFullscreen ? this.windowWidth /2 : this.windowWidth),
                    (isFullscreen ? this.windowHeight/2 : this.windowHeight),
                    (GLFW_DONT_CARE));

            //glfwSetWindowSize(window, (isFullscreen ? this.windowWidth /2 : this.windowWidth), (isFullscreen ? this.windowHeight/2 : this.windowHeight));

            // Sets the view of the window
            glViewport((isFullscreen ? this.windowWidth/4 : 0),
                    (isFullscreen ? this.windowHeight/4 : 0),
                    (isFullscreen ? this.windowWidth/2 : this.windowWidth),
                    (isFullscreen ? this.windowHeight/2 : this.windowHeight));

            isFullscreen = isFullscreen ? false : true;

            //if (isFullscreen) {

                //isFullscreen = false;

            //}
            //else if (!isFullscreen) {

                //isFullscreen = true;

            //}
            //else {

                //throw new RuntimeException("The window variable is neither true nor false");

            //}

            // Makes the thread sleep to prevent the fullscreen bugging out
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException ex){

                throw new RuntimeException("The thread could not sleep");

            }

        }

        //glfwSetWindowSizeCallback(window, GLFW);

        //if (isFullscreen) {

            //double lastTime = glfwGetTime();
            //while (glfwGetTime() < lastTime + 1.0/frameLimit) {

            //}
        //}

        if (KeyboardListener.isKeyPressed(GLFW_KEY_DELETE)) {
            if (!playing) {
                playing = true;
            }
            else {
                playing = false;
            }
        }

        if (KeyboardListener.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {

            main.render.camera.setMoveSpeed(1.0f);

        }
        else if (!KeyboardListener.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {

            main.render.camera.setMoveSpeed(0.05f);

        }

        // Toggleable mouse lock
        if (playing) {

            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        }
        else {

            glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

        }

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

        //MacOS stuff
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 1);

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

        // Destroy audio context
        //alcDestroyContext(audioContext);
        //alcCloseDevice(audioDevice);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public int getFrames() {

        return finalFrames;

    }

    private void setLocalCallbacks() {

        //Checks to see if the window was resized
        GLFWWindowSizeCallback windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int callWidth, int callHeight) {

                width = callWidth;
                height = callHeight;
                hasResized = true;

            }
        };

        glfwSetWindowSizeCallback(window, windowSizeCallback);

    }

}
