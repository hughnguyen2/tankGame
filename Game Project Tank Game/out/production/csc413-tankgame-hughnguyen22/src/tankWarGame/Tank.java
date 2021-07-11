package tankWarGame;


import tankWarGame.object.ObjectCollide;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Tank implements ObjectCollide {

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private Rectangle rectangleExample;

    private int R = 5;
    private final int ROTATIONSPEED = 4;
    private int currentHealth = 100;
    private int lives = 3;

    private BufferedImage img;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;
    private ArrayList<Bullet> numberOfBullets = new ArrayList<>();


    Tank(int x, int y, int vx, int vy, int angle, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.img = img;
        this.angle = angle;
        this.rectangleExample = new Rectangle(x, y, img.getWidth(), img.getHeight());

    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setSpeed(int value) { this.R+=value; }

    public void collision(int value){

        if(currentHealth - value <= 0){
            currentHealth = 0;
            removeLife();
        }
        else
            currentHealth -= value;
    }

    public void setImage(BufferedImage img) {
        this.img = img;
    }

    public void powerHealth(int value){
        if(currentHealth + value >= 100)
            currentHealth = 100;
        else
            currentHealth += value;
    }

    public int getCurrentHealth() { return currentHealth; }


    public void addLife() { this.lives += 1; }

    public int getLives() { return this.lives; }

    public void removeLife() {
        if(lives == 0){
            currentHealth = 0;
        } else {
            lives -= 1;
            powerHealth(100);
        }
    }

    void toggleUpPressed() {

        this.UpPressed = true;
    }

    void toggleDownPressed() {

        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {

        this.LeftPressed = true;
    }

    void toggleShootPressed() {

        this.ShootPressed = true;
    }

    void unToggleUpPressed() {

        this.UpPressed = false;
    }

    void unToggleDownPressed() {

        this.DownPressed = false;
    }

    void unToggleRightPressed() {

        this.RightPressed = false;
    }

    void unToggleLeftPressed() {

        this.LeftPressed = false;
    }

    void unToggleShootPressed() {

        this.ShootPressed = false;
    }



    public void update() {
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed) {
            this.shootBullet();
            ShootPressed = false;
        }
        for(int i = 0; i < numberOfBullets.size(); i++){
            if(numberOfBullets.get(i).hasCollided()) {
                numberOfBullets.remove(i);
            }else{
                numberOfBullets.get(i).update();
            }
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;

        checkBorder();
    }

    private void shootBullet() {
            Bullet b = new Bullet(this.x, this.y, this.angle);
            numberOfBullets.add(b);
    }

    @Override
    public void checkIfCollided(ObjectCollide c) {
        if(this.getRectangle().intersects(c.getRectangle())){
            if(c instanceof Bullet){
                collision(10);
            } else {
                Rectangle intersection = this.getRectangle().intersection(c.getRectangle());
                if(intersection.height > intersection.width  && this.x < intersection.x){ //left
                    x-= intersection.width/2;
                }
                else if(intersection.height > intersection.width  && this.x > c.getRectangle().x){ //right
                    x+= intersection.width/2;
                }
                else if(intersection.height < intersection.width  && this.y < intersection.y){ //up
                    y-= intersection.height/2;
                }
                else if(intersection.height < intersection.width  && this.y > c.getRectangle().y){ //down
                    y+= intersection.height/2;
                }
            }
        }
        for(Bullet b : numberOfBullets){
            b.checkIfCollided(c);
            c.checkIfCollided(b);
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(this.x, this.y, img.getWidth(), img.getHeight());
    }


    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameWorld.WORLD_WIDTH - 88) {
            x = GameWorld.WORLD_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameWorld.WORLD_HEIGHT - 80) {
            y = GameWorld.WORLD_HEIGHT - 80;
        }
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    void drawImage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for(Bullet b : numberOfBullets){
            b.drawImage(g2d);
        }
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        g2d.drawImage(this.img, rotation, null);
    }

}
