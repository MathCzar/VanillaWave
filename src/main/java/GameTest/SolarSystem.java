package GameTest;

import VanillaWaveEngine.Math.Vector3f;
import VanillaWaveEngine.Object;
import VanillaWaveEngine.Rendering.Scene;

import java.util.Random;

public class SolarSystem {

    public void createSystem(long seed, Scene scene) {

        Random random = new Random();

        random.setSeed(seed);

        float sunSize = random.nextFloat() * 100;
        int numPlanets = random.nextInt(5);
        float psPosition = 50;

        Object sun = new Object(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), new Vector3f(sunSize, sunSize, sunSize), 3,"sphere");

        for (int p = 0; p <= numPlanets; p++) {

            float pSize = random.nextFloat() * 10;
            float pPosition = random.nextFloat() * 10;
            float pSpeed = random.nextFloat();

            scene.addObject(new Object(new Vector3f(pPosition + psPosition, 0, 0), new Vector3f(0, 0, 0), new Vector3f(pSize, pSize, pSize), 2, "sphere"));
            psPosition = pPosition;

        }

        scene.addObject(sun);

        for (int o = 0; o <= 0; o++) {



        }

    }

    public void updateSystem() {



    }

}
