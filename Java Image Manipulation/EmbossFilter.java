import java.util.concurrent.ExecutionException;

public class EmbossFilter implements Filter{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData(); 
        Pixel[][] old = pi.getData();  
        int val = 4;
        int[][] weights = {
                {-1-1,0},
                {-1,0,-1},
                {0, 1, 1}
            }; 
        for (int row = val; row < data.length-val; row++) {
            for (int col = val; col < data[row].length-val; col++) {
                int avgR = 0, avgG = 0, avgB = 0;
                int weight = 4,rR = 0, gG = 0, bB = 0;
                boolean goof = false;
                // TOP ROW
                for(int i = 0; i < 3; i++){
                    rR += old[row-1][col-1+i].getRed() * weights[0][i];
                    bB += old[row-1][col-1+i].getBlue()* weights[0][i];
                    gG += old[row-1][col-1+i].getGreen()* weights[0][i];
                    
                }
                // BOT ROW
                for(int i = 0; i < 3; i++){
                    rR += old[row+val-1][col-1+i].getRed()* weights[0][i];
                    bB += old[row+val-1][col-1+i].getBlue()* weights[0][i];
                    gG += old[row+val-1][col-1+i].getGreen()* weights[0][i];}

            // LEFT COL
            for(int i = 0; i < 3; i++){
                rR += old[row-1-i][col-1].getRed()* weights[i][0];
                bB += old[row-1-i][col-1].getBlue()* weights[i][0];
                gG += old[row-1-i][col-1].getGreen()* weights[i][0];
            }
            // RIGHT COL
            for(int i = 0; i < 3; i++){
                rR += old[row-1-i][col+val-1].getRed()* weights[i][0];
                bB += old[row-1-i][col+val-1].getBlue()* weights[i][0];
                gG += old[row-1-i][col+val-1].getGreen()* weights[i][0];
            }
           
            avgR = rR;
            avgB = bB;
            avgG = gG;
            avgR /= val*val;
            avgG /= val*val; 
            avgB /= val*val;
            //if(goof){
                data[row][col].setBlue((int)avgB);
                data[row][col].setGreen((int)avgG);
                data[row][col].setRed((int)avgR);
            //}
        }

       
    }
    pi.setData(data);
}
}
