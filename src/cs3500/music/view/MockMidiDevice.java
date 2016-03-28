package cs3500.music.view;

import cs3500.music.view.ViewModel;

import javax.sound.midi.*;
import java.util.List;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;
/**
 * Created by leyiqiang on 11/22/15.
 */

/**
 * to represent the MockMidiDevice
 */
public class MockMidiDevice implements Synthesizer {
  StringBuilder output;
  int startTime = 0;
  ViewModel vm;

  /**
   * to construct a MockMidiDevice
   *
   * @param vm
   */
  MockMidiDevice(ViewModel vm) {
    this.output = new StringBuilder("");
    this.vm = vm;
  }

  @Override
  public int getMaxPolyphony() {
    return 0;
  }

  @Override
  public long getLatency() {
    return 0;
  }

  @Override
  public MidiChannel[] getChannels() {
    return new MidiChannel[0];
  }

  @Override
  public VoiceStatus[] getVoiceStatus() {
    return new VoiceStatus[0];
  }

  @Override
  public boolean isSoundbankSupported(Soundbank soundbank) {
    return false;
  }

  @Override
  public boolean loadInstrument(Instrument instrument) {
    return false;
  }

  @Override
  public void unloadInstrument(Instrument instrument) {

  }

  @Override
  public boolean remapInstrument(Instrument from, Instrument to) {
    return false;
  }

  @Override
  public Soundbank getDefaultSoundbank() {
    return null;
  }

  @Override
  public Instrument[] getAvailableInstruments() {
    return new Instrument[0];
  }

  @Override
  public Instrument[] getLoadedInstruments() {
    return new Instrument[0];
  }

  @Override
  public boolean loadAllInstruments(Soundbank soundbank) {
    return false;
  }

  @Override
  public void unloadAllInstruments(Soundbank soundbank) {

  }

  @Override
  public boolean loadInstruments(Soundbank soundbank, Patch[] patchList) {
    return false;
  }

  @Override
  public void unloadInstruments(Soundbank soundbank, Patch[] patchList) {

  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {

  }

  @Override
  public void close() {

  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    output.append("tempo " + Long.toString(vm.getViewTempo()) + "\n");
    return new Receiver() {
      @Override
      public void send(MidiMessage message, long timeStamp) {
        ShortMessage sm = (ShortMessage) message;
        if (sm.getCommand() == ShortMessage.NOTE_ON) {
          startTime = ((int) timeStamp) / 200000;
        }
        if (sm.getCommand() == ShortMessage.NOTE_OFF) {

          sm.getChannel(); // channel + 1
          sm.getData1(); // pitch + 12
          sm.getData2(); // volume
          output.append("note " + startTime + " " +
                  (((int) timeStamp) / 200000) + " " +
                  (sm.getChannel() + 1) + " " +
                  (sm.getData1() + 12) + " " + sm.getData2() + "\n");
        }
      }

      @Override
      public void close() {

      }
    };
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
