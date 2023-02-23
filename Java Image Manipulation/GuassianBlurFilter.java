import java.util.concurrent.ExecutionException;

public class GuassianBlurFilter implements Filter{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData(); 
        Pixel[][] old = pi.getData();   
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length ; col++) {
                Pixel g = data[row][col];
                int avgR  = 0, avgG = 0, avgB = 0;
                int val = 4;
                for(int i = 0; i < val; i++){
                    try{
                        avgR += old[row][col+i].getRed();
                        avgB += old[row][col+i].getBlue();
                        avgG += old[row][col+i].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }
                for(int i = 0; i < val; i++){
                    try{
                        avgR += old[row+i][col+val-1].getRed();
                        avgB += old[row+i][col+val-1].getBlue();
                        avgG += old[row+i][col+val-1].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }
                for(int i = val; i > 0; i--){
                    try{
                        avgR += old[row+val-1][col-i].getRed();
                        avgB += old[row+val-1][col-i].getBlue();
                        avgG += old[row+val-1][col-i].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }
                for(int i = val; i > 0; i--){
                    try{
                        avgR += old[row-i][col-val-1].getRed();
                        avgB += old[row-i][col-val-1].getBlue();
                        avgG += old[row-i][col-val-1].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }
                avgR /= val*val;
                avgG /= val*val;
                avgB /= val*val;
                data[row][col].setBlue(avgB);
                data[row][col].setGreen(avgG);
                data[row][col].setRed(avgR);
            }
        }
         pi.setData(data);
     }
}