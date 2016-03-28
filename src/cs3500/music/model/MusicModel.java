package cs3500.music.model;

import cs3500.music.util.CompositionBuilder;

import java.util.ArrayList;
import java.util.IllformedLocaleException;
import java.util.TreeMap;

/**
 * Created by ScottChen on 15/11/19.
 */

/**
 * to represent a MusicModel
 */
public final class MusicModel implements Model {
  private TreeMap<Integer, ArrayList<Note>> editor;
  private Status state;
  private int currentTime;
  private long tempo;
  private int currentWidth = DEFAULT_WIDTH;
  private int currentHeight = DEFAULT_HEIGHT;
  private Pitch botPitch = BOTPITCH;
  private Note storedNote;
  private ArrayList<RepeatSign> los;
  private Ending ends;
  private int leftEndBound;

  // INVARIANT 1: 0 <= currentTime <= this.currentWidth
  // INVARIANT 2: DEFAULT_WIDTH <= this.currentWidth
  // INVARIANT 3: DEFAULT_HEIGHT <= this.currentHeight

  /**
   * to construct a MusicModel
   *
   * @param editor        the board of this MusicModel
   * @param state         the state of this MusicModel
   * @param currentTime   the currentTime of this MusicModel
   * @param tempo         the tempo of this MusicModel
   * @param currentWidth  the currentWidth of this MusicModel
   * @param currentHeight the currentHeight of this MusicModel
   */
  private MusicModel(TreeMap<Integer, ArrayList<Note>> editor, Status state, int currentTime,
                     long tempo, int currentWidth, int currentHeight, Pitch botPitch,
                     ArrayList<RepeatSign> los, Ending ends, int leftEndBound) {
    if (currentTime < 0 || currentTime > currentWidth ||
            currentWidth < 0 || currentHeight < 0) {
      throw new IllegalArgumentException("Illegal Model");
    }

    this.editor = editor;
    this.state = state;
    this.currentTime = currentTime;
    this.tempo = tempo;
    this.currentWidth = currentWidth;
    this.currentHeight = currentHeight;
    this.botPitch = botPitch;
    this.los = los;
    this.ends = ends;
    this.leftEndBound = leftEndBound;
  }


  @Override
  public boolean isEnd() {
    return this.state == Status.STOPPED;
  }

  @Override
  public void pauseAndPlay() {
    if (this.state == Status.STOPPED || this.state == Status.PAUSED) {
      this.state = Status.PLAYING;
    } else if (this.state == Status.PLAYING) {
      this.state = Status.PAUSED;
    }
  }

  @Override
  public void toStop() {
    this.state = Status.STOPPED;
    this.currentTime = 0;
  }

  @Override
  public void addNote(Note note) {
    int extraTime = note.getTime() + note.getDuration() - this.currentWidth;
    int extraTop = note.getPitch().pitchToInt() - (this.botPitch.pitchToInt() + this.currentHeight);
    int extraBot = note.getPitch().pitchToInt();
    if (extraTime > 0) {
      this.addColumns(extraTime);
    }
    if (extraTop > 0) {
      this.addTopRows(extraTop + 1);
    }
    if (extraBot < this.botPitch.pitchToInt()) {
      this.addBotRows(Math.abs(extraBot - this.botPitch.pitchToInt()));
    }
    int noteTime = note.getTime();
    ArrayList<Note> treeValue = new ArrayList<Note>();
    if (this.editor.get(noteTime) == null) {
      treeValue.add(note);
      this.editor.put(noteTime, treeValue);
    } else {
      this.editor.get(noteTime).add(note);
    }
  }

  @Override
  public void removeNote(int x, int y) {
    ArrayList<Note> treeValue = this.editor.get(x);
    if (treeValue == null) {
      throw new IllegalArgumentException("There is no such a note x");
    }
    int check = 0;
    for (int i = 0; i < treeValue.size(); i++) {
      if (treeValue.get(treeValue.size() - i - 1).getPitch().pitchToInt() == y) {
        this.storedNote = treeValue.get(treeValue.size() - i - 1);
        treeValue.remove(treeValue.size() - i - 1);
        check = 1;
        break;
      }
    }
    if (check == 0) {
      throw new IllegalArgumentException("There is no such a note y");
    }
  }

  @Override
  public void changeCurrentTime(int time) {
    if (time > this.currentWidth || time < 0) {
      throw new IllegalArgumentException("Illegal time");
    }
    this.currentTime = time;
  }

  @Override
  public ArrayList<Note> getCurrentNotes() {
    if (this.currentTime == this.currentWidth) {
      this.state = Status.STOPPED;
    }
    return this.editor.get(this.currentTime);
  }

  @Override
  public void addTopRows(int numOfRows) {
    this.currentHeight += numOfRows;
  }

  @Override
  public void addBotRows(int numOfRows) {
    this.currentHeight += numOfRows;
    this.botPitch = Pitch.intToPitch(this.botPitch.pitchToInt() - numOfRows);
  }

  @Override
  public void addColumns(int numOfColumns) {
    this.currentWidth += numOfColumns;
  }

  @Override
  public void moveNote(int x1, int y1, int x2, int y2) {
    this.removeNote(x1, y1);
    this.addNote(new Note(storedNote.getDuration(),
            x2, Pitch.intToPitch(y2), storedNote.getInstrument(),
            storedNote.getVolume()));
  }

  @Override
  public void addRepeatSign(int startAt, int signAt) {
    if (signAt > this.currentWidth) {
      throw new IllegalArgumentException("Sign should be in the board");
    }
    this.los.add(new RepeatSign(startAt, signAt));
  }

