package VanillaWaveEngine;

import org.lwjgl.system.MemoryUtil;

import java.io.*;
import java.nio.FloatBuffer;

public class FileUtilities {

    public static String loadAsString(String path) {

        StringBuilder result = new StringBuilder();

        try (InputStream inputStream = FileUtilities.class.getResourceAsStream(path); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;

            // While there are still lines for the shaders, append that result
            while((line = reader.readLine()) != null) {

                result.append(line).append("\n");

            }

        } catch (IOException e) {
            System.out.println(e);

            System.err.println("Could not find the file at " + path);


        }

        return result.toString();

    }

    public static FloatBuffer storeDataInFloatBuffer(float[] data) {

        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();
        return buffer;

    }

}
