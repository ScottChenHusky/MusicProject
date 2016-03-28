package cs3500.music.view;

import cs3500.music.view.ViewModel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by leyiqiang on 11/23/15.
 */

/**
 * to represent an IndicatorPanel
 */
public class IndicatorPanel extends JPanel {
  ViewModel vm;
  int x;

  /**
   * to construct an IndicatorPanel
   *
   * @param vm the ViewModel which is taken
   * @param x  the x position of this indicator
   */
  public IndicatorPanel(ViewModel vm, int x) {
    this.vm = vm;
    this.x = x;
  }

  /**
   * to paint the red line Indicator
   *
   * @param g the graph g.
   */
  public void paint(Graphics g) {
    if (x + ConcreteGuiViewPanel.moveX > 0 && x + ConcreteGuiViewPanel.moveX < vm.getViewWidth()) {
      g.setColor(Color.red);
      Graphics2D g2d = (Graphics2D) g.create();
      g2d.setStroke(new BasicStroke(2));
      g2d.drawLine(40 + x * 20 + ConcreteGuiViewPanel.moveX * 20, 20,
              40 + x * 20 + ConcreteGuiViewPanel.moveX * 20,
              GuiViewFrame.vm.getViewHeight() * 20 + 20 + ConcreteGuiViewPanel.moveY * 20);
    }
  }
}
