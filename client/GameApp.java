package GameApp.client;

import GameApp.client.core.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A class responsible for starting up the window for graphical user interface
 * @author Adrian Bugiel
 * @version 1.0
 */

public class GameApp extends Application {

  /**
   * Starts up the window for graphical user interface.
   * @param stage the window for graphical user interface
   */
  @Override
  public void start(Stage stage) {
    ViewHandler.getInstance().start();
  }
}
