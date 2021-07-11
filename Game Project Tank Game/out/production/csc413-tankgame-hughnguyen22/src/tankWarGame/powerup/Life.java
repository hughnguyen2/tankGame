package tankWarGame.powerup;

import tankWarGame.object.ObjectCollide;
import tankWarGame.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Life extends HealthIncrease {
    private int x;
    private int y;
    private Rectangle rectangleExample;
    private static BufferedImage bimage;
    private boolean collided = false;

    public Life(int x, int y){
        this.x = x;
        this.y = y;
        this.rectangleExample = new Rectangle(x, y, bimage.getWidth(), bimage.getHeight());
    }

    public static BufferedImage getImage() {
        return bimage;
    }

    public static void setImage(BufferedImage img){

        Life.bimage = img;
    }


    @Override
    public void checkIfCollided(ObjectCollide object1c) {
        if(object1c instanceof Tank){
            if(this.getRectangle().intersects(object1c.getRectangle())){
                collided = true;
                ((Tank) object1c).addLife();
                ((Tank) object1c).powerHealth(100);
            }
        }
    }

    public boolean collisionHappened() { return collided; }


    @Override
    public Rectangle getRectangle() {

        return new Rectangle(x, y, bimage.getWidth(), bimage.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        buffer.drawImage(bimage, x, y, null);
    }

}
