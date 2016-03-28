package cs3500.music.controller;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leyiqiang on 11/22/15.
 */

/**
 * to represent the KeyboardHandler
 */
public final class KeyboardHandler implements KeyListener {
  Controller c;
  private Map<Integer, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;
  protected int counter = 0;
  protected String buffer = "";
  protected static boolean isCPressed = false;
  protected static boolean isRPressed = false;
  protected static boolean isTPressed = false;
  protected static boolean isLPressed = false;
  protected static boolean isEPressed = false;
  protected static boolean isBPressed = false;
  protected static int[] noteFields = new int[]{1, 100, 4, 5, 28};
  protected static ArrayList<Integer> multipleNotes = new ArrayList<>();
  protected static ArrayList<Integer> multipleSign = new ArrayList<>();
  protected static ArrayList<Integer> multipleEnds = new ArrayList<>();


  /**
   * to construct a KeyboardHandler
   *
   * @param c the controller has been taken in
   */
  public KeyboardHandler(Controller c) {
    this.c = c;
    this.typed = new HashMap<>();
    this.pressed = new HashMap<>();
    this.released = new HashMap<>();
    released.put(KeyEvent.VK_SPACE, c.pauseAndPlay());
    pressed.put(KeyEvent.VK_RIGHT, c.moveXToLeft());
    pressed.put(KeyEvent.VK_LEFT, c.moveXToRight());
    pressed.put(KeyEvent.VK_A, c.moveXToLeftMost());
    pressed.put(KeyEvent.VK_D, c.moveXToRightMost());
    pressed.put(KeyEvent.VK_UP, c.moveYToBottom());
    pressed.put(KeyEvent.VK_DOWN, c.moveYToTop());
    pressed.put(KeyEvent.VK_W, c.moveYToTopMost());
    pressed.put(KeyEvent.VK_S, c.moveYToBottomMost());
  }


  /**
   * Invoked when a key has been typed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key typed event.
   *
   * @param e
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (e.getKeyChar() == 'c' || isCPressed) {
      this.checkCTyped(e);
    }
    if (e.getKeyChar() == 'r' || isRPressed) {
      this.checkRTyped(e);
    }
    if (e.getKeyChar() == 't' || isTPressed) {
      this.checkTTyped(e);
    }
    if (e.getKeyChar() == 'l' || isLPressed) {
      this.checkLTyped(e);
    }
    if(e.getKeyChar() == 'e' || isEPressed) {
      this.checkETyped(e);
    }
    if(e.getKeyChar() == 'b' || isBPressed) {
      this.checkBTyped(e);
    }
    if (typed.containsKey(e.getKeyCode())) {
      typed.get(e.getKeyCode()).run();
    }

  }

  /**
   * Invoked when a key has been pressed.
   * See the class description for {@link KeyEvent} for a definition of
   * a key pressed event.
   *
   * @param e
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyChar() == 'c') {
      this.checkCPressed(e);
    }
    if (e.getKeyChar() == 'r') {
      this.checkRPressed(e);
    }
    if (e.getKeyChar() == 't') {
      this.checkTPressed(e);
    }

    if(e.getKeyChar() == 'l') {
      this.checkLPressed(e);
    }
    if(e.getKeyChar() == 'e') {
      this.checkEPressed(e);
    }
    if(e.getKeyChar() == 'b') {
      this.checkBPressed(e);
    }
    if (pressed.containsKey(e.getKeyCode())) {
      pressed.get(e.getKeyCode()).run();
    }

  }

  /**
   * Invoked when a key has been released.
   * See the class description for {@link KeyEvent} for a definition of
   * a key released event.
   *
   * @param e
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (e.getKeyChar() == 'c') {
      this.checkCReleased(e);
    }
    if (e.getKeyChar() == 'r') {
      this.checkRReleased(e);
    }
    if (e.getKeyChar() == 't') {
      this.checkTReleased(e);
    }
    if(e.getKeyChar() == 'l') {
      this.checkLReleased(e);
    }
    if(e.getKeyChar() == 'e') {
      this.checkEReleased(e);
    }

    if(e.getKeyChar() == 'b') {
      this.checkBReleased(e);
    }
    if (released.containsKey(e.getKeyCode())) {
      released.get(e.getKeyCode()).run();
    }
  }


  /////////////////////////////////////////////////////////
  // helper functions for checking particular key states //
  /////////////////////////////////////////////////////////


  // KEY C

  /**
   * to check is the Key C typed
   *
   * @param e the key
   */
  private void checkCTyped(KeyEvent e) {
    if (isCPressed && e.getKeyChar() != 'c' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }

    if (e.getKeyChar() == ',') {
      try {
        noteFields[counter] = Integer.parseInt(buffer);
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }

      System.out.println(noteFields[counter]);
      counter++;
      buffer = "";
    }
  }

  /**
   * to check is the key C pressed
   *
   * @param e the key
   */
  private void checkCPressed(KeyEvent e) {
    if (e.getKeyChar() == 'c') {
      isCPressed = true;
    }
  }

