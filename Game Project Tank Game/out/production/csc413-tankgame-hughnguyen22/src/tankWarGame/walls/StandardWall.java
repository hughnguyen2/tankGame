package tankWarGame.walls;

import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class StandardWall implements ObjectCollide {

    public StandardWall(){};

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    public static void setImg(BufferedImage wallImg){ }

    public void drawImage(Graphics2D buffer){ }

    public abstract boolean hasCollided();
}
