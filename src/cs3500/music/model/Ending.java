package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by ScottChen on 15/12/12.
 */

/**
 * to represent endings
 */
public final class Ending {
  private final ArrayList<Integer> endings;
  private int atEnding;

  /**
   * to construct endings
   * @param endings the start time of endings
   */
  public Ending(ArrayList<Integer> endings) {
    for (int time : endings) {
      if (time % 4 != 0) {
        throw new IllegalArgumentException("Illegal Ending");
      }
    }
    this.endings = this.removeDul(endings);
    Collections.sort(this.endings);
    this.atEnding = 0;
  }

  /**
   * to remove duplicated inputs
   * @param loi list of integers
   * @return
   */
  public ArrayList<Integer> removeDul(ArrayList<Integer> loi) {
    ArrayList<Integer> answer = new ArrayList<Integer>();
    for (Integer i : loi) {
      if (!answer.contains(i)) {
        answer.add(i);
      }
    }
    return answer;
  }

  /**
   * to add more endings
   * @param is integers
   */
  public void addEndings(ArrayList<Integer> is) {
    for (Integer i : is) {
      this.endings.add(i);
    }
    Collections.sort(this.removeDul(this.endings));
  }

  /**
   * to get the endings of this
   * @return list of endings
   */
  public ArrayList<Integer> getEndings() {
    return endings;
  }

  /**
   * to get the at ending of this
   * @return at ending
   */
  public int getAtEnding() {
    return atEnding;
  }
}
