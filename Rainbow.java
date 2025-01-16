public class Rainbow {
    private String color;
    private int[] rgbVals;

    public Rainbow(String color, int[] rgbVals) {
        this.color = color;
        this.rgbVals = rgbVals;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String newColor) {
        this.color = newColor;
    }

    public int[] getRGB() {
        return rgbVals;
    }

    public void setRGB(int[] newRGB) {
        this.rgbVals = newRGB;
    }

    // So the color's name is printed in the window
    @Override
    public String toString() {
        return getColor();
    }
}
