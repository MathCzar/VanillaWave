package VanillaWaveEngine.Input;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardListener {

    private static KeyboardListener instance;
    private boolean keyPressed [] = new boolean[350];

    public static KeyboardListener get() {

        // Makes sure there is only 1 instance of the mouse listener
        if (KeyboardListener.instance == null) {

            KeyboardListener.instance = new KeyboardListener();

        }

        return KeyboardListener.instance;

    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {

        // Checks if a button on the mouse is pressed
        if (action == GLFW_PRESS) {

            // Checks if a button on the mouse is one of the 3 standard buttons
            if (key < get().keyPressed.length) {

                get().keyPressed[key] = true;

            }

        }
        // Checks if a button on the mouse is released
        else if (action == GLFW_RELEASE) {

            // Checks if a button on the mouse is one of the 3 standard buttons
            if (key < get().keyPressed.length) {

                get().keyPressed[key] = false;

            }

        }

    }

    public static boolean isKeyPressed(int keyNum) {

        if (keyNum < get().keyPressed.length) {

            return get().keyPressed[keyNum];

        }
        else {

            throw new IllegalStateException("Key pressed out of bounds");

        }

    }

}
