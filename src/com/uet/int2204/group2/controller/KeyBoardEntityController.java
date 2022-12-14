package com.uet.int2204.group2.controller;

import com.uet.int2204.group2.entity.Entity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.util.Collection;

public abstract class KeyBoardEntityController<T extends Entity> 
implements EntityController<T>, EventHandler<KeyEvent> {
  public KeyBoardEntityController() {
  }

  // add this to a collection
  public KeyBoardEntityController(Collection<EventHandler<KeyEvent>> handlerList) {
    this();
    handlerList.add(this);
  }
}
