package com.uet.int2204.group2.map;

public interface SingleUseWorldTrigger extends WorldTrigger {
  @Override
  default boolean isDone() {
    return true;
  }
}
