package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private GameWorld gw;

	public ExitCommand(GameWorld command) {
		super("Exit");
		gw = command;
	}

	public void actionPerformed(ActionEvent evt) {
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to exit (Yes/No)", "Yes", "No");
		if (bOk) {
			Display.getInstance().exitApplication();
		}
	}
}
