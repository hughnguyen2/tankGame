package tankWarGame.Tank;

import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Bullet implements ObjectCollide {
    private static BufferedImage bulletImage;
    private BufferedImage explosion;
    private final int R = 5;
    private int x, y, angle, vx, vy;
    private Rectangle rectangleExample;
    private boolean collided = false;
    int waitTime = 0;


    public Bullet(int x, int y, int angle){
        this.x = x;
        this.y = y;
        this.vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        this.vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        this.angle =  angle;
        this.rectangleExample = new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight());
    }

    public void drawImage(Graphics2D buffer){
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth() / 2.0, this.bulletImage.getHeight() / 2.0);
        buffer.drawImage(bulletImage, x, y, null);

    }

    public static void setImage(BufferedImage img){

        bulletImage = img;
    }

    public boolean hasCollided() {

        return collided;
    }

    @Override
    public void checkIfCollided(ObjectCollide c) {
        if(this.getRectangle().intersects(c.getRectangle())){
            collided = true;
        }
    }

    public void update(){
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }


    @Override
    public Rectangle getRectangle() {
        return new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight());
    }
}
