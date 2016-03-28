package cs3500.music.view;

import cs3500.music.model.Model;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.*;

/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent the View interface
 */
public interface View {

  /**
   * to simulate the cs3500.music.model
   *
   * @param type the type that a user want to use
   */
  void simulate(String type) throws IOException, InvalidMidiDataException, MidiUnavailableException;


  /**
   * An adapter, which adapts a Model to a ViewModel
   *
   * @param adaptee
   * @return
   */
  static ViewModel ModelToViewModel(Model adaptee) {
    return new ViewModel(adaptee.getEditor(), adaptee.getCurrentWidth(),
            adaptee.getCurrentHeight(), adaptee.getBotPitch(), adaptee.getCurrentTime(),
            adaptee.getTempo(), adaptee.getState(), adaptee.getLos(), adaptee.getEnding(),
            adaptee.getLeftEndBound()) {
    };
  }

  /**
   * move the cs3500.music.view on time
   *
   * @param time the time of the composition
   */
  void moveOnTime(int time) throws InvalidMidiDataException;


}