  @Override
  public void addEndings(ArrayList<Integer> is) {
    this.ends.addEndings(is);
    for(int i = 1; i <is.size() -1; i++) {
      this.los.add(new RepeatSign(leftEndBound, is.get(i)));
    }
  }

  @Override
  public void setLeftEndBound(int leftEndBound) {
    this.leftEndBound = leftEndBound;
  }

  @Override
  public int getLeftEndBound() {
    return this.leftEndBound;
  }

  @Override
  public TreeMap<Integer, ArrayList<Note>> getEditor() {
    return editor;
  }

  @Override
  public Status getState() {
    return state;
  }

  @Override
  public int getCurrentTime() {
    return currentTime;
  }

  @Override
  public long getTempo() {
    return tempo;
  }

  @Override
  public int getCurrentWidth() {
    return currentWidth;
  }

  @Override
  public int getCurrentHeight() {
    return currentHeight;
  }

  @Override
  public Pitch getBotPitch() {
    return botPitch;
  }

  @Override
  public ArrayList<RepeatSign> getLos() {
    return this.los;
  }

  @Override
  public Ending getEnding() {
    return this.ends;
  }

  /**
   * to represent a nest class ModelBuilderImpl
   */
  protected static class ModelBuilderImpl implements ModelBuilder {
    private TreeMap<Integer, ArrayList<Note>> editor;
    private Status state;
    private int currentTime;
    private long tempo;
    private int currentWidth;
    private int currentHeight;
    private Pitch botPitch;
    private ArrayList<RepeatSign> los;
    private Ending ends;
    private int leftEndBound;

    /**
     * to construct a ModelBuilderImpl
     */
    public ModelBuilderImpl() {
      editor = new TreeMap<Integer, ArrayList<Note>>();
      state = Status.STOPPED;
      currentTime = 0;
      tempo = TEMPO;
      currentWidth = DEFAULT_WIDTH;
      currentHeight = DEFAULT_HEIGHT;
      botPitch = BOTPITCH;
      los = new ArrayList<RepeatSign>();
      ends = new Ending(new ArrayList<Integer>());
      leftEndBound = 0;
    }

    @Override
    public Model build() {
      return new MusicModel(this.editor, this.state, this.currentTime, this.tempo,
              this.currentWidth, this.currentHeight, this.botPitch, this.los, this.ends, this.leftEndBound);
    }

    @Override
    public ModelBuilder setStatus(Status status) {
      this.state = status;
      return this;
    }

    @Override
    public ModelBuilder setCurrentTime(int time) {
      this.currentTime = time;
      return this;
    }

    @Override
    public ModelBuilder setCurrentWidth(int width) {
      this.currentWidth = width;
      return this;
    }

    @Override
    public ModelBuilder setCurrentHeight(int height) {
      this.currentHeight = height;
      return this;
    }

    @Override
    public ModelBuilder setTempo(long tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public ModelBuilder setEditor(TreeMap<Integer, ArrayList<Note>> editor) {
      this.editor = editor;
      return this;
    }

    @Override
    public ModelBuilder setListOfRepeatSign(ArrayList<RepeatSign> los) {
      this.los = los;
      return this;
    }

    @Override
    public ModelBuilder setEndings(Ending ends) {
      this.ends = ends;
      return this;
    }

    @Override
    public ModelBuilder setLeftEndBound(int leftEndBound) {
      this.leftEndBound = leftEndBound;
      return this;
    }


  }

  /**
   * help to build a CompositionBuilder
   */
  public static final class Builder implements CompositionBuilder<Model> {
    private TreeMap<Integer, ArrayList<Note>> editor;
    private Status state;
    private int currentTime;
    private long tempo;
    private int currentWidth;
    private int currentHeight;
    private Pitch botPitch;
    private ArrayList<RepeatSign> los;
    private Ending ends;
    private int leftEndBound;

    /**
     * to construct a Builder
     */
    public Builder() {
      this.editor = new TreeMap<Integer, ArrayList<Note>>();
      this.state = Status.STOPPED;
      this.currentTime = 0;
      this.tempo = TEMPO;
      this.currentWidth = DEFAULT_WIDTH;
      this.currentHeight = DEFAULT_HEIGHT;
      this.botPitch = BOTPITCH;
      this.los = new ArrayList<RepeatSign>();
      this.ends = new Ending(new ArrayList<Integer>());
      this.leftEndBound = 0;
    }

    @Override
    public Model build() {
      return new MusicModel(this.editor, this.state, this.currentTime, this.tempo,
              this.currentWidth, this.currentHeight, this.botPitch, this.los, this.ends, this.leftEndBound);
    }

    @Override
    public CompositionBuilder<Model> setTempo(long tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<Model> addNote(int start, int end, int instrument,
                                             int pitch, int volume) {
      Note note = new Note(end - start, start, Pitch.intToPitch(pitch - 12),
              instrument - 1, volume);
      int extraTime = note.getTime() + note.getDuration() - this.currentWidth;
      int extraTop = note.getPitch().pitchToInt() -
              (this.botPitch.pitchToInt() + this.currentHeight);
      int extraBot = note.getPitch().pitchToInt();
      if (extraTime > 0) {
        this.currentWidth += extraTime;
      }
      if (extraTop > 0) {
        this.currentHeight += extraTop + 1;
      }
      if (extraBot < this.botPitch.pitchToInt()) {
        this.currentHeight += Math.abs(extraBot - this.botPitch.pitchToInt());
        this.botPitch = note.getPitch();
      }
      int noteTime = note.getTime();
      ArrayList<Note> treeValue = new ArrayList<Note>();
      if (this.editor.get(noteTime) == null) {
        treeValue.add(note);
        this.editor.put(noteTime, treeValue);
      } else {
        this.editor.get(noteTime).add(note);
      }
      return this;
    }
  }

}
