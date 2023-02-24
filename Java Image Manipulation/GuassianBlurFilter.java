import java.util.concurrent.ExecutionException;

public class GuassianBlurFilter implements Filter{
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData(); 
        Pixel[][] old = pi.getData();  
        int val = 4;
        int[][] weights = {
            {1,2,1},
            {2,4,2},
            {1,2,1}
        }; 
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length ; col++) {
                int avgR = 0, avgG = 0, avgB = 0;
                int weight = 4,rR = 0, gG = 0, bB = 0;
                for(int xR = 0; xR < 6; xR++){
                    boolean goof = true;
                	if(xR < 3){
                		try{
				        rR += old[row-1][col+xR].getRed();
				        bB += old[row-1][col+xR].getBlue();
				        gG += old[row-1][col+xR].getGreen();
				        
                    		}catch(Exception e){
                                goof = false;
                    		}
                    		weight = weights[0][xR];
                	}else{
                            try{
                            rR += old[row+val-1][col-xR-3].getRed();
                            bB += old[row+val-1][col-xR-3].getBlue();
                            gG += old[row+val-1][col-xR-3].getGreen();
                    		}catch(Exception e){
                                goof = false;
			
                    		}
                    		weight = weights[0][xR-3];
                	}
                    if(goof){
                        avgR += rR * weight;
                        avgG += gG * weight;
                        avgB += bB * weight;
                        avgR /= val*val;
                        avgG /= val*val;
                        avgB /= val*val;
                    }
                }
                for(int xR = 0; xR < 6; xR++){
                    boolean goof = true;
                	if(xR < 3){
                		
                		try{
				        rR += old[row-xR][col-val-1].getRed();
				        bB += old[row-xR][col-val-1].getBlue();
				        gG += old[row-xR][col-val-1].getGreen();
				        
                    		}catch(Exception e){
                                goof = false;
                    		}
                    		weight = weights[0][xR];
                	}else{
                		try{
				        rR +=  old[row-xR-3][col-val-1].getRed();
				        bB +=  old[row-xR-3][col-val-1].getBlue();
				        gG +=  old[row-xR-3][col-val-1].getGreen();
                    		}catch(Exception e){
                                goof = false;
                    		}
                    	weight = weights[0][xR-3];
                	}
                    if(goof){
                	avgR += rR * weight;
                	avgG += gG * weight;
                	avgB += bB * weight;
                	avgR /= val*val;
                    avgG /= val*val;
                    avgB /= val*val;
                    }
                }
               
                
                data[row][col].setBlue((int)avgB);
                data[row][col].setGreen((int)avgG);
                data[row][col].setRed((int)avgR);
            }
        }
         pi.setData(data);
     }
}
