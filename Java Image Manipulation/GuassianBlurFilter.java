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
                int[][] weights = {
                	{1,2,1},
                	{2,4,2},
                	{1,2,1}
                };
                // X (+ and -)
                int weight = 4,rR = 0, gG = 0, bB = 0;
                for(int xR = 0; xR < 6; xR++){
                	
                	if(xR < 3){
                		
                		try{
				        rR += old[row][col+xR].getRed();
				        bB += old[row][col+xR].getBlue();
				        gG += old[row][col+xR].getGreen();
				        
                    		}catch(Exception e){
				        rR += old[row][col].getRed();
				        bB += old[row][col].getBlue();
				        gG += old[row][col].getGreen();
                    		}
                    		weight = weights[0][xR];
                	}else{
                		try{
				        rR += old[row+val-1][col-xR-3].getRed();
				        bB += old[row+val-1][col-xR-3].getBlue();
				        gG += old[row+val-1][col-xR-3].getGreen();
                    		}catch(Exception e){
				        rR += old[row][col].getRed();
				        bB += old[row][col].getBlue();
				        gG += old[row][col].getGreen();
                    		}
                    		weight = weights[0][xR-3];
                	}
                	avgR += rR * weight;
                	avgG += gG * weight;
                	avgB += bB * weight;
                	avgR /= val*val;
                	avgG /= val*val;
               	 	avgB /= val*val;
                }
                for(int xR = 0; xR < 6; xR++){
                	if(xR < 3){
                		
                		try{
				        rR += old[row-xR][col-val-1].getRed();
				        bB += old[row-xR][col-val-1].getBlue();
				        gG += old[row-xR][col-val-1].getGreen();
				        
                    		}catch(Exception e){
				        rR += old[row][col].getRed();
				        bB += old[row][col].getBlue();
				        gG += old[row][col].getGreen();
                    		}
                    		weight = weights[0][xR];
                	}else{
                		try{
				        rR +=  old[row-xR-3][col-val-1].getRed();
				        bB +=  old[row-xR-3][col-val-1].getBlue();
				        gG +=  old[row-xR-3][col-val-1].getGreen();
                    		}catch(Exception e){
				        rR += old[row][col].getRed();
				        bB += old[row][col].getBlue();
				        gG += old[row][col].getGreen();
                    		}
                    		weight = weights[0][xR-3];
                	}
                	avgR += rR * weight;
                	avgG += gG * weight;
                	avgB += bB * weight;
                	avgR /= val*val;
		        avgG /= val*val;
		        avgB /= val*val;
                }
                /*for(int i = 0; i < val; i++){
                    try{
                        avgR += old[row][col+i].getRed();
                        avgB += old[row][col+i].getBlue();
                        avgG += old[row][col+i].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }*/
                /*for(int i = 0; i < val; i++){
                    try{
                        avgR += old[row+i][col+val-1].getRed();
                        avgB += old[row+i][col+val-1].getBlue();
                        avgG += old[row+i][col+val-1].getGreen();
                    }catch(Exception e){
                        avgR += old[row][col].getRed();
                        avgB += old[row][col].getBlue();
                        avgG += old[row][col].getGreen();
                    }
                }*/
               /* for(int i = val; i > 0; i--){
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
                */
                /*
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
                */
                
                data[row][col].setBlue(avgB);
                data[row][col].setGreen(avgG);
                data[row][col].setRed(avgR);
            }
        }
         pi.setData(data);
     }
}
