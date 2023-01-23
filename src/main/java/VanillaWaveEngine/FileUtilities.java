package VanillaWaveEngine;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtilities {

    public static String loadAsString(String path) {

        StringBuilder result = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {

            String line;

            // While there are still lines for the shaders, append that result
            while((line = reader.readLine()) != null) {

                result.append(line).append("\n");

            }

        } catch (IOException e) {

            System.err.println("Could not find the file at " + path);


        }

        return result.toString();

    }

}
