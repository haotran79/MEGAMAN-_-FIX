package com.hao.userinterface;

import com.hao.effect.CacheDataLoader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author David Tran
 */
public class GameFrame extends JFrame{
    
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 500;
    
    GamePanel gamePanel;

    public GameFrame(){
        
        Toolkit toolkit = this.getToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.setBounds((dimension.width - SCREEN_WIDTH)/2, (dimension.height - SCREEN_HEIGHT)/2, SCREEN_WIDTH, SCREEN_HEIGHT);
        // dung de man hinh game hien thi ơ vi tri trung tâm
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // load data
        try {
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        gamePanel = new GamePanel();
        add(gamePanel);
        
        this.addKeyListener(gamePanel);
        
    }
    
    public void startGame(){
        gamePanel.startGame();
    }
    
    public static void main(String[] args) {
        
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true); // dung de hien thi frame cua game
        gameFrame.startGame();
        
    }
}
