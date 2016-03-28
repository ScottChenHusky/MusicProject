package cs3500.music.view;

import cs3500.music.view.View;

/**
 * Created by leyiqiang on 11/22/15.
 */

/**
 * to represent the GuiView interface
 */
public interface GuiView extends View {

  /**
   * to refresh the image after user has changed the image
   */
  void refresh();

  /**
   * move the background to the left
   */
  void moveBGToLeft();

  /**
   * move the background to the right
   */
  void moveBGToRight();

  /**
   * jump to the left most position
   */
  void moveBGToLeftMost();

  /**
   * jump to the right most position
   */
  void moveBGToRightMost();

  /**
   * jump to the top most position
   */
  void moveBGToTopMost();

  /**
   * jump to the lowest position
   */
  void moveBGToBottomMost();

  /**
   * move the background to the top
   */
  void moveBGToTop();

  /**
   * move the background to the bottom
   */
  void moveBGToBottom();

  /**
   * move the background when the red line indicator has reached the right bound
   */
  void moveBGOnTime();
}
