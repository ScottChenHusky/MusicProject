package cs3500.music.view;

import cs3500.music.model.*;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent the ViewModel abstract class
 */
public abstract class ViewModel {
  private final TreeMap<Integer, ArrayList<Note>> viewEditor;
  private final int viewWidth;
  private final int viewHeight;
  private final Pitch viewBotPitch;
  private final int viewTime;
  private final long viewTempo;
  private final Model.Status state;
  private final ArrayList<RepeatSign> vlos;
  private final Ending vends;
  private final int viewLeftEndBound;

  /**
   * to construct a ViewModel
   *
   * @param viewEditor   the ViewModel editor
   * @param viewWidth    the ViewModel width
   * @param viewHeight   the ViewModel height
   * @param viewBotPitch the ViewModel botPitch
   * @param viewTime     the ViewModel time
   * @param viewTempo    the ViewModel tempo
   */
  protected ViewModel(TreeMap<Integer, ArrayList<Note>> viewEditor, int viewWidth, int viewHeight,
                      Pitch viewBotPitch, int viewTime, long viewTempo, Model.Status state,
                      ArrayList<RepeatSign> los, Ending vends, int viewLeftEndBound) {
    this.viewEditor = viewEditor;
    this.viewWidth = viewWidth;
    this.viewHeight = viewHeight;
    this.viewBotPitch = viewBotPitch;
    this.viewTime = viewTime;
    this.viewTempo = viewTempo;
    this.state = state;
    this.vlos = los;
    this.vends = vends;
    this.viewLeftEndBound = viewLeftEndBound;
  }

  /**
   * to get the tempo
   *
   * @return the tempo in long
   */
  public long getViewTempo() {
    return viewTempo;
  }

  /**
   * to get the editor
   *
   * @return the treeMap editor
   */
  public TreeMap<Integer, ArrayList<Note>> getViewEditor() {
    return viewEditor;
  }

  /**
   * to get the width
   *
   * @return the width in int
   */
  public int getViewWidth() {
    return viewWidth;
  }

  /**
   * to get the height
   *
   * @return the height in int
   */
  public int getViewHeight() {
    return viewHeight;
  }

  /**
   * to get the botPitch
   *
   * @return the botPitch
   */
  public Pitch getViewBotPitch() {
    return viewBotPitch;
  }

  /**
   * to get the time
   *
   * @return the time in int
   */
  public int getViewPosition() {
    return viewTime;
  }

  /**
   * to get the state of this cs3500.music.model
   */
  public Model.Status getState() {
    return state;
  }

  public ArrayList<RepeatSign> getVlos() {
    return this.vlos;
  }

  public Ending getEnding() {
    return this.vends;
  }

  public int getViewLeftEndBound() {
    return this.viewLeftEndBound;
  }
}
