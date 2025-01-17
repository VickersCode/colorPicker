import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColorPicker {
    public static void main(String[] args) {
        List<Rainbow> colorList = parseColors("colors.txt");
        Rainbow[] colorsArray = colorList.toArray(new Rainbow[0]);
        GUI gui = new GUI(colorsArray);
        
        // Add this to ensure the file is written when the application closes
        gui.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                writeColorsToFile(colorList, "colors.txt");
            }
        });
    }

    public static void writeColorsToFile(List<Rainbow> colors, String fileName) {
    try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
        for (Rainbow color : colors) {
            out.printf("%s %d %d %d%n", color.getColor(), color.getRGB()[0], color.getRGB()[1], color.getRGB()[2]);
        }
    } catch (IOException e) {
        System.err.println("Error writing to file: " + e.getMessage());
    }
    }

    private static List<Rainbow> parseColors(String fileName) {
        List<Rainbow> colors = new ArrayList<>();
    
        try (Scanner scanner = new Scanner(new File(fileName))) { 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] attributes = line.split("\\s+");
    
                String colorName = attributes[0];
                int[] rgb = {
                    Integer.parseInt(attributes[1]),
                    Integer.parseInt(attributes[2]),
                    Integer.parseInt(attributes[3])
                };
                colors.add(new Rainbow(colorName, rgb));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }
    
        return colors;
    }
}