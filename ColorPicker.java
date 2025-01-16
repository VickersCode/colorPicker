import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ColorPicker {
    public static void main(String[] args) {
        List<Rainbow> colorList = parseColors("colors.txt");
        Rainbow[] colorsArray = colorList.toArray(new Rainbow[0]);
        new GUI(colorsArray);
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