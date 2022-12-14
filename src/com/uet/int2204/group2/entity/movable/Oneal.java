package com.uet.int2204.group2.entity.movable;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Oneal extends Enemy implements SimpleEnemy {
  public static final double SPEED = 60;

  private Animation animation = new Animation(ResourceManager.oneal);
  private EntityController<? super Oneal> controller = EntityController.doNothingController;

  public Oneal(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Oneal(int tileX, int tileY, EntityController<? super Oneal> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Oneal> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Oneal> controller) {
    this.controller = controller;
  }

  @Override
  public Sprite getSprite() {
    return this.animation.currentSprite();
  }

  @Override
  public double getSpeed() {
    return SPEED;
  }

  @Override
  public void getHit() {
    if (!this.isDying() && getWorld().getPlayer() != null) {
      this.getWorld().getPlayer().increasePoint(180);
    }
    SimpleEnemy.super.getHit();
  }

  @Override 
  public Animation getAnimation() {
    return this.animation;
  }

  @Override
  public void setDyingAnimation() {
    this.animation = new Animation(ResourceManager.onealDie);
  }

  public void control() {
    getController().control(this);
  }

  @Override
  public void update(double dt) {
    SimpleEnemy.super.update(dt);
  }
}
