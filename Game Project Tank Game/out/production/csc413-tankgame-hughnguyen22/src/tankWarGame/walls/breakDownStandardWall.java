package tankWarGame.walls;

import Bullet;
import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.image.BufferedImage;

public class breakDownStandardWall extends StandardWall {
    private int x, y;
    private Rectangle r;
    private static BufferedImage wallImg;
    private boolean collided = false;

    public breakDownStandardWall(int x, int y){
        this.x = x;
        this.y = y;
        this.r = new Rectangle(x, y, wallImg.getWidth(), wallImg.getHeight());
    }

    public static void setImg(BufferedImage img){
        wallImg = img;
    }

    @Override
    public void checkIfCollided(ObjectCollide c) {
        if(c instanceof Bullet){
            if(this.getRectangle().intersects(c.getRectangle())){
                collided = true;
            }
        }
    }

    public boolean hasCollided() {
        return collided;
    }

    @Override
    public Rectangle getRectangle() {
        return r;
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(wallImg, x, y, null);
    }
}
