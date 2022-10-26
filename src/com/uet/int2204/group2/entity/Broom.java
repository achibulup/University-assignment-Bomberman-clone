package com.uet.int2204.group2.entity;

import com.uet.int2204.group2.controller.EntityController;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.graphics.Sprite;
import com.uet.int2204.group2.utils.ResourceManager;

public class Broom extends Enemy {
  public static final double SPEED = 80;

  private Animation animation = new Animation(ResourceManager.broom);
  private EntityController<? super Broom> controller = EntityController.doNothingController;

  public Broom(int tileX, int tileY) {
    super(tileX, tileY);
  }

  public Broom(int tileX, int tileY, EntityController<? super Broom> controller) {
    super(tileX, tileY);
    setController(controller);
  }

  public EntityController<? super Broom> getController() {
    return this.controller;
  }

  public void setController(EntityController<? super Broom> controller) {
    this.controller = controller;
  }

  @Override
  public boolean blockedBy(Class<? extends Tile> tile) {
    return tile != Brick.class && super.blockedBy(tile);
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
    if (isDying()) {
      return;
    }
    this.setDying(true);
    this.animation = new Animation(ResourceManager.broomDie);
    if (getWorld().getPlayer() != null) {
      this.getWorld().getPlayer().increasePoint(120);
    }
  }

  public void control() {
    getController().control(this);
  }

  @Override
  public void update(double dt) {
    if (!isDying()) {
      if (isHalfwayBlocked(getDirection())) {
        setDirection(getDirection().opposite());
      }
      if (isMovable(getDirection())) {
        adjustedMove(getSpeed() * dt);
      }
      if (isAligned()) {
        control();
      }
      this.animation.update(dt);
    } else {
      this.animation.update(dt);
      if (this.animation.isEnded()) {
        markExpired();
      }
    }
  }
}
