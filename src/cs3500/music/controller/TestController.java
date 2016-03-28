//package cs3500.music.controller;
//
//import cs3500.music.model.Model;
//import cs3500.music.model.Note;
//import cs3500.music.model.Pitch;
//import cs3500.music.view.ConcreteGuiViewPanel;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by leyiqiang on 11/25/15.
// */
//public class TestController {
//  Model model = Model.builder().build();
//  Controller c = new Controller(model);
//  Note noteF = new Note(3, 0, new Pitch("F", 2), 1, 1);
//  Note noteE = new Note(3, 0, new Pitch("E", 2), 1, 1);
//
//  @Test
//  public void testCreateNote() {
//    assertEquals(0, model.getEditor().keySet().size());
//    c.createNote(noteE).run();
//    assertEquals(1, model.getEditor().keySet().size());
//
//    assertEquals("E2", model.getEditor().get(0).get(0).getPitch().toString());
//    assertEquals(16, model.getCurrentHeight());
//  }
//
//  @Test
//  public void testPauseAndPlay() {
//    assertEquals(model.getState(), Model.Status.STOPPED);
//    c.pauseAndPlay().run();
//    assertEquals(model.getState(), Model.Status.PAUSED);
//
//  }
//
//  @Test
//  public void testRemoveAll() {
//    assertEquals(0, model.getEditor().keySet().size());
//    model.addNote(noteE);
//    model.addNote(noteF);
//    ArrayList<Integer> loi = new ArrayList<Integer>();
//    loi.add(0);
//    loi.add(28);
//    loi.add(0);
//    loi.add(29);
//    assertEquals(2, model.getEditor().get(0).size());
//    c.removeAll(loi).run();
//    assertEquals(0, model.getEditor().get(0).size());
//  }
//
//  @Test
//  public void testMoveOnX() {
//    Assert.assertEquals(ConcreteGuiViewPanel.moveX, 0);
//    c.moveXToLeft().run();
//    assertEquals(ConcreteGuiViewPanel.moveX, -1);
//    c.moveXToRight().run();
//    assertEquals(ConcreteGuiViewPanel.moveX, 0);
//    c.moveXToRight().run();
//    assertEquals(ConcreteGuiViewPanel.moveX, 0);
//    c.moveXToRightMost().run();
//    assertEquals(ConcreteGuiViewPanel.moveX, -48);
//    c.moveXToLeftMost().run();
//    assertEquals(ConcreteGuiViewPanel.moveX, 0);
//
//  }
//
//  @Test
//  public void testMoveOnY() {
//    assertEquals(ConcreteGuiViewPanel.moveY, 0);
//    c.moveYToTop().run();
//    assertEquals(ConcreteGuiViewPanel.moveY, -1);
//    c.moveYToBottom().run();
//    assertEquals(ConcreteGuiViewPanel.moveY, 0);
//    c.moveYToBottom().run();
//    assertEquals(ConcreteGuiViewPanel.moveY, 0);
//    c.moveYToBottomMost().run();
//    assertEquals(ConcreteGuiViewPanel.moveY, -6);
//    c.moveYToTopMost().run();
//    assertEquals(ConcreteGuiViewPanel.moveY, 0);
//  }
//
//
//  @Test
//  public void testMoveANote() {
//    assertEquals(0, model.getEditor().keySet().size());
//    c.createNote(noteE).run();
//    assertEquals(1, model.getEditor().get(0).size());
//    c.moveANote(0, 28, 1, 29);
//    assertEquals(0, model.getEditor().get(0).size());
//    assertEquals(1, model.getEditor().get(1).size());
//    assertEquals("F2", model.getEditor().get(1).get(0).getPitch().toString());
//  }
//
//
//  @Test
//  public void testChangeTime() {
//    assertEquals(0, model.getCurrentTime());
//    c.changeTime(10).run();
//    assertEquals(10, model.getCurrentTime());
//
//  }
//}
