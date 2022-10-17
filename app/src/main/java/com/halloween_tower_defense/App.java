/**
 * This is the main class that contains the main method and instantiate the model, view, controller.
 *
 * @author Ali Hajimirza, Logan Testi, Reggie Renteria
 */

package com.halloween_tower_defense;

import com.halloween_tower_defense.controllers.GameController;
import com.halloween_tower_defense.models.GameModel;
import com.halloween_tower_defense.views.GameView;

public class App {

  /**
   * Main method.
   *
   * @param args
   */
  public static void main(String... args) {
    GameModel model = new GameModel();
    GameView view = new GameView(model);

    new GameController(model, view);
  }
}
