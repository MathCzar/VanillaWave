package VanillaWaveEngine.Input;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {

    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lastY;
    private boolean mouseButtonPressed [] = new boolean[3];
    private boolean isDragging;

    private MouseListener() {

        // Sets all variables to 0
        scrollX = 0.0f;
        scrollY = 0.0f;
        xPos = 0.0f;
        yPos = 0.0f;
        lastX = 0.0f;
        lastY = 0.0f;

    }

    public static MouseListener get() {

        // Makes sure there is only 1 instance of the mouse listener
        if (MouseListener.instance == null) {

            MouseListener.instance = new MouseListener();

        }

        return MouseListener.instance;

    }

    public static void mousePosCallback(long window, double xPosCallback, double yPosCallback) {

        // Sets the last x and y position of the mouse to the current x and y position
        get().lastX = get().xPos;
        get().lastY = get().yPos;

        get().xPos = xPosCallback;
        get().yPos = yPosCallback;

        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];

    }

    public static void mouseButtonCallback(long window, int button, int action, int mods) {

        // Checks if a button on the mouse is pressed
        if (action == GLFW_PRESS) {

            // Checks if a button on the mouse is one of the 3 standard buttons
            if (button < get().mouseButtonPressed.length) {

                get().mouseButtonPressed[button] = true;

            }
            else {

                throw new IllegalStateException("Mouse button pressed out of bounds");

            }

        }
        // Checks if a button on the mouse is released
        else if (action == GLFW_RELEASE) {

            // Checks if a button on the mouse is one of the 3 standard buttons
            if (button < get().mouseButtonPressed.length) {

                get().mouseButtonPressed[button] = false;
                get().isDragging = false;

            }
            else {

                throw new IllegalStateException("Mouse button pressed out of bounds");

            }

        }

    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {

        // Gets the offset of the scroll-wheel on the mouse
        get().scrollX = xOffset;
        get().scrollY = yOffset;

    }

    // Ends the frame
    public static void endFrame() {

        get().lastX = get().xPos;
        get().lastY = get().yPos;

        get().scrollX = 0;
        get().scrollY = 0;

    }

    // Gets the x-position of the mouse
    public static float getX() {

        return (float) get().xPos;

    }

    // Gets the y-position of the mouse
    public static float getY() {

        return (float) get().yPos;

    }

    // Gets the lapsed x-position of the mouse in the current frame
    public static float getDX() {

        return (float) (get().lastX - get().xPos);

    }

    // Gets the lapsed y-position of the mouse in the current frame
    public static float getDY() {

        return (float) (get().lastY - get().yPos);

    }

    public static float getScrollX() {

        return (float) get().scrollX;

    }

    public static float getScrollY() {

        return (float) get().scrollY;

    }

    public static boolean isDragging() {

        return get().isDragging;

    }

    public static boolean buttonPressedDown(int button) {

        if (button < get().mouseButtonPressed.length) {

            return get().mouseButtonPressed[button];

        }
        else {

            return false;

        }

    }

}
