package cs3500.music.view;

/**
 * Created by ScottChen on 15/11/19.
 */

import cs3500.music.model.Model;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.view.View;
import cs3500.music.view.ViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * to represent a ConsoleView
 */
public final class ConsoleView implements View {
  private final Appendable output;
  private final Formatter formatter;
  private final ViewModel vm;

  public static Builder builder() {
    return new Builder();
  }

  /**
   * to construct a ConsoleView
   *
   * @param output is the output
   * @param vm     is the ViewModel
   */
  protected ConsoleView(Appendable output, ViewModel vm) {
    this.output = output;
    this.formatter = new Formatter(output);
    this.vm = vm;
  }

  @Override
  public void simulate(String type) throws IOException {
    output.append("    ");
    int p = vm.getViewBotPitch().pitchToInt();
    for (int i = p; i < p + vm.getViewHeight(); i++) {
      String s = Pitch.intToPitch(i).toString();
      if (s.length() == 2) {
        output.append(" " + s);
      } else {
        output.append(s);
      }
    }
    char[][] grid = new char[this.vm.getViewWidth()][this.vm.getViewHeight() * 3]; // x,y
    for (int i = 0; i < this.vm.getViewWidth(); i++) {
      Arrays.fill(grid[i], ' ');
    }
    Set<Integer> keys = this.vm.getViewEditor().keySet();
    for (Integer key : keys) {
      ArrayList<Note> notes = this.vm.getViewEditor().get(key);
      for (int i = 0; i < notes.size(); i++) {
        Note n = notes.get(i);
        int dur = n.getDuration();
        for (int j = 0; j < dur; j++) {
          if (j == 0) {
            grid[n.getTime() + j][(n.getPitch().pitchToInt() -
                    this.vm.getViewBotPitch().pitchToInt()) * 3] = 'X';
          } else {
            grid[n.getTime() + j][(n.getPitch().pitchToInt() -
                    this.vm.getViewBotPitch().pitchToInt()) * 3] = '|';
          }
        }
      }
    }

    int drawTime = 0;
    while (drawTime < vm.getViewWidth()) {
      StringBuilder sb = new StringBuilder();
      char[] row = grid[drawTime];
      sb.append(row);
      output.append("\n");
      formatter.format("%4d", drawTime);
      output.append(" ");
      output.append(sb.toString());
      drawTime++;
    }
  }

  /**
   * move the cs3500.music.view on time
   *
   * @param time the time of the composition
   */
  @Override
  public void moveOnTime(int time) {

  }

  /**
   * the builder of ConsoleView
   */
  public static final class Builder {
    private Appendable output;
    private ViewModel vm;

    protected Builder() {
      this.output = System.out;
      this.vm = View.ModelToViewModel(Model.builder().build());
    }

    /**
     * set the output for the ConsoleView
     *
     * @param output the output of the cs3500.music.console cs3500.music.view
     * @return the builder of the cs3500.music.console cs3500.music.view
     */
    public Builder setOutput(Appendable output) {
      this.output = requireNonNull(output);
      this.output = output;
      return this;
    }

    /**
     * set the cs3500.music.view cs3500.music.model of the ConsoleView
     *
     * @param vm the cs3500.music.view cs3500.music.model
     * @return the builder of the cs3500.music.console cs3500.music.view
     */
    public Builder setViewModel(ViewModel vm) {
      this.vm = vm;
      return this;
    }

    /**
     * to build the ConsoleView
     *
     * @return a cs3500.music.view with the given inputs
     */
    public View build() {
      return new ConsoleView(this.output, this.vm);
    }
  }


}
