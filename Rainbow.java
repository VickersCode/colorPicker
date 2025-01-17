public class Rainbow {
    private String color;
    private int[] rgbVals;
    private int[] savedRGB; // Stores the last saved state

    public Rainbow(String color, int[] rgbVals) {
        this.color = color;
        this.rgbVals = rgbVals;
        this.savedRGB = rgbVals.clone(); // Initialize saved state
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

    public void adjustRGB(int index, int delta) {
        rgbVals[index] = Math.max(0, Math.min(255, rgbVals[index] + delta));
    }

    public void saveRGB() {
        savedRGB = rgbVals.clone();
    }

    public void resetRGB() {
        rgbVals = savedRGB.clone();
    }

    @Override
    public String toString() {
        return getColor();
    }
}
