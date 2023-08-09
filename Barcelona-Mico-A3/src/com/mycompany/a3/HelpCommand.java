package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command {
	private GameWorld gw;

	public HelpCommand(GameWorld command) {
		super("Help");
		this.gw = command;

	}

	public void actionPerformed(ActionEvent evt) {
		String h = "Welcome to the Help Section of the program\n";
		String c = "Keybinds:\n " + "\'A\' = Accelerate\n" + "\'B\' = Brake\n" + "\'R\' = Right Turn\n"
				+ "\'L\' = Left Turn\n" + "\'S\' = Cllide with Spider\n" + "\'F\' = Collide with FoodStation\n"
				+ "\'T\' = Tick\n";
		// Prints out the dialog on screen
		Dialog.show("Help", h + c, "Ok", null);
	}
}