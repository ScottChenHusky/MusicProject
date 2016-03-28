package cs3500.music.model;


/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent a pitch
 */
public final class Pitch {
  private final String pitchType;
  private final int octave;
  private final static String[] pitchTypes =
          new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

  //INVARIANT 1: pitchType has to be one of pitchTypes

  /**
   * to construct a Pitch
   *
   * @param pitchType the pitch type
   * @param octave    the octave of this pitch
   */
  public Pitch(String pitchType, int octave) {
    boolean pitchInside = false;
    for (int i = 0; i < pitchTypes.length; i++) {
      pitchInside = pitchInside || pitchTypes[i] == pitchType;
    }
    if (!pitchInside) {
      throw new IllegalArgumentException("Illegal Pitch");
    }
    this.pitchType = pitchType;
    this.octave = octave;
  }

  /**
   * convert this pitch a its integer value
   *
   * @return the integer representation of this pitch
   */
  public int pitchToInt() {
    int y = 0;
    for (int i = 0; i < pitchTypes.length; i++) {
      if (this.pitchType.equals(pitchTypes[i])) {
        y = i;
      }
    }
    y = y + this.octave * 12;
    return y;
  }

  /**
   * convert this int to its pitch value
   *
   * @return the pitch representation of this integer
   */
  public static Pitch intToPitch(int y) {
    if (y < 0) {
      return new Pitch(pitchTypes[(12 - (-y % 12)) % 12], (y - 11) / 12);
    } else {
      return new Pitch(pitchTypes[y % 12], y / 12);
    }
  }

  @Override
  public String toString() {
    return this.pitchType + this.octave;
  }
}
