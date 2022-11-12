package com.hao.userinterface;

import com.hao.gameObjects.GameWorld;
import com.hao.gameObjects.Megaman;
import java.awt.event.KeyEvent;

/**
 *
 * @author David Tran
 */
public class InputManager {
    
    private GameWorld gameWorld;
    
    public InputManager(GameWorld gameWorld){
        this.gameWorld = gameWorld;
    }
    
    public void processKeyPressed(int keyCode){
        switch (keyCode) {
            
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.megaman.dick();
                break;
            case KeyEvent.VK_LEFT:
                gameWorld.megaman.setDirection(Megaman.LEFT_DIR);
                gameWorld.megaman.run();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(Megaman.RIGHT_DIR);
                gameWorld.megaman.run();
                break;
            case KeyEvent.VK_ENTER:
                
                break;
            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;
            case KeyEvent.VK_G:
                gameWorld.megaman.attack();
                break;
            case KeyEvent.VK_H:
                gameWorld.megaman.attack1();
                break;
        }
    }
    
    public void processKeyReleased(int keyCode){
        switch (keyCode) {
            
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                gameWorld.megaman.standUp();
                break;
            case KeyEvent.VK_LEFT:
                if(gameWorld.megaman.getSpeedX() < 0)
                    gameWorld.megaman.stopRun();
                break;
            case KeyEvent.VK_RIGHT:
                if(gameWorld.megaman.getSpeedX() > 0)
                    gameWorld.megaman.stopRun();
                break;
            case KeyEvent.VK_ENTER:
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_G:
//                break;
        }
    }
    
}
