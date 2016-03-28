package cs3500.music.model;


import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent the interface of a Model
 */
public interface Model {
  int DEFAULT_WIDTH = 64;
  int DEFAULT_HEIGHT = 16;
  long TEMPO = 200000;
  Pitch BOTPITCH = new Pitch("E", 2);

  /**
   * to get the internal representation of the cs3500.music.model
   *
   * @return the internal representation.
   */
  TreeMap<Integer, ArrayList<Note>> getEditor();

  /**
   * to get the state of this cs3500.music.model
   *
   * @return the state of this cs3500.music.model
   */
  Status getState();

  /**
   * to get the current time of this cs3500.music.model
   *
   * @return the current time of this cs3500.music.model in int
   */
  int getCurrentTime();

  /**
   * to get the tempo of this music
   *
   * @return the tempo of this music in long
   */
  long getTempo();

  /**
   * to get the current width
   *
   * @return the current width in int
   */
  int getCurrentWidth();

  /**
   * to get the current height
   *
   * @return the current height in int
   */
  int getCurrentHeight();

  /**
   * to get the current lowest pitch
   *
   * @return the current lowest pitch
   */
  Pitch getBotPitch();

  /**
   * to get the list of repeat sign
   * @return
   */
  ArrayList<RepeatSign> getLos();

  /**
   * to get endings
   * @return the endings
   */
  Ending getEnding();

  /**
   * to represent three different types of the Music Editor states
   */
  enum Status {
    PLAYING,
    PAUSED,
    STOPPED
  }

  /**
   * to build a ModelBuilder
   *
   * @return a ModelBuilder which helps to build a Model
   */
  static ModelBuilder builder() {
    return new MusicModel.ModelBuilderImpl();
  }


  /**
   * to represent a ModelBuilder interface
   */
  interface ModelBuilder {

    /**
     * to build the Model
     *
     * @return a Model with initialized values
     */
    Model build();

    /**
     * to set the state of a music editor
     *
     * @param status the given state
     * @return a ModelBuilder with the given state
     */
    ModelBuilder setStatus(Status status);

    /**
     * to set the time of a music editor
     *
     * @param time the given time
     * @return a ModelBuilder with the given time
     */
    ModelBuilder setCurrentTime(int time);

    /**
     * to set the width of a music editor
     *
     * @param width the given width
     * @return a ModelBuilder with the given width
     */
    ModelBuilder setCurrentWidth(int width);

    /**
     * to set the height of a music editor
     *
     * @param height the given height
     * @return a ModelBuilder with the given height
     */
    ModelBuilder setCurrentHeight(int height);

    /**
     * to set the tempo of a music editor
     *
     * @param tempo the given tempo
     * @return a ModelBuilder with the given tempo
     */
    ModelBuilder setTempo(long tempo);

    /**
     * to set the editor of a music editor
     *
     * @param editor the given editor
     * @return a ModelBuilder with the given editor
     */
    ModelBuilder setEditor(TreeMap<Integer, ArrayList<Note>> editor);

    /**
     * to set the list of repeat sign
     * @param los list of sign
     * @return a builder with the given list
     */
    ModelBuilder setListOfRepeatSign(ArrayList<RepeatSign> los);

    /**
     * to set the endings of a builder
     * @param ends the given endings
     * @return a builder with the given endings
     */
    ModelBuilder setEndings(Ending ends);

    /**
     * to set the left end bound of a builder
     */
    ModelBuilder setLeftEndBound(int leftEndBound);


  }

  /**
   * Have all the notes been played, or there is no notes
   *
   * @return
   */
  boolean isEnd();

  /**
   * to pause the music when it's playing, or to play the music when it's paused or stopped.
   */
  void pauseAndPlay();

  /**
   * to stop the music
   */
  void toStop();

  /**
   * add the given note to its position
   */
  void addNote(Note note);

  /**
   * to delete a note in the given position
   */
  void removeNote(int x, int y);

  /**
   * change the current time to be the given new time
   *
   * @param time
   */
  void changeCurrentTime(int time);

  /**
   * to get notes which should be playing
   *
   * @return ArrayList<Note>, which is a list of current notes
   */
  ArrayList<Note> getCurrentNotes();

  /**
   * add rows to the top
   *
   * @param numOfRows number of rows that wanted to add
   */
  void addTopRows(int numOfRows);

  /**
   * add rows to the bottom
   *
   * @param numOfRows number of rows that wanted to add
   */
  void addBotRows(int numOfRows);

  /**
   * add columns to the right
   *
   * @param numOfColumns number of columns that wanted to add
   */
  void addColumns(int numOfColumns);


  /**
   * to move a note
   *
   * @param x1 original x
   * @param y1 original y
   * @param x2 moved to x
   * @param y2 moved to y
   */
  void moveNote(int x1, int y1, int x2, int y2);

  /**
   * to add a repeat sign
   * @param startAt where to start
   * @param signAt there the sign is
   */
  void addRepeatSign(int startAt, int signAt);

  /**
   * add list of endings to ending
   * @param is list of endings
   */
  void addEndings(ArrayList<Integer> is);


  /**
   * to set the left end bound of the model
   */
  void setLeftEndBound(int leftEndBound);

  /**
   * to get the left end bound
   */
  int getLeftEndBound();

}
