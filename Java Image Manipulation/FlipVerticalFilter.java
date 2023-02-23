public class FlipVerticalFilter implements Filter
{
   public void filter(PixelImage pi) {
       Pixel[][] data = pi.getData();    
        // [row][col]
        for(int row = 0; row < data.length/2; row++){
            Pixel[] temp = data[row];
            data[row] = data[data.length - row - 1]; 
            data[data.length - row - 1] = temp;

        }
        pi.setData(data);
    }
}
