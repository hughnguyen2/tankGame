/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankWarGame;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TankControl implements KeyListener {

    private Tank tankExample;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;
    
    public TankControl(Tank tankExample, int up, int down, int left, int right, int shoot) {
        this.tankExample = tankExample;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyPressed = ke.getKeyCode();
        if (keyPressed == up) {
            this.tankExample.toggleUpPressed();
        }
        if (keyPressed == down) {
            this.tankExample.toggleDownPressed();
        }
        if (keyPressed == left) {
            this.tankExample.toggleLeftPressed();
        }
        if (keyPressed == right) {
            this.tankExample.toggleRightPressed();
        }
        if (keyPressed == shoot) {
            this.tankExample.toggleShootPressed();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int keyReleased = ke.getKeyCode();
        if (keyReleased  == up) {
            this.tankExample.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.tankExample.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.tankExample.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.tankExample.unToggleRightPressed();
        }
        if (keyReleased  == shoot) {
            this.tankExample.unToggleShootPressed();
        }

    }
}
