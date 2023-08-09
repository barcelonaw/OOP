package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command {
	private GameWorld gw;

	public TurnRightCommand(GameWorld gw) {
		super("Right Turn");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Ant has gone 5 degrees towards the right");
		gw.turnR();
	}
}
