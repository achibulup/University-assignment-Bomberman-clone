package com.uet.int2204.group2.entity.tile;

import com.uet.int2204.group2.entity.Entity;
import com.uet.int2204.group2.entity.movable.MovableEntity;
import com.uet.int2204.group2.utils.Constants;

// static entities are tied to a tile.
public abstract class Tile extends Entity {
  private int tileX;
  private int tileY;

  public Tile() {
  }

  public Tile(int x, int y) {
    this.tileX = x;
    this.tileY = y;
  }

  @Override
  public double getPixelX() {
    return getTileX() * Constants.TILE_SIZE;
  }

  @Override
  public double getPixelY() {
    return getTileY() * Constants.TILE_SIZE;
  }

  @Override
  public int getTileX() {
    return this.tileX;
  }

  @Override
  public int getTileY() {
    return this.tileY;
  }

  public void setTileX(int tileX) {
    this.tileX = tileX;
  }

  public void setTileY(int tileY) {
    this.tileY = tileY;
  }

  public Runnable getInteractionToEntity(MovableEntity entity) {
    return null;
  }
}
