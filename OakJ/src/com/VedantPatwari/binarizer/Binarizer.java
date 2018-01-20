package com.VedantPatwari.binarizer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Binarizer {

    private static BufferedImage original, grayscale, binarized;

    public static void main(String[] args) throws IOException {
        File original_f = new File(args[0]+ ".jpg");
        String output_f = args[0]+"_bin";
        original = ImageIO.read(original_f);
        grayscale = toGray(original);
    }

    private static BufferedImage toGray(BufferedImage original){
        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for(int i = 0; i < original.getWidth(); i ++){
            for(int j = 0; j < original.getHeight(); j++){
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);

                newPixel = colorToRGB(alpha, red, red, red);

                lum.setRGB(i, j, newPixel);
            }
        }

        return lum;
    }

    private static int colorToRGB(int alpha, int red, int green, int blue){
        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;
    }


}
