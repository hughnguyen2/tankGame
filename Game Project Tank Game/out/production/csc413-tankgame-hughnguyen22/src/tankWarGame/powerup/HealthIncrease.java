package tankWarGame.powerup;

import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class HealthIncrease implements ObjectCollide {


    @Override
    public void checkIfCollided(ObjectCollide c) {

    }

    @Override
    public Rectangle getRectangle() {
        return null;
    }

    public static void setImage(BufferedImage powerUp){

    }

    public void drawImage(Graphics2D buffer){

    }

    public abstract boolean collisionHappened();
}
