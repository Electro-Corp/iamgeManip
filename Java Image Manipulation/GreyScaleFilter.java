public class GreyScaleFilter implements Filter{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();    
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length ; col++) {
                int avg = (data[row][col].getRed() + data[row][col].getBlue() + data[row][col].getGreen()) /3;
                data[row][col].setRed(avg);
                data[row][col].setGreen(avg);
                data[row][col].setBlue(avg);
            }
        }
         pi.setData(data);
     }
}
