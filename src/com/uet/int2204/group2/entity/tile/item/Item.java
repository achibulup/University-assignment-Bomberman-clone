package com.uet.int2204.group2.entity.tile.item;

import com.uet.int2204.group2.entity.movable.Player;
import com.uet.int2204.group2.entity.tile.DestroyableTile;
import com.uet.int2204.group2.entity.tile.Tile;
import com.uet.int2204.group2.graphics.Animation;
import com.uet.int2204.group2.utils.Constants;
import com.uet.int2204.group2.utils.ResourceManager;
import javafx.scene.canvas.GraphicsContext;

public abstract class Item extends Tile implements DestroyableTile {
  protected Animation explosionAnimation = null;

  public Item(){
  }

  public Item(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override
  public void update(double dt) {
    if (beingDestroyed()) {
      this.explosionAnimation.update(dt);
      if (this.explosionAnimation.isEnded()) {
        markExpired();
      }
    }
  }

  @Override
  public void renderTo(GraphicsContext target) {
    getSprite().drawTo(target, getPixelX(), getPixelY());
    if (beingDestroyed()) {
      this.explosionAnimation.currentSprite().drawTo(
          target, getPixelX(), getPixelY(), Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
  }

  @Override
  public void destroy() {
    if (!beingDestroyed()) {
      this.explosionAnimation = new Animation(ResourceManager.itemExplosion);
    }
  }

  public abstract void onCollect(Player player);

  public boolean beingDestroyed() {
    return this.explosionAnimation != null;
  }
}
