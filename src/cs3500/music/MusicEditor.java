package cs3500.music;

import cs3500.music.view.ConsoleView;
import cs3500.music.view.View;
import cs3500.music.view.ViewModel;
import cs3500.music.model.Model;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.controller.Controller;
import cs3500.music.controller.KeyboardHandler;
import cs3500.music.controller.MouseHandler;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * the main class
 */
public class MusicEditor {

  /**
   * to Build the cs3500.music.view
   *
   * @param type     what type of cs3500.music.view: "midi", "cs3500.music.console", "visual"
   * @param fileName what is the name of file
   * @throws IOException
   * @throws MidiUnavailableException
   * @throws InvalidMidiDataException
   */
  static void BuildView(String type, String fileName) throws IOException, MidiUnavailableException,
          InvalidMidiDataException {
    // read file
    CompositionBuilder<Model> cb = new MusicModel.Builder();
    //File file = new File(fileName);
    //String path = file.getAbsolutePath();
    BufferedReader br = new BufferedReader(new FileReader(
            new File("/Users/ScottChen/Desktop/Work/GitHub/MusicEditor/mary-little-lamb.txt")));
    Model mm = MusicReader.parseFile(br, cb);
//    mm.addRepeatSign(12, 16);
//    mm.addRepeatSign(8, 24);
//    mm.setLeftEndBound(8);
//    mm.addEndings(12,16,20,24);

    // adapt cs3500.music.model to cs3500.music.view cs3500.music.model
    ViewModel vm = View.ModelToViewModel(mm);
    // create a controller
    Controller c = new Controller(mm);
    // add customized keyboard handler
    KeyListener kl = new KeyboardHandler(c);
    MouseListener ml = new MouseHandler(c);
    c.addKeyListener(kl);
    c.addMouseListener(ml);
    if (type.equals("console")) {
      View v1 = ConsoleView.builder().setViewModel(vm).build();
      v1.simulate(type);
    } else if (type.equals("visual")) {
      c.gv.simulate(type);
    } else if (type.equals("midi")) {
      c.gv.simulate(type);
    } else {
      throw new IllegalArgumentException("Illegal Type");
    }
  }

  public static void main(String[] args) throws IOException,
          InvalidMidiDataException, MidiUnavailableException {
    BuildView("midi", "mary-little-lamb");
  }
}