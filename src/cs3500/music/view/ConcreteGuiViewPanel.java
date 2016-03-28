package cs3500.music.view;


import cs3500.music.model.Model;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.RepeatSign;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import javax.swing.*;


/**
 * A dummy cs3500.music.view that simply draws a string
 */
public class ConcreteGuiViewPanel extends JPanel {
  public static int moveX;
  public static int moveY;

  /**
   * to construct a ConcreteGuiViewPanel
   *
   * @param moveX the distance moved on the X axis
   * @param moveY the distance moved on the Y axis
   */
  ConcreteGuiViewPanel(int moveX, int moveY) {
    this.moveX = moveX;
    this.moveY = moveY;
  }

  /**
   * to pint the graph
   *
   * @param g is the given graph
   */
  public void paint(Graphics g) {
    this.paintNodes(g);
    g.setColor(Color.black);
    this.paintBG(g);
    this.paintStart(g);
    this.paintTime(g);
    this.paintPitch(g);
    this.paintRepeatSign(g);
    this.paintEndings(g);
  }

  /**
   * to print the background
   *
   * @param g is the given graph
   */
  private final void paintBG(Graphics g) {
    for (int i = 0; i <= GuiViewFrame.vm.getViewHeight(); i++) {
      g.drawLine(40, 20 * (i + 1) + moveY * 20,
              (GuiViewFrame.vm.getViewWidth() * 20) + 40 + moveX * 20,
              20 * (i + 1) + moveY * 20);
    }
    for (int i = 0; i <= GuiViewFrame.vm.getViewWidth(); i = i + 4) {
      if (i * 20 + 40 + moveX * 20 >= 40 &&
              i * 20 + 40 + moveX * 20 <= GuiViewFrame.vm.getViewWidth() * 20) {
        g.drawLine(i * 20 + 40 + moveX * 20, 20, i * 20 + 40 + moveX * 20,
                GuiViewFrame.vm.getViewHeight() * 20 + 20 + moveY * 20);
      }
    }
  }

  /**
   * to paint all the notes
   *
   * @param g is the given graph
   */
  private final void paintNodes(Graphics g) {
    Set<Integer> keys = GuiViewFrame.vm.getViewEditor().keySet();
    for (Integer key : keys) {
      for (Note note : GuiViewFrame.vm.getViewEditor().get(key)) {
        this.paintANote(g, note);
      }
    }
  }


  /**
   * to paint A note
   *
   * @param g    the graph
   * @param note the note needed to draw
   */
  protected final void paintANote(Graphics g, Note note) {
    int x = note.getTime();
    int y = note.getPitch().pitchToInt() - GuiViewFrame.vm.getViewBotPitch().pitchToInt();
    int d = note.getDuration();
    for (int i = 0; i < d; i++) {
      if (i == 0 && (i + x) * 20 + moveX * 20 >= 0 && (i + x) * 20 + moveX * 20
              <= GuiViewFrame.vm.getViewWidth() * 20 &&
              (GuiViewFrame.vm.getViewHeight() - y) + moveY > 0) {
        g.setColor(Color.black);
        g.fillRect(40 + (i + x) * 20 + moveX * 20, 20 +
                (GuiViewFrame.vm.getViewHeight() - 1 - y) * 20 + moveY * 20, 20, 20);
      } else if ((i + x) * 20 + moveX * 20 >= 0 && (i + x) * 20 +
              moveX * 20 <= GuiViewFrame.vm.getViewWidth() * 20 &&
              (GuiViewFrame.vm.getViewHeight() - y) + moveY > 0) {
        g.setColor(Color.green);
        g.fillRect(40 + (i + x) * 20 + moveX * 20, 20 + (GuiViewFrame.vm.getViewHeight() - 1 - y) *
                20 + moveY * 20, 20, 20);
      }
    }
  }


  /**
   * to paint the the middle pitch
   *
   * @param g is the given graph
   */
  private final void paintStart(Graphics g) {
    g.setColor(Color.BLACK);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setStroke(new BasicStroke(3));
    g2d.drawLine(40, 20 + 20 * Model.DEFAULT_HEIGHT / 2 + moveY * 20,
            (GuiViewFrame.vm.getViewWidth() * 20) + 40 +
            moveX * 20, 20 + 20 * Model.DEFAULT_HEIGHT / 2 + moveY * 20);
  }

  /**
   * to paint time line
   *
   * @param g is the given graph
   */
  private final void paintTime(Graphics g) {
    g.setColor(Color.black);
    for (int i = 0; i <= GuiViewFrame.vm.getViewWidth(); i++) {
      if (i % 16 == 0 && i * 20 + 40 + moveX * 20 >= 40 &&
              i * 20 + 40 + moveX * 20 <= GuiViewFrame.vm.getViewWidth() * 20) {
        g.drawString(Integer.toString(i), 35 + i * 20 + moveX * 20, 15);
      }
    }
  }

  /**
   * to paint the pitch
   *
   * @param g is the given graph
   */
  private final void paintPitch(Graphics g) {
    int j = 0;
    int p = GuiViewFrame.vm.getViewBotPitch().pitchToInt();
    for (int i = p; i < p + GuiViewFrame.vm.getViewHeight(); i++) {
      if ((GuiViewFrame.vm.getViewHeight() - j) * 20 + moveY * 20 > 15) {
        String s = Pitch.intToPitch(i - 12).toString();
        g.drawString(s, 5, 15 + (GuiViewFrame.vm.getViewHeight() - j) * 20 + moveY * 20);
        j++;
      }
    }
  }

  private final void paintRepeatSign(Graphics g) {
    ArrayList<RepeatSign> los = GuiViewFrame.vm.getVlos();
    g.setColor(Color.orange);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setStroke(new BasicStroke(5));
    for (RepeatSign sign : los) {
      g2d.drawLine(sign.getSignAt() * 20 + 40 + moveX * 20 + 3, 20 + 1,
              sign.getSignAt() * 20 + 40 + moveX * 20 + 3,
              GuiViewFrame.vm.getViewHeight() * 20 + 20 + moveY * 20 + 1);
      g2d.drawLine(sign.getStartAt() * 20 + 40 + moveX * 20 - 3, 20 + 1,
              sign.getStartAt() * 20 + 40 + moveX * 20 - 3,
              GuiViewFrame.vm.getViewHeight() * 20 + 20 + moveY * 20 + 1);
    }
  }

  private final void paintEndings(Graphics g) {
    g.setColor(Color.blue);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setStroke(new BasicStroke(3));
    ArrayList<Integer> loi = GuiViewFrame.vm.getEnding().getEndings();
    for (int i = 0; i < loi.size() - 1; i++) {
      g2d.drawLine(45 + loi.get(i) * 20 + moveX * 20, moveY * 20 + 18,
              35 + loi.get(i+1) * 20 + moveX * 20,
              moveY * 20 + 18);
      g2d.drawString(Integer.toString(i+1), loi.get(i) * 20 + 40 + (loi.get(i+1)-loi.get(i))*10 +
              moveX * 20, moveY * 20 + 13);
    }
  }

}

