public class NegativeImageFilter implements Filter{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();    
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length ; col++) {
                data[row][col].setRed(255 - data[row][col].getRed());
                data[row][col].setGreen(255 - data[row][col].getGreen());
                data[row][col].setBlue(255 - data[row][col].getBlue());
            }
        }
         pi.setData(data);
     }
}
