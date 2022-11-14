package com.hao.gameObjects;

import com.hao.effect.CacheDataLoader;
import com.hao.userinterface.GameFrame;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameWorld {
    
    private BufferedImage bufferedImage;
    private int lastState;
    
    public Megaman megaman;
    public PhysicalMap physicalMap;
    public BackgroundMap backgroundMap;
    public BulletManager bulletManager;
    public ParticularObjectManager particularObjectManager;
    public Camera camera;
    public BlueFire blueFire;
    
    private int numberOfLife = 3;
    
    public GameWorld(){
        
        megaman = new Megaman(450, 300, this);
        megaman.setTeamType(ParticularObject.LEAGUE_TEAM);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT, this);
        
        bulletManager = new BulletManager(this);
        
        particularObjectManager = new ParticularObjectManager(this);
        particularObjectManager.addObject(megaman);
        
        initEnemies();
    }
    
    private void initEnemies(){
        ParticularObject redeye = new RedEyeEnermy(1100, 340, this);
        redeye.setDirection(ParticularObject.LEFT_DIR);
        redeye.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(redeye);
        
        ParticularObject redeye1 = new RedEyeEnermy(170, 420, this);
        redeye1.setDirection(ParticularObject.LEFT_DIR);
        redeye1.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(redeye1);
        
        ParticularObject redeye2 = new RedEyeEnermy(2900, 430, this);
        redeye2.setDirection(ParticularObject.LEFT_DIR);
        redeye2.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(redeye2);
        
        ParticularObject redeye22 = new RedEyeEnermy(3300, 430, this);
        redeye22.setDirection(ParticularObject.LEFT_DIR);
        redeye22.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(redeye22);
        
        ParticularObject boss = new FinalBoss(4100, 360, this);
        boss.setTeamType(ParticularObject.ENEMY_TEAM);
        boss.setDirection(ParticularObject.LEFT_DIR);
        particularObjectManager.addObject(boss);
        
        ParticularObject boss1 = new FinalBoss(1900, 1150, this);
        boss1.setTeamType(ParticularObject.ENEMY_TEAM);
        boss1.setDirection(ParticularObject.LEFT_DIR);
        particularObjectManager.addObject(boss1);
        
        ParticularObject robotR = new RobotR(900, 300, this);
        robotR.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR);
        
        ParticularObject robotR2 = new RobotR(3400, 250, this);
        robotR2.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR2);
        
        ParticularObject robotR22 = new RobotR(3300, 250, this);
        robotR22.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR22);
        
        ParticularObject robotR3 = new RobotR(1450, 1000, this);
        robotR3.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR3);
        
        ParticularObject robotR4 = new RobotR(1250, 1000, this);
        robotR4.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR4);
        
        ParticularObject robotR5 = new RobotR(1750, 1000, this);
        robotR5.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR5);
        
        ParticularObject robotR6 = new RobotR(2150, 1000, this);
        robotR6.setTeamType(ParticularObject.ENEMY_TEAM);
        particularObjectManager.addObject(robotR6);
    }
    
    public void Update(){
        particularObjectManager.UpdateObjects();
        camera.Update();
        
        bulletManager.UpdateObjects();
        
        if(megaman.getState() == ParticularObject.DEATH){
            numberOfLife --;
            if(numberOfLife >= 0){
                megaman.setBlood(100);
                megaman.setPosY(megaman.getPosY() - 50);
                megaman.setState(ParticularObject.NOBEHURT);
                particularObjectManager.addObject(megaman);
            }
        }
        
    }
    
    public void Render(Graphics2D g2){
        
//        physicalMap.draw(g2);
        backgroundMap.draw(g2);
        particularObjectManager.draw(g2);
        bulletManager.draw(g2);
        
        g2.setColor(Color.GRAY);
        g2.fillRect(19, 59, 102, 22);
        g2.setColor(Color.red);
        g2.fillRect(20, 60, megaman.getBlood(), 20);

        for(int i = 0; i < numberOfLife; i++){
            g2.drawImage(CacheDataLoader.getInstance().getFrameImage("hearth").getImage(), 20 + i*40, 18, null);
        }
        if(particularObjectManager.getSize() == 1){
            g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin").getImage(), 300, 300, null);
        }
    }
    
}
