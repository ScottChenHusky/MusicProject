//package cs3500.music.controller;
//
//import cs3500.music.model.Model;
//import org.junit.Test;
//
//import java.awt.event.KeyEvent;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by leyiqiang on 11/25/15.
// */
//public class TestKeyBoardHandler {
//  Model model = Model.builder().build();
//  Controller c = new Controller(model);
//  KeyboardHandler kbh = new KeyboardHandler(c);
//
//
//  @Test
//  public void testMoveBG() {
//    KeyEvent upReleased = new KeyEvent(c.gv, 402, 0, 0, 38, (char) 38);
//    KeyEvent downReleased = new KeyEvent(c.gv, 402, 0, 0, 40, (char) 40);
//    KeyEvent leftReleased = new KeyEvent(c.gv, 402, 0, 0, 37, (char) 37);
//    KeyEvent rightReleased = new KeyEvent(c.gv, 402, 0, 0, 39, (char) 39);
//
//    assertEquals(c.output.toString(), "");
//    kbh.keyPressed(upReleased);
//    assertEquals(c.output.toString(), "Key up run");
//    kbh.keyPressed(downReleased);
//    assertEquals(c.output.toString(), "Key down run");
//    kbh.keyPressed(leftReleased);
//    assertEquals(c.output.toString(), "Key left run");
//    kbh.keyPressed(rightReleased);
//    assertEquals(c.output.toString(), "Key right run");
//
//  }
//
//
//  @Test
//  public void testJumpBG() {
//    KeyEvent wReleased = new KeyEvent(c.gv, 402, 0, 0, 87, (char) 87);
//    KeyEvent aReleased = new KeyEvent(c.gv, 402, 0, 0, 65, (char) 65);
//    KeyEvent sReleased = new KeyEvent(c.gv, 402, 0, 0, 83, (char) 83);
//    KeyEvent dReleased = new KeyEvent(c.gv, 402, 0, 0, 68, (char) 68);
//
//    assertEquals(c.output.toString(), "");
//    kbh.keyPressed(wReleased);
//    assertEquals(c.output.toString(), "Key w run");
//    kbh.keyPressed(aReleased);
//    assertEquals(c.output.toString(), "Key a run");
//    kbh.keyPressed(sReleased);
//    assertEquals(c.output.toString(), "Key s run");
//    kbh.keyPressed(dReleased);
//    assertEquals(c.output.toString(), "Key d run");
//  }
//
//
//  @Test
//  public void testCreateNote() {
//    KeyEvent cReleased = new KeyEvent(c.gv, 402, 0, 0, 67, 'c');
//
//    kbh.keyReleased(cReleased);
//    assertEquals(c.output.toString(), "Key c run");
//  }
//
//  @Test
//  public void testRemoveNote() {
//    KeyEvent rReleased = new KeyEvent(c.gv, 402, 0, 0, 82, 'r');
//
//    kbh.keyReleased(rReleased);
//    assertEquals(c.output.toString(), "Key r run");
//  }
//
//  @Test
//  public void testPauseAndPlay() {
//    KeyEvent spaceReleased = new KeyEvent(c.gv, 402, 0, 0, 32, ' ');
//    kbh.keyReleased(spaceReleased);
//    assertEquals(c.output.toString(), "Key space run");
//
//  }
//}
