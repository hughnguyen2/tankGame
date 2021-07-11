package tankWarGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends JPanel {
    private BufferedImage background;

    public Background(BufferedImage bimage) {

        initializeBackgroundDesign(bimage);
    }

    private void initializeBackgroundDesign(BufferedImage bimage) {
        loadImage(bimage);

        int width = bimage.getWidth();
        int height = bimage.getHeight();
        setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(BufferedImage bimage) {

        this.background = bimage;
    }

    void drawImage(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        for(int i = 0; i < GameWorld.WORLD_WIDTH; i+=background.getWidth()){

            for(int j = 0; j < GameWorld.WORLD_HEIGHT; j+=background.getHeight()){

                g2d.drawImage(this.background, i,j, null);
            }
        }
    }
}
