package cs3500.music.controller;

import cs3500.music.view.View;
import cs3500.music.view.ViewModel;
import cs3500.music.model.Model;
import cs3500.music.model.Note;
import cs3500.music.view.CompositeView;
import cs3500.music.view.ConcreteGuiViewPanel;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by leyiqiang on 11/22/15.
 */

/**
 * to represent a Controller
 */
public final class Controller {
  private Model model;
  private ViewModel vm;
  public GuiViewFrame gv;
  private CompositeView cv;
  private MidiViewImpl mv;
  public Appendable output;

  /**
   * to construct a Controller
   *
   * @param model pass in a Model to communicate with the controller
   */
  public Controller(Model model) {
    this.model = model;
    this.vm = View.ModelToViewModel(model);
    this.gv = new GuiViewFrame(vm);
    this.mv = new MidiViewImpl(vm);
    this.cv = new CompositeView(gv, mv);
    this.output = new StringBuilder("");
  }

  /**
   * to add a KeyListener
   *
   * @param kl the KeyListener needed to add
   */
  public void addKeyListener(KeyListener kl) {
    gv.addKeyListener(kl);
  }

  /**
   * let the client to remove the KeyListener
   *
   * @param kl the KeyListener needed to remove
   */
  public void removeKeyListener(KeyListener kl) {
    gv.removeKeyListener(kl);
  }

  /**
   * to add a MouseListener
   *
   * @param ml the MouseListener needed to add
   */
  public void addMouseListener(MouseListener ml) {
    gv.addMouseListener(ml);
  }

  /**
   * let the client to remove the MouseListener
   *
   * @param ml the MouseListener needed to remove
   */
  public void removeMouseListener(MouseListener ml) {
    gv.removeMouseListener(ml);
  }

  /**
   * to create a note on the board
   *
   * @param note the note needed to create
   * @return A runnable with the new note
   */
  public Runnable createNote(Note note) {
    Model model = this.model;
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key c run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        model.addNote(note);
        vm = View.ModelToViewModel(model);
        GuiViewFrame.vm = vm;
        gv.refresh();
      }
    };
  }

  /**
   * to pause or to play the music
   *
   * @return a runnable which is paused or palying
   */
  public Runnable pauseAndPlay() {
    Model model = this.model;
    model.pauseAndPlay();
    GuiViewFrame.vm = View.ModelToViewModel(model);
    GuiViewFrame gv = this.gv;
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key space run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        model.pauseAndPlay();
        vm = View.ModelToViewModel(model);
        cv.pauseAndPlay();
        gv.refresh();
      }
    };
  }

  /**
   * to remove all the notes which are chosen
   *
   * @param lop list of posns
   * @return a runnable whcih has removed all the chosen notes
   */
  public Runnable removeAll(ArrayList<Integer> lop) {
    Model model = this.model;
    GuiViewFrame gv = this.gv;
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key r run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        int idx = 0;
        while (idx < lop.size() - 1) {
          model.removeNote(lop.get(idx), lop.get(idx + 1));
          vm = View.ModelToViewModel(model);
          gv.refresh();
          idx = idx + 2;
        }
      }
    };
  }

  /**
   * move the background to the right
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveXToLeft() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key right run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        if ((vm.getViewWidth() + ConcreteGuiViewPanel.moveX) >= 16) {
          gv.moveBGToLeft();
        }
      }
    };
  }

  /**
   * move the background to the left
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveXToRight() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key left run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        if ((ConcreteGuiViewPanel.moveX) < 0) {
          gv.moveBGToRight();
        }
      }
    };
  }

  /**
   * jump to the right most position
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveXToRightMost() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key d run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        gv.moveBGToRightMost();
      }
    };
  }

  /**
   * jump to the left most position
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveXToLeftMost() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key a run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        gv.moveBGToLeftMost();
      }
    };
  }

  /**
   * move the background to the bottom
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveYToBottom() {
    return new Runnable() {
      @Override
      public void run() {
        try {
          output = new StringBuilder();
          output.append("Key up run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (ConcreteGuiViewPanel.moveY < 0) {
          gv.moveBGToTop();
        }
      }
    };
  }

  /**
   * move the background to the top
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveYToTop() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key down run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (ConcreteGuiViewPanel.moveY + vm.getViewHeight() > 10) {
          gv.moveBGToBottom();
        }
      }
    };
  }

  /**
   * jump to the top most position
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveYToTopMost() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key w run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        gv.moveBGToTopMost();
      }
    };
  }

  /**
   * jump to the lowest position
   *
   * @return a new Runnable with the new position
   */
  public Runnable moveYToBottomMost() {
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key s run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        gv.moveBGToBottomMost();
      }
    };
  }

  /**
   * to move a note from (x1, y1) to (x2, y2)
   *
   * @param x1 the original x position
   * @param y1 the original y position
   * @param x2 new x position
   * @param y2 new y position
   */
  public void moveANote(int x1, int y1, int x2, int y2) {
    model.moveNote(x1, y1, x2, y2);
    vm = View.ModelToViewModel(model);
    GuiViewFrame.vm = vm;
    gv.refresh();
  }

  /**
   * to change the current time
   *
   * @param time the given new time
   * @return a runnable with the new given time
   */
  public Runnable changeTime(int time) {
    return new Runnable() {
      @Override
      public void run() {
        model.changeCurrentTime(time);
        vm = View.ModelToViewModel(model);
        GuiViewFrame.vm = vm;
        cv.changeCurrentTime(time);
        gv.moveOnTime(time);
        gv.refresh();
      }
    };
  }

  //"e" ends, "b" left bound

  public Runnable addRepeat(ArrayList<Integer> los) {
    Model model = this.model;
    GuiViewFrame gv = this.gv;
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key l run");
        } catch (IOException e) {
          e.printStackTrace();
        }
        int idx = 0;
        while (idx < los.size() - 1) {
          model.addRepeatSign(los.get(idx), los.get(idx + 1));
          vm = View.ModelToViewModel(model);
          gv.refresh();
          idx = idx + 2;
        }
      }
    };
  }

  public Runnable addEnds(ArrayList<Integer> los) {
    Model model = this.model;
    GuiViewFrame gv = this.gv;
    return new Runnable() {
      @Override
      public void run() {
        output = new StringBuilder();
        try {
          output.append("Key e run");
        } catch (IOException e) {
          e.printStackTrace();
        }
          model.addEndings(los);
         vm = View.ModelToViewModel(model);
          gv.refresh();

      }
    };
  }


  public Runnable changeLeftBound(int time) {
    return new Runnable() {
      @Override
      public void run() {
        model.setLeftEndBound(time);
        vm = View.ModelToViewModel(model);
        GuiViewFrame.vm = vm;
        gv.refresh();
      }
    };
  }
}
