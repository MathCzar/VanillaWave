package Main;

import VanillaWaveEngine.*;

public class Main {

    public Thread window;

    public static void main(String[] args) {

        new Main().startThread();

        Window window = Window.get();
        window.run();

    }

    public void startThread() {

        window = new Thread();
        window.start();

    }

}
