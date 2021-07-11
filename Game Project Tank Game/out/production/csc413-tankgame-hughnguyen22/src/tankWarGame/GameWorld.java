package tankWarGame;

import tankWarGame.map.GameMap;
import tankWarGame.powerup.Life;
import tankWarGame.powerup.increaseSpeed;
import tankWarGame.walls.CannotBreakStandardWall;
import tankWarGame.walls.breakDownStandardWall;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class GameWorld extends JPanel  {

    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 960;
    public static final int WORLD_WIDTH = 1600;
    public static final int WORLD_HEIGHT = 1200;
    private static boolean gameOver = false;
    private static Life life;

    private BufferedImage world;
    private BufferedImage player1wins;
    private BufferedImage player2wins;
    private tankWarGame.Background backgroundImage;
    private Graphics2D buffer;
    private JFrame jf;
    private Tank tank1;
    private Tank tank2;
    private GameMap map;


    public static void main(String[] args) {
        Thread x;
        GameWorld gameTestRun = new GameWorld();
        gameTestRun.initializeGame();
        try {

            while (!gameOver) {
                gameTestRun.repaint();

                Thread.sleep(1000 / 144);
            }

        } catch (InterruptedException ignored) {

        }
    }

    private void initializeGame() {
        this.jf = new JFrame("Tank Wars");

        this.world = new BufferedImage(GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage t1image=null;
        BufferedImage t2image=null;

        try {
            BufferedImage temp;

            t1image = ImageIO.read(getClass().getResource("/resources/tank1.png"));

            t2image = ImageIO.read(getClass().getResource("/resources/tank2.png"));


            backgroundImage = new Background(ImageIO.read(getClass().getResource("/resources/Background.bmp")));

            Bullet.setImage(ImageIO.read(getClass().getResource("/resources/Weapon.gif")));

            Life.setImage(ImageIO.read(getClass().getResource("/resources/extraLife.png")));

            increaseSpeed.setImage(ImageIO.read(getClass().getResource("/resources/speed-boost.png")));

            CannotBreakStandardWall.setImg(ImageIO.read(getClass().getResource("/resources/Wall1.gif")));

            breakDownStandardWall.setImg(ImageIO.read(getClass().getResource("/resources/Wall2.gif")));


            player1wins = ImageIO.read(getClass().getResource("/resources/player-1-wins.png"));

            player2wins = ImageIO.read(getClass().getResource("/resources/player-2-wins.png"));


        } catch (IOException e) {

            System.out.println(e.getMessage());
        }

        tank1 = new Tank(200, 200, 0, 0, 0, t1image);

        tank2 = new Tank(1200, 400, 0, 0, 180, t2image);

        map = new GameMap();

        life = new Life(0, 0);

        TankControl controlSettingTank1 = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        TankControl controlSettingTank2 = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        this.jf.setLayout(new BorderLayout());
        this.jf.add(this);


        this.jf.addKeyListener(controlSettingTank1);
        this.jf.addKeyListener(controlSettingTank2);

        this.jf.setSize(SCREEN_WIDTH, SCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setVisible(true);

    }

    private void update(){
        tank1.update();
        tank2.update();

        tank1.checkIfCollided(tank2);
        tank2.checkIfCollided(tank1);
        map.handleCollision(tank1);
        map.handleCollision(tank2);

    }


    private int getCoordinateX(Tank t){
        int x = t.getX();
        if(x < SCREEN_WIDTH / 4)
            x = SCREEN_WIDTH / 4;
        if(x > WORLD_WIDTH - SCREEN_WIDTH / 4)
            x = WORLD_WIDTH - SCREEN_WIDTH / 4;
        return x;
    }


    // y coordinate
    public int getCoordinateY(Tank t){
        int y = t.getY();
        if(y < SCREEN_HEIGHT / 2)
            y = SCREEN_HEIGHT / 2;
        if(y > WORLD_HEIGHT - SCREEN_HEIGHT / 2)
            y = WORLD_HEIGHT - SCREEN_HEIGHT / 2;
        return y;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        update();

        buffer = world.createGraphics();

        super.paintComponent(g2);

        this.setBackground(Color.black);

        this.backgroundImage.drawImage(buffer);

        map.drawImage(buffer);

        this.tank1.drawImage(buffer);
        this.tank2.drawImage(buffer);

        BufferedImage leftScreen = world.getSubimage(getCoordinateX(tank1) - SCREEN_WIDTH / 4, getCoordinateY(tank1) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
        BufferedImage rightScreen = world.getSubimage(getCoordinateX(tank2) - SCREEN_WIDTH / 4, getCoordinateY(tank2) - SCREEN_HEIGHT / 2, SCREEN_WIDTH / 2, SCREEN_HEIGHT);

        g2.drawImage(leftScreen, 0, 0, null);
        g2.drawImage(rightScreen, SCREEN_WIDTH / 2 + 10, 0, null);

        int location;
        int placement;
        for(int i = 1; i <= tank1.getLives(); i++){
            location = (life.getImage().getWidth() + 40) * i;
            placement = location / 2;
            g2.drawImage(life.getImage(), placement, 10, null);
        }

        for(int i = 1; i <= tank2.getLives(); i++){
            location = (life.getImage().getWidth() + 40) * i;
            placement = location/2 + SCREEN_WIDTH - SCREEN_WIDTH / 2 + 10;
            g2.drawImage(life.getImage(), placement, 10, null);
        }

        g2.setColor(Color.RED);
        g2.fillRect(SCREEN_WIDTH / 4, 30, 2* tank1.getCurrentHealth(), 20);
        g2.fillRect(SCREEN_WIDTH - SCREEN_WIDTH / 4, 30, 2* tank2.getCurrentHealth(), 20);

        //minimap
        g2.drawImage(world, SCREEN_WIDTH / 2 - WORLD_WIDTH / 10,
                SCREEN_HEIGHT - WORLD_HEIGHT / 5,
                WORLD_WIDTH / 5,
                WORLD_HEIGHT / 5,
                null);

        if(tank1.getLives() == 0){
            g2.drawImage(player2wins, 100, 100, SCREEN_WIDTH + 10, SCREEN_HEIGHT, null);
            gameOver = true;
        }

        if(tank2.getLives() == 0) {
            g2.drawImage(player1wins, 100, 100, SCREEN_WIDTH + 10, SCREEN_HEIGHT, null);
            gameOver = true;
        }

    }


}
