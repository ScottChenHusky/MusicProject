package cs3500.music.model;

/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent a note
 */
public final class Note {
  private final int duration;
  private final int time;
  private final Pitch pitch;
  private final int instrument;
  private final int volume;

  //INVARIANT 1: duration >= 0;
  //INVARIANT 2: time >= 0;

  /**
   * to construct a Note
   *
   * @param duration   the duration of a note
   * @param time       the x position of a note
   * @param pitch      the y position of a note
   * @param instrument the sound of a note
   * @param volume     the volume of a note
   */
  public Note(int duration, int time, Pitch pitch, int instrument, int volume) {
    if (duration < 0 || time < 0) {
      throw new IllegalArgumentException("Illegal Note");
    }
    this.duration = duration;
    this.time = time;
    this.pitch = pitch;
    this.instrument = instrument;
    this.volume = volume;
  }

  /**
   * to get the x position of this note
   *
   * @return playing time of this note in int
   */
  public int getTime() {
    return time;
  }

  /**
   * to get the duration of this note
   *
   * @return the duration of this note in int
   */
  public int getDuration() {
    return duration;
  }

  /**
   * to get the pitch of this note
   *
   * @return the Pitch of this note in Pitch(enum)
   */
  public Pitch getPitch() {
    return pitch;
  }

  /**
   * to get the type of this instrument
   *
   * @return the instrument type in int
   */
  public int getInstrument() {
    return instrument;
  }

  /**
   * to get the volume of this note
   *
   * @return the volume of this note in int
   */
  public int getVolume() {
    return volume;
  }
}
