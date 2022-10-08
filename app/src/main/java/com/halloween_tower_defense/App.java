/**
 * This is the main class that contains the main method and instantiate the model, view, controller
 * @author Ali Hajimirza, Logan Testi, Reggie Renteria
 *
 */
package com.halloween_tower_defense;

import com.halloween_tower_defense.models.DriverModel;
import com.halloween_tower_defense.controllers.DriverController;
import com.halloween_tower_defense.views.DriverView;

public class App {
	public static String OS_NAME;
	private DriverModel model;
	private DriverView view;
	private DriverController controller= new DriverController();


	/**
	 * Constructor
	 */
	public App()
	{
		this.model = new DriverModel();
		this.view = new DriverView(model);
		this.controller.setModel(model);
		this.controller.setView(view);
	}

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String... args)
	{
		if (System.getProperty("os.name").substring(0,3).equalsIgnoreCase("Win")){
			OS_NAME = "Windows";
		}
		else if (System.getProperty("os.name").substring(0,3).equalsIgnoreCase("Mac")) {
//			System.setProperty("apple.laf.useScreenMenuBar", "true");
			OS_NAME = "Mac";
		}
		else if(System.getProperty("os.name").substring(0,3).equalsIgnoreCase("Lin")){
			OS_NAME = "Linux";
		}
		else{
			OS_NAME = "undefined";
		}

		new App();
	}

	/**
	 * Method to check if the machine is Mac or not
	 * @return boolean
	 */
	public static boolean isMac()
	{
	    return OS_NAME.equals("Mac");
	}
}
