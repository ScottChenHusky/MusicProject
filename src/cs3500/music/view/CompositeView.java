package cs3500.music.view;

import cs3500.music.model.Model;
import cs3500.music.model.RepeatSign;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by leyiqiang on 11/23/15.
 */

/**
 * to represent the CompositeView
 */
public class CompositeView {
  private GuiViewFrame gvf;
  private MidiViewImpl mvi;
  private int currPosition;
  private Model.Status state;
  private Timer timer;
  private  int index;

  /**
   * to construct a CompositeView
   *
   * @param gvf GuiView of this CompositeView
   * @param mvi MidiView of this CompositeView
   */
  public CompositeView(GuiViewFrame gvf, MidiViewImpl mvi) {
    this.gvf = gvf;
    this.mvi = mvi;
    this.currPosition = GuiViewFrame.vm.getViewPosition();
    this.state = GuiViewFrame.vm.getState();
    int delay = (int) GuiViewFrame.vm.getViewTempo() / 1000;
    this.index = GuiViewFrame.vm.getEnding().getAtEnding();
    this.timer = new Timer(delay, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          if (state != Model.Status.STOPPED) {
            gvf.moveOnTime(currPosition);
            mvi.moveOnTime(currPosition);
          }
        } catch (InvalidMidiDataException e1) {
          e1.printStackTrace();
        }
        if (state == Model.Status.PLAYING) {
          ArrayList<Integer> loi = GuiViewFrame.vm.getEnding().getEndings();
          if (loi.size() > 0) {
            int endBoundLeft = loi.get(0);
            if (currPosition+1 == endBoundLeft) {
              if (index >= 0) {
                currPosition = loi.get(index) -1;
                index++;
              }
            }
          }
          if (GuiViewFrame.vm.getVlos().size() != 0) {
            for (RepeatSign sign : GuiViewFrame.vm.getVlos()) {
              if (sign.getSignAt() == currPosition + 1 && !sign.isUsed()) {
                currPosition = sign.getStartAt();
                sign.passed();
              }
            }
          }
          currPosition++;
        }
        if (currPosition * 20 >= 1000) {
          gvf.moveBGOnTime();
        }
        if (currPosition >= GuiViewFrame.vm.getViewWidth()) {
          gvf.moveOnTime(currPosition);
          currPosition = 0;
          state = Model.Status.STOPPED;
          for (RepeatSign sign : GuiViewFrame.vm.getVlos()) {
            sign.passed();
          }
          index = 0;
        }
      }
    });
  }

  /**
   * to play or to pause the music
   */
  public void pauseAndPlay() {
    if (state == Model.Status.PAUSED) {
      state = Model.Status.PLAYING;
      timer.start();
    } else if (state == Model.Status.PLAYING) {
      state = Model.Status.PAUSED;
      timer.stop();
    } else if (state == Model.Status.STOPPED) {
      timer.restart();
      state = Model.Status.PLAYING;
    }
  }

  /**
   * to change the the current time
   *
   * @param time
   */
  public void changeCurrentTime(int time) {
    this.currPosition = time;
  }

}
