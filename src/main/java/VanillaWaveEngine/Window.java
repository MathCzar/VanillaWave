package VanillaWaveEngine;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;

import VanillaWaveEngine.Input.KeybindHandler;
import VanillaWaveEngine.Input.KeyboardListener;
import VanillaWaveEngine.Input.MouseListener;
import VanillaWaveEngine.Math.Matrix4f;

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

    private int windowWidth, windowHeight;
    private final String title, windowIcon;
    private boolean isFullscreen, hasResized;
    public static long window;

    private final Main.Main main;

    private final float red, green, blue, alpha;

    private Matrix4f projection;

    public int frames, finalFrames;
    public double frameLimit = 144.0;
    public long time;

    KeybindHandler keybindHandler;
    HashMap<String, Integer> defaultKeybinds = new HashMap<>();


    boolean pT = true;

    public Window(int width, int height, String title, String windowIcon, Main.Main main) {

        // Get LWJGL Version
        System.out.println("The current version of LWJGL is " + Version.getVersion());

        // Sets the size of the start-up window
        this.windowWidth = width;
        this.windowHeight = height;

        // Sets the title of the window
        this.title = title;

        // Set the icon's image path
        this.windowIcon = windowIcon;

        // Sets the main class
        this.main = main;

        // Sets the rgba of the screen background
        red = 1f;
        green = 0f;
        blue = 0f;
        alpha = 1f;

        this.isFullscreen = true;

        // Set up projection matrix
        projection = setProjectionMatrix(90.0f, (float) windowWidth, (float) windowHeight, 0.01f, 100000.0f);

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

        defaultKeybinds.put("left", GLFW_KEY_A);
        defaultKeybinds.put("right", GLFW_KEY_D);
        defaultKeybinds.put("forward", GLFW_KEY_W);
        defaultKeybinds.put("backward", GLFW_KEY_S);
        defaultKeybinds.put("up", GLFW_KEY_SPACE);
        defaultKeybinds.put("down", GLFW_KEY_LEFT_SHIFT);
        defaultKeybinds.put("quit", GLFW_KEY_ESCAPE);
        defaultKeybinds.put("focus mouse", GLFW_KEY_DELETE);
        defaultKeybinds.put("play sound", GLFW_KEY_P);

        keybindHandler = new KeybindHandler(defaultKeybinds);

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
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Sets the position of the window to the middle of the screen
            glfwSetWindowPos(window,
                    (vidMode.width() - pWidth.get(0)) / 4,
                    (vidMode.height() - pHeight.get(0)) / 4);

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
        glfwSwapInterval(GLFW_TRUE);

        // Make the window visible
        glfwShowWindow(window);

        try (MemoryStack stack = MemoryStack.stackPush()) {

            // Get the absolute path to find the resource file
            File file = new File(windowIcon);
            String absolutePath = file.getAbsolutePath();

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
            iconImage.set(w.get(), h.get(), image); // Set the image
            iconBuffer.put(0, iconImage); // Set the buffer
            glfwSetWindowIcon(window, iconBuffer); // Set the icon
            stbi_image_free(image); // Free up the image

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

        // Measure FPS
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

        // Call on keybinds


        // Destroys the window and terminates the program without an error
        if (KeyboardListener.isKeyPressed(keybindHandler.getKeybinds().get("quit"))) {

            main.skyShader.destroy();
            main.txtShader.destroy();
            main.objShader.destroy();

            glfwDestroyWindow(window);

            glfwTerminate();

            main.soundMgr.cleanup();

            System.exit(0);

        }

        if (KeyboardListener.isKeyPressed(GLFW_KEY_P)) {

            projection = pT ? setProjectionMatrix(90.0f, (float) 10, (float) 5, 0.01f, 100000.0f) : setProjectionMatrix(90.0f, (float) 4, (float) 3, 0.01f, 100000.0f);

            pT = !pT;
        }

        // Makes the window toggleable to be fullscreen or not
        if (KeyboardListener.isKeyPressed(GLFW_KEY_F11, true)) {

            // Set the window's position, scale, and refresh rate
            glfwSetWindowMonitor(
                    (window),
                    (isFullscreen ? NULL : glfwGetPrimaryMonitor()),
                    (isFullscreen ? this.windowWidth / 4 : 0),
                    (isFullscreen ? this.windowHeight / 4 : 0),
                    (isFullscreen ? this.windowWidth / 2 : this.windowWidth),
                    (isFullscreen ? this.windowHeight / 2 : this.windowHeight),
                    (GLFW_DONT_CARE));

            // Sets the view of the window
            glViewport((isFullscreen ? this.windowWidth / 4 : 0),
                    (isFullscreen ? this.windowHeight / 4 : 0),
                    (isFullscreen ? this.windowWidth / 2 : this.windowWidth),
                    (isFullscreen ? this.windowHeight / 2 : this.windowHeight));

            glfwWindowHint(GLFW_MAXIMIZED, isFullscreen ? GLFW_FALSE : GLFW_TRUE);


            isFullscreen = !isFullscreen;

        }

        // Toggleable mouse lock
        if (KeyboardListener.isKeyPressed(GLFW_KEY_DELETE, true)) {

            glfwSetInputMode(window, GLFW_CURSOR, glfwGetInputMode(window, GLFW_CURSOR) == GLFW_CURSOR_NORMAL ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);

        }

        main.render.camera.moveSpeed = KeyboardListener.isKeyPressed(GLFW_KEY_LEFT_CONTROL) ? 1.0f : 0.05f;

        // If the window has resized, change the viewport so the player can see the screen they resized to
        if (hasResized) {

            glViewport(0, 0, this.windowWidth, this.windowHeight);

            hasResized = false;

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

    public Matrix4f setProjectionMatrix(float fov, float aspectWidth, float aspectHeight, float near, float far) {

        return Matrix4f.projection(fov, aspectWidth / aspectHeight, near, far);

    }

    public static void swapBuffer() {

        // Refresh positions
        GLFW.glfwSwapBuffers(window);

    }

    public void terminate() {

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

    private void setLocalCallbacks() {

        //Checks to see if the window was resized
        GLFWWindowSizeCallback windowSizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int callWidth, int callHeight) {

                windowWidth = callWidth;
                windowHeight = callHeight;
                hasResized = true;

            }
        };

        glfwSetWindowSizeCallback(window, windowSizeCallback);

    }

    public int getFrames() {

        return finalFrames;

    }

    public long getTime() {

        return time;

    }

}
