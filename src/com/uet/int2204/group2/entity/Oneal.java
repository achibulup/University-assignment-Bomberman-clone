package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Oneal extends Enemy implements BasicEnemy {
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
  public void control() {
    getController().control(this);
  }

  @Override
  public void update(long dt) {
    BasicEnemy.super.update(dt);
    this.animation.update(dt);
  }

}
