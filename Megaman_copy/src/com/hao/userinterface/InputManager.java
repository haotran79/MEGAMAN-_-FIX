package com.hao.userinterface;

import com.hao.gameObjects.GameWorld;
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
                gameWorld.megaman.setDirection(gameWorld.megaman.LEFT_DIR);
                gameWorld.megaman.run();
                break;
            case KeyEvent.VK_RIGHT:
                gameWorld.megaman.setDirection(gameWorld.megaman.RIGHT_DIR);
                gameWorld.megaman.run();
                break;
            case KeyEvent.VK_ENTER:
                
                break;
            case KeyEvent.VK_SPACE:
                gameWorld.megaman.jump();
                break;
            case KeyEvent.VK_G:
                gameWorld.megaman.attack2(15, 0);
                break;
            case KeyEvent.VK_H:
                gameWorld.megaman.attack2(15, -6.5f);
                break;
            case KeyEvent.VK_T:
                gameWorld.megaman.attack2(0, -29.5f);
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
