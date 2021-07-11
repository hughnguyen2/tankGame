package tankWarGame.powerup;

import tankWarGame.object.ObjectCollide;
import tankWarGame.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class increaseSpeed extends HealthIncrease {

        private int x;
        private int y;
        private Rectangle rectangleExample;
        private static BufferedImage bimage;
        private boolean collided = false;

        public increaseSpeed(int x, int y){

            this.x = x;
            this.y = y;

            this.rectangleExample = new Rectangle(x, y, bimage.getWidth(), bimage.getHeight());
        }

        public static void setImage(BufferedImage bimage){

            increaseSpeed.bimage = bimage;
        }

        @Override
        public void checkIfCollided(ObjectCollide c) {

            if(c instanceof Tank) {

                if(this.getRectangle().intersects(c.getRectangle())){
                    collided = true;
                    ((Tank) c).setSpeed(4);
                }
            }
        }

        public boolean collisionHappened() {
            return collided;
        }

        @Override
        public Rectangle getRectangle() {

            return new Rectangle(x, y, bimage.getWidth(), bimage.getHeight());
        }

        public void drawImage(Graphics2D buffer){

            buffer.drawImage(bimage, x, y, null);
        }

}
