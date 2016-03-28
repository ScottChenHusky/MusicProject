package cs3500.music.view;

import cs3500.music.view.View;
import cs3500.music.view.ViewModel;
import cs3500.music.model.Note;

import java.io.IOException;
import java.util.Set;
import javax.sound.midi.*;


/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements View {
  private final ViewModel vm;
  private final Synthesizer synth;
  public final Receiver receiver;

  /**
   * to construct a MidiViewImpl
   *
   * @param vm the ViewModel which is taken
   */
  public MidiViewImpl(ViewModel vm) {
    Synthesizer s = null;
    Receiver r = null;
    try {
      s = MidiSystem.getSynthesizer();
      r = s.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = s;
    try {
      this.synth.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.receiver = r;
    this.vm = vm;
  }


  /**
   * constructor of a MidiViewImple
   *
   * @param vm
   * @param synth
   */
  public MidiViewImpl(ViewModel vm, Synthesizer synth) {
    this.vm = vm;
    this.synth = synth;
    Receiver r = null;
    try {
      r = synth.getReceiver();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.receiver = r;
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library:
   * <ul>
   * <li>{@link MidiSystem#getSynthesizer()}</li>
   * <li>{@link Synthesizer}
   * <ul>
   * <li>{@link Synthesizer#open()}</li>
   * <li>{@link Synthesizer#getReceiver()}</li>
   * <li>{@link Synthesizer#getChannels()}</li>
   * </ul>
   * </li>
   * <li>{@link Receiver}
   * <ul>
   * <li>{@link Receiver#send(MidiMessage, long)}</li>
   * <li>{@link Receiver#close()}</li>
   * </ul>
   * </li>
   * <li>{@link MidiMessage}</li>
   * <li>{@link ShortMessage}</li>
   * <li>{@link MidiChannel}
   * <ul>
   * <li>{@link MidiChannel#getProgram()}</li>
   * <li>{@link MidiChannel#programChange(int)}</li>
   * </ul>
   * </li>
   * </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   * https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  @Override
  public void simulate(String type) throws IOException, InvalidMidiDataException,
          MidiUnavailableException {
    Set<Integer> keys = vm.getViewEditor().keySet();
    for (Integer key : keys) {
      if (vm.getViewEditor().containsKey(key)) {
        for (Note note : this.vm.getViewEditor().get(key)) {
          int midiPitchNum = note.getPitch().pitchToInt() + 12;
          MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                  midiPitchNum, note.getVolume());
          MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                  midiPitchNum, note.getVolume());
          this.receiver.send(start, key * vm.getViewTempo());
          this.receiver.send(stop, key * vm.getViewTempo() +
                  vm.getViewTempo() * note.getDuration());
        }
      }
    }
    this.receiver.close();
  }


  @Override
  public void moveOnTime(int time) throws InvalidMidiDataException {
    if (GuiViewFrame.vm.getViewEditor().containsKey(time)) {
      for (Note note : GuiViewFrame.vm.getViewEditor().get(time)) {
        int midiPitchNum = note.getPitch().pitchToInt() + 12;
        MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument(),
                midiPitchNum, note.getVolume());
        this.receiver.send(start, time * GuiViewFrame.vm.getViewTempo());
        MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument(),
                midiPitchNum, note.getVolume());
        this.receiver.send(stop, time * GuiViewFrame.vm.getViewTempo() +
                GuiViewFrame.vm.getViewTempo()
                * note.getDuration() * 1000);
      }
    }
  }
}
