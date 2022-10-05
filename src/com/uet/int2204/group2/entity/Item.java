package com.uet.int2204.group2.entity;

public abstract class Item extends Tile implements DestroyableTile {
  public Item(int tileX, int tileY) {
    super(tileX, tileY);
  }

  @Override 
  public void destroy() {
    markExpired();
  }

  public abstract void onCollect(Player player);
}
