/**
 * This is the main class that contains the main method and instantiate the model, view, controller.
 *
 * @author Ali Hajimirza, Logan Testi, Reggie Renteria
 */

package com.halloween_tower_defense;

import com.halloween_tower_defense.controllers.DriverController;
import com.halloween_tower_defense.models.DriverModel;
import com.halloween_tower_defense.views.DriverView;

public class App {
  private final DriverModel model;
  private final DriverView view;
  private final DriverController controller = new DriverController();

  public App() {
    this.model = new DriverModel();
    this.view = new DriverView(model);
    this.controller.setModel(model);
    this.controller.setView(view);
  }

  /**
   * Main method.
   *
   * @param args
   */
  public static void main(String... args) {
    new App();
  }

  /**
   * Method to check if the machine is Mac or not.
   *
   * @return boolean
   */
  public static boolean isMac() {
    return System.getProperty("os.name").substring(0, 3).equalsIgnoreCase("Mac");
  }
}
