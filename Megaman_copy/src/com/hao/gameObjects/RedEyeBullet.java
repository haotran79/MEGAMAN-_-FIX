package com.hao.gameObjects;

import com.hao.effect.Animation;
import com.hao.effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RedEyeBullet extends Bullet{
    private Animation forwardBulletAnim, backBulletAnim;
    
    public RedEyeBullet(float x, float y, GameWorld gameWorld){
        super(x, y, 25, 25, 1.0f, 10, gameWorld);
        forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
        backBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
        backBulletAnim.flipAllImage();
        
        setSpeedY(1);
    }

    @Override
    public void draw(Graphics2D g2) {
        if(getSpeedX() > 0){
            forwardBulletAnim.Update(System.nanoTime());
            forwardBulletAnim.draw((int)(getPosX() - getGameWorld().camera.getPosX()), (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
        } else {
            backBulletAnim.Update(System.nanoTime());
            backBulletAnim.draw((int)(getPosX() - getGameWorld().camera.getPosX()), (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
        }
    }
    
    @Override
    public void Update(){
        super.Update();
        
    }

    @Override
    public void attack() {
        
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }
}
