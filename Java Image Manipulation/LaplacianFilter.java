public class LaplacianFilter implements Filter {
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] old = pi.getData();
        int val = 4;
        int[][] weights = {
                { -1, -1, -1 },
                { -1, 8, -1 },
                { -1, -1, -1 }
        };
        // int[][] weights = {
        // {1,1,1},
        // {1,1,1},
        // {1,1,1}
        // };
        for (int row = val; row < data.length - val; row++) {
            for (int col = val; col < data[row].length - val; col++) {
                int avgR = 0, avgG = 0, avgB = 0;
                int rR = 0, gG = 0, bB = 0;
                int cA = 0;     // old[row][col].getRed() * 8;
                int cB = 0;     // old[row][col].getBlue() * 8;
                int cG = 0;     // old[row][col].getGreen() * 8;
                rR += old[row][col].getRed() * 8;
                bB += old[row][col].getBlue() * 8;
                gG += old[row][col].getGreen() * 8;
                // TOP ROW
                for (int i = 0; i < 3; i++) {
                    rR += old[row - 1][col - 1 + i].getRed() - cA * -1;
                    bB += old[row - 1][col - 1 + i].getBlue() - cB * -1;
                    gG += old[row - 1][col - 1 + i].getGreen() - cG * -1;

                }
                // BOT ROW
                for (int i = 0; i < 3; i++) {
                    rR += old[row + val - 1][col - 1 + i].getRed() * -1;
                    bB += old[row + val - 1][col - 1 + i].getBlue() * -1;
                    gG += old[row + val - 1][col - 1 + i].getGreen() * -1;
                }

                // LEFT COL
                for (int i = 0; i < 3; i++) {
                    rR += old[row - 1 - i][col - 1].getRed() * -1;
                    bB += old[row - 1 - i][col - 1].getBlue() * -1;
                    gG += old[row - 1 - i][col - 1].getGreen() * -1;
                }
                // RIGHT COL
                for (int i = 0; i < 3; i++) {
                    rR += old[row - 1 - i][col + val - 1].getRed() * -1;
                    bB += old[row - 1 - i][col + val - 1].getBlue() * -1;
                    gG += old[row - 1 - i][col + val - 1].getGreen() * -1;
                }

                avgG = gG;
                avgB = bB;
                avgR = rR;
                // avgR = rR /16;
                // avgB = bB /16;
                // avgG = gG /16;
                if (avgG > 255)
                    avgG = 255;
                if (avgB > 255)
                    avgB = 255;
                if (avgR > 255)
                    avgR = 255;
                if (avgG < 0)
                    avgG = 0;
                if (avgB < 0)
                    avgB = 0;
                if (avgR < 0)
                    avgR = 0;

                data[row][col].setBlue((int) avgB);
                data[row][col].setGreen((int) avgG);
                data[row][col].setRed((int) avgR);
            }

        }
        pi.setData(data);
    }
}
