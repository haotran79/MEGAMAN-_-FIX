package com.hao.gameObjects;

import com.hao.effect.Animation;
import com.hao.effect.CacheDataLoader;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Hashtable;

public class FinalBoss extends Human{
    
    private Animation idleforward, idleback;
    private Animation shootingforward, shootingback;
    private Animation slideforward, slideback;
    
    private long startTimeForAttacked;
    
    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>();
    private String[] attackType = new String[4];
    private int attackIndex = 0;
    private long lastAttackTime;
    
    private AudioClip shooting;
    
    private float X0 = getPosX();
    
    public FinalBoss(float x, float y, GameWorld gameWorld){
        super(x, y, 110, 150, 0.1f, 200, gameWorld);
        idleback = CacheDataLoader.getInstance().getAnimation("boss_idle");
        idleforward = CacheDataLoader.getInstance().getAnimation("boss_idle");
        idleforward.flipAllImage();
        
        shootingback = CacheDataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward = CacheDataLoader.getInstance().getAnimation("boss_shooting");
        shootingforward.flipAllImage();
        
        slideback = CacheDataLoader.getInstance().getAnimation("boss_slide");
        slideforward = CacheDataLoader.getInstance().getAnimation("boss_slide");
        slideforward.flipAllImage();
        
        setTimeForNoBeHurt(500*1000000);
        setDamage(10);
        
        attackType[0] = "NONE";
        attackType[1] = "shooting";
        attackType[2] = "NONE";
        attackType[3] = "slide";
        
        timeAttack.put("NONE", new Long(2000));
        timeAttack.put("shooting", new Long(500));
        timeAttack.put("slide", new Long(5000));
        
        shooting = CacheDataLoader.getInstance().getSound("rocketsound");
    }
    
    public void Update(){
        super.Update();
        
        // dung de cho con boss luong huong ve con nhan vat chinh
        if(getGameWorld().megaman.getPosX() > getPosX())
            setDirection(RIGHT_DIR);
        else setDirection(LEFT_DIR);
        
        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 200){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }
        
        if(!attackType[attackIndex].equals("NONE")){
            if(attackType[attackIndex].equals("shooting")){
                
                Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameWorld());
                if(getDirection() == LEFT_DIR) bullet.setSpeedX(-4);
                else bullet.setSpeedX(4);
                setSpeedY(8);
                bullet.setTeamType(getTeamType());
                getGameWorld().bulletManager.addObject(bullet);
                
            }else if(attackType[attackIndex].equals("slide")){
                // cho truong hop con boss cham tuong roi quay dau
                if(getPosX() == X0 - 320) setSpeedX(5);
                if(getPosX() == X0+320) setSpeedX(-5);
                 
                setPosX(getPosX() + getSpeedX());
            }
        }else{
            // stop attack
            setSpeedX(0);
        }
    }

    @Override
    public void run() {
        }

    @Override
    public void jump() {
        }

    @Override
    public void dick() {
        }

    @Override
    public void standUp() {
        }

    @Override
    public void stopRun() {
        }

    @Override
    public void attack() {
        
        if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])){
            lastAttackTime = System.currentTimeMillis();
            
            attackIndex ++;
            if(attackIndex >= attackType.length) attackIndex = 0;
            
            if(attackType[attackIndex].equals("slide")){
                if(getPosX() < getGameWorld().megaman.getPosX()) setSpeedX(5);
                else setSpeedX(-5);
            }
            if(attackType[attackIndex].equals("shooting")){
                shooting.play();
            }
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        if(attackType[attackIndex].equals("slide")){
            Rectangle rect = getBoundForCollisionWithMap();
            rect.y += 100;
            rect.height -= 100;
            return rect;
        }else
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
        {
//            System.out.println("Plash...");
        }else{
            
            if(attackType[attackIndex].equals("NONE")){
                if(getDirection() == RIGHT_DIR){
                    idleforward.Update(System.nanoTime());
                    idleforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                    idleback.Update(System.nanoTime());
                    idleback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
            }else if(attackType[attackIndex].equals("shooting")){
                
                if(getDirection() == RIGHT_DIR){
                    shootingforward.Update(System.nanoTime());
                    shootingforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                    shootingback.Update(System.nanoTime());
                    shootingback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
                
            }else if(attackType[attackIndex].equals("slide")){
                if(getSpeedX() > 0){
                    slideforward.Update(System.nanoTime());
                    slideforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 50, g2);
                }else{
                    slideback.Update(System.nanoTime());
                    slideback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 50, g2);
                }
            }
        }
    }
    
}
