package tankWarGame.walls;

import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CannotBreakStandardWall extends StandardWall {
    private int x, y;
    private Rectangle r;
    private static BufferedImage wallImg;

    public CannotBreakStandardWall() {}

    public CannotBreakStandardWall(int x, int y){
        this.x = x;
        this.y = y;
        this.r = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public static void setImg(BufferedImage img){
        CannotBreakStandardWall.wallImg = img;
    }


    @Override
    public void checkIfCollided(ObjectCollide c) { }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }

    @Override
    public boolean hasCollided() {
        return false;
    }
}
