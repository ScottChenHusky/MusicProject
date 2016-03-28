package cs3500.music.model;

/**
 * Created by ScottChen on 15/12/12.
 */

/**
 * to represent a repeat sign
 */
public final class RepeatSign {
  private final int startAt;
  private final int signAt;
  private boolean used;

  //INVARIANT(1): this.startAt >= 0;
  //INVARIANT(2): this.startAt <= signAt
  //INVARIANT(3): this.signAt >= 0;
  //INVARIANT(4): this.signAt <= Model.length;
  //INVARIANT(5): this.signAt and this.startAt are multiples of 4.

  /**
   * to construct a repeat sign
   * @param startAt the back to this time
   * @param signAt the repreat sign is at this time
   */
  public RepeatSign(int startAt, int signAt) {
    if (startAt < 0 || signAt < 0 || startAt > signAt || signAt%4 != 0 || startAt%4 != 0) {
      throw new IllegalArgumentException("Illegal Sign");
    }
    this.startAt = startAt;
    this.signAt = signAt;
    this.used = false;
  }

  /**
   * if the repeat sign is used
   */
  public void passed() {
    this.used = !this.used;
  }

  /**
   * to get the start time
   * @return an integer of the start time
   */
  public int getStartAt() {
    return startAt;
  }

  /**
   * to get where the sign is
   * @return an integer of the position of the sign
   */
  public int getSignAt() {
    return signAt;
  }

  /**
   * to get is the sign used
   * @return a boolean to show is the sign used
   */
  public boolean isUsed() {
    return used;
  }
}
