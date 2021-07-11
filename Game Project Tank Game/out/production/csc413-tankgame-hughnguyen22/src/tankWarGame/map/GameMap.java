package tankWarGame.map;

import tankWarGame.object.ObjectCollide;
import tankWarGame.GameWorld;
import tankWarGame.powerup.Life;
import tankWarGame.powerup.HealthIncrease;
import tankWarGame.powerup.increaseSpeed;
import tankWarGame.walls.breakDownStandardWall;
import tankWarGame.walls.CannotBreakStandardWall;
import tankWarGame.walls.StandardWall;

import java.awt.*;
import java.util.ArrayList;

public class GameMap {

    private ArrayList<StandardWall> standardWalls = new ArrayList<>();
    private ArrayList<HealthIncrease> healthIncreases = new ArrayList<>();
    int width = GameWorld.WORLD_WIDTH;
    int height = GameWorld.WORLD_HEIGHT;

    public GameMap(){


        //placement for ubreakable walls
        for(int i = 0; i < width; i += 32){

            standardWalls.add(new CannotBreakStandardWall(i, 0));
            standardWalls.add(new CannotBreakStandardWall(i, height - 32));
        }

        //placement for ubreakable walls
        for(int i = 32; i < height; i += 32){

            standardWalls.add(new CannotBreakStandardWall(width - 32, i));
            standardWalls.add(new CannotBreakStandardWall(0, i));
        }

        //placement for breakable walls
        for(int i = width / 10; i < width; i += 192){

            for(int j = height / 4; j < height - 192; j += 192){
                standardWalls.add(new breakDownStandardWall(i, j));
            }
        }


        //placement for breakable walls
        for(int i = 64; i < height - 64; i += 32){

            standardWalls.add(new breakDownStandardWall(width / 2 + 32, i));
        }
        //extra life hearts placement
        healthIncreases.add(new Life(150, 250));
        healthIncreases.add(new Life(1100, 600));
        healthIncreases.add(new Life(1200, 600));

        //increase speed placement
        healthIncreases.add(new increaseSpeed(200, 1000));
        healthIncreases.add(new increaseSpeed(800, 150));
    }

    public void handleCollision(ObjectCollide c) {

        for(int i = 0; i < standardWalls.size(); i++){

            c.checkIfCollided(standardWalls.get(i));
            standardWalls.get(i).checkIfCollided(c);
            if(standardWalls.get(i).hasCollided()){

                standardWalls.remove(i);
            }
        }
        for(int i = 0; i < healthIncreases.size(); i++){

            healthIncreases.get(i).checkIfCollided(c);
            if(healthIncreases.get(i).collisionHappened()){

                healthIncreases.remove(i);
            }
        }
    }

    public void drawImage(Graphics g) {
        Graphics2D g2g = (Graphics2D) g;
        for(int i = 0; i < standardWalls.size(); i++) {

            standardWalls.get(i).drawImage(g2g);
        }
        for(int i = 0; i < healthIncreases.size(); i++){

            healthIncreases.get(i).drawImage(g2g);
        }
    }
}

