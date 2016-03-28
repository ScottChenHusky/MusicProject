package cs3500.music.controller;

import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.GuiViewFrame;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by leyiqiang on 11/22/15.
 */

/**
 * to represent the MouseHandler
 */
public class MouseHandler implements MouseListener {
  private Controller c;
  private boolean isRightButtonPressed = false;
  private int x;
  private int y;

  /**
   * to construct a MouseHandler
   *
   * @param c the controller which is taken in
   */
  public MouseHandler(Controller c) {
    this.c = c;
    this.x = 0;
    this.y = 0;
  }

  /**
   * to convert the X position on the screen to its coordinate time position in the music editor
   *
   * @param x the X position on the screen
   * @return
   */
  public int convertX(int x) {
    return (x - x % 20 - 40) / 20;
  }

  /**
   * to convert the Y position on the screen to its coordinate Pitch in the music editor
   *
   * @param y the Y position on the screen
   * @return
   */
  public int convertY(int y) {
    int pitch = -((y - 20 - ConcreteGuiViewPanel.moveY * 20) / 20 -
            GuiViewFrame.vm.getViewHeight() + 1 -
            GuiViewFrame.vm.getViewBotPitch().pitchToInt()) + 1;
    return pitch - ConcreteGuiViewPanel.moveY;

  }

  /**
   * Invoked when the mouse button has been clicked (pressed
   * and released) on a component.
   *
   * @param e
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    if (KeyboardHandler.isCPressed) {
      KeyboardHandler.noteFields[3] = this.convertX(e.getX()) - ConcreteGuiViewPanel.moveX;
      KeyboardHandler.noteFields[4] = this.convertY(e.getY()) + ConcreteGuiViewPanel.moveY;
    }
    if (KeyboardHandler.isRPressed) {
      int idx = KeyboardHandler.multipleNotes.size();
      KeyboardHandler.multipleNotes.add(idx, this.convertX(e.getX()) - ConcreteGuiViewPanel.moveX);
      KeyboardHandler.multipleNotes.add(idx + 1,
              this.convertY(e.getY()) + ConcreteGuiViewPanel.moveY);
    }

  }

  /**
   * Invoked when a mouse button has been pressed on a component.
   *
   * @param e
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.getButton() == 3) {
      isRightButtonPressed = true;
      x = this.convertX(e.getX()) - ConcreteGuiViewPanel.moveX;
      y = this.convertY(e.getY()) + ConcreteGuiViewPanel.moveY;
    }
  }

  /**
   * Invoked when a mouse button has been released on a component.
   *
   * @param e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.getButton() == 3) {
      isRightButtonPressed = false;
      c.moveANote(x, y, this.convertX(e.getX()) - ConcreteGuiViewPanel.moveX,
              this.convertY(e.getY()) + ConcreteGuiViewPanel.moveY);
    }
  }

  /**
   * Invoked when the mouse enters a component.
   *
   * @param e
   */
  @Override
  public void mouseEntered(MouseEvent e) {

  }

  /**
   * Invoked when the mouse exits a component.
   *
   * @param e
   */
  @Override
  public void mouseExited(MouseEvent e) {

  }
}
