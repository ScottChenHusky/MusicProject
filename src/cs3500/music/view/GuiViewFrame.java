package cs3500.music.view;

import cs3500.music.view.ViewModel;

import java.awt.*;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends javax.swing.JFrame implements GuiView {
  private final ConcreteGuiViewPanel backgroundPanel;
  private IndicatorPanel indicatorPanel;
  public static ViewModel vm;
  public int currPosition;


  /**
   * to construct the GuiViewFrame
   *
   * @param vm the ViewModel need to take in
   */
  public GuiViewFrame(ViewModel vm) {
    this.backgroundPanel = new ConcreteGuiViewPanel(0, 0);
    this.indicatorPanel = new IndicatorPanel(vm, 0);
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.getContentPane().add(indicatorPanel, 0);
    this.getContentPane().add(backgroundPanel, BorderLayout.CENTER);
    this.pack();
    this.vm = vm;
    this.currPosition = vm.getViewPosition();
  }

  /**
   * to initialize the board
   */
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public void simulate(String type) throws IOException,
          InvalidMidiDataException, MidiUnavailableException {
    this.initialize();

    if (type.equals("midi")) {
      MidiViewImpl mv = new MidiViewImpl(vm);
    }
  }

  /**
   * move the cs3500.music.view on time
   *
   * @param time the time of the composition
   */
  @Override
  public void moveOnTime(int time) {
    this.getContentPane().remove(this.indicatorPanel);
    this.indicatorPanel = new IndicatorPanel(GuiViewFrame.vm, time);
    this.getContentPane().add(this.indicatorPanel, 0);
    this.refresh();
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1300, 500);
  }


  @Override
  public void refresh() {
    this.getContentPane().validate();
    this.repaint();
  }

  @Override
  public void moveBGToLeft() {
    backgroundPanel.moveX--;
    this.refresh();
  }

  @Override
  public void moveBGToRight() {
    backgroundPanel.moveX++;
    this.refresh();
  }

  @Override
  public void moveBGToLeftMost() {
    backgroundPanel.moveX = 0;
    this.refresh();
  }

  @Override
  public void moveBGToRightMost() {
    backgroundPanel.moveX = 16 - vm.getViewWidth();
    this.refresh();

  }

  @Override
  public void moveBGToTopMost() {
    backgroundPanel.moveY = 0;
    this.refresh();
  }

  @Override
  public void moveBGToBottomMost() {
    backgroundPanel.moveY = 10 - vm.getViewHeight();
    this.refresh();
  }

  @Override
  public void moveBGToTop() {
    backgroundPanel.moveY++;
    this.refresh();
  }

  @Override
  public void moveBGToBottom() {
    backgroundPanel.moveY--;
    this.refresh();

  }

  @Override
  public void moveBGOnTime() {
    backgroundPanel.moveX--;
    this.refresh();
  }
}
