package VanillaWaveEngine.Input;

import java.io.*;
import java.util.HashMap;

public class KeybindHandler {

    private static File options;

    public static HashMap<String, Integer> keybinds;

    public KeybindHandler(HashMap<String, Integer> defaultKeybinds) {

        // Default window configuration
        keybinds = defaultKeybinds;

        try {
            options = new File("src/main/resources/keybinds.txt");

            if (options.createNewFile()) { // Create a new file if no file exists

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(options))) {

                    for (String i : keybinds.keySet()) {

                        writer.write(i + ": " + keybinds.get(i) + "\n");

                    }

                } catch (IOException e) {

                    System.out.println("Could not write to keybinds.txt");

                }

            } else { // If the file already exists, get all options

                BufferedReader reader = new BufferedReader(new FileReader(options));

                String line;
                while ((line = reader.readLine()) != null) {

                    String[] arrLine = line.split(":");
                    keybinds.put(arrLine[0], Integer.valueOf(arrLine[1].trim()));

                }

            }

        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Error creating keybinds.txt");

        }
    }

    public void saveKeybinds() {

        if (options.delete()) { // If I can delete the file

            try {
                if (options.createNewFile()) { // Create a new file if no file exists

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(options))) {

                        for (String i : keybinds.keySet()) {

                            writer.write(i + ": " + keybinds.get(i) + "\n");

                        }

                    } catch (IOException e) {

                        System.out.println("Could not write to keybinds.txt");

                    }

                } else {

                    System.out.println("Could not create keybinds.txt");

                }

            } catch(IOException e) {

                e.printStackTrace();
                System.out.println("Error creating keybinds.txt");

            }

        } else {

            System.out.println("keybinds.txt could not be deleted");

        }


    }

    public HashMap<String, Integer> getKeybinds() {

        return keybinds;

    }

}
