package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command {
	private GameWorld gw;

	public AboutCommand(GameWorld command) {
		super("About Page");
		this.gw = command;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String s = "Welcome the FlagbyFlag game about page use the Buttons, Keybinds, and "
				+ "Menu to engage in this game from more information about keybinds go the " + "the help page";
		Dialog.show("About", s, "Ok", null);
	}
}