  /**
   * to check is the key C released
   *
   * @param e the key
   */
  private void checkCReleased(KeyEvent e) {
    if (e.getKeyChar() == 'c') {
      isCPressed = false;
      counter = 0;
      buffer = "";
      Note note = new Note(noteFields[2], noteFields[3],
              Pitch.intToPitch(noteFields[4]), noteFields[0],
              noteFields[1]);
      this.released.put(KeyEvent.VK_C, c.createNote(note));

    }
  }


  //Key A

  /**
   * to check is the key R typed
   *
   * @param e the key
   */
  private void checkRTyped(KeyEvent e) {
    if (isRPressed && e.getKeyChar() != 'r' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }

    if (e.getKeyChar() == ',') {
      try {
        multipleNotes.add(Integer.parseInt(buffer));
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }
      counter++;
      buffer = "";
    }
  }

  /**
   * to check is the key R pressed
   *
   * @param e the key
   */
  private void checkRPressed(KeyEvent e) {
    if (e.getKeyChar() == 'r') {
      isRPressed = true;
    }
  }

  /**
   * to check is the key R released
   *
   * @param e the key
   */
  private void checkRReleased(KeyEvent e) {
    if (e.getKeyChar() == 'r') {
      isRPressed = false;
      counter = 0;
      buffer = "";
      this.released.put(KeyEvent.VK_R, c.removeAll(this.multipleNotes));
      this.multipleNotes = new ArrayList<Integer>();
    }
  }

  //Key T

  /**
   * to check is the key T typed
   *
   * @param e the key
   */
  private void checkTTyped(KeyEvent e) {
    if (isTPressed && e.getKeyChar() != 't' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }
    if (e.getKeyChar() == ',') {
      try {
        multipleNotes.add(Integer.parseInt(buffer));
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }
      counter++;
      buffer = "";
    }
  }

  /**
   * to check is the key T pressed
   *
   * @param e the key
   */
  private void checkTPressed(KeyEvent e) {
    if (e.getKeyChar() == 't') {
      isTPressed = true;
    }
  }

  /**
   * to check is the key T released
   *
   * @param e the key
   */
  private void checkTReleased(KeyEvent e) {
    if (e.getKeyChar() == 't') {
      isTPressed = false;
      System.out.println(buffer);
      this.released.put(KeyEvent.VK_T, c.changeTime(Integer.parseInt(buffer)));
      buffer = "";
    }
  }


  // Key L
  private void checkLTyped(KeyEvent e) {
    if (isLPressed && e.getKeyChar() != 'l' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }

    if (e.getKeyChar() == ',') {
      try {
        multipleSign.add(Integer.parseInt(buffer));
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }
      counter++;
      buffer = "";
    }
  }

  private void checkLPressed(KeyEvent e) {
    if(e.getKeyChar() == 'l') {
      isLPressed = true;
    }
  }

  private void checkLReleased(KeyEvent e) {
    if (e.getKeyChar() == 'l') {
      isLPressed = false;
      counter = 0;
      buffer = "";
      this.released.put(KeyEvent.VK_L, c.addRepeat(this.multipleSign));
      this.multipleSign = new ArrayList<Integer>();
    }

  }



  // Key E
  private void checkETyped(KeyEvent e) {
    if (isEPressed && e.getKeyChar() != 'e' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }

    if (e.getKeyChar() == ',') {
      try {
        multipleEnds.add(Integer.parseInt(buffer));
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }
      counter++;
      buffer = "";
    }
  }

  private void checkEPressed(KeyEvent e) {
    if(e.getKeyChar() == 'e') {
      isEPressed = true;
    }
  }

  private void checkEReleased(KeyEvent e) {
    if (e.getKeyChar() == 'e') {
      isEPressed = false;
      counter = 0;
      buffer = "";
      this.released.put(KeyEvent.VK_E, c.addEnds(this.multipleEnds));
      this.multipleEnds = new ArrayList<Integer>();
    }

  }



  //Key B

  /**
   * to check is the key B typed
   *
   * @param e the key
   */
  private void checkBTyped(KeyEvent e) {
    if (isBPressed && e.getKeyChar() != 'b' && e.getKeyChar() != ',') {
      buffer += Character.getNumericValue(e.getKeyChar());
    }
    if (e.getKeyChar() == ',') {
      try {
        multipleNotes.add(Integer.parseInt(buffer));
      } catch (IndexOutOfBoundsException exception) {
        System.out.println("To much information");
      }
      counter++;
      buffer = "";
    }
  }

  /**
   * to check is the key B pressed
   *
   * @param e the key
   */
  private void checkBPressed(KeyEvent e) {
    if (e.getKeyChar() == 'b') {
      isBPressed = true;
    }
  }

  /**
   * to check is the key B released
   *
   * @param e the key
   */
  private void checkBReleased(KeyEvent e) {
    if (e.getKeyChar() == 'b') {
      isBPressed = false;
      System.out.println(buffer);
      this.released.put(KeyEvent.VK_B, c.changeLeftBound(Integer.parseInt(buffer)));
      buffer = "";
    }
  }

}
