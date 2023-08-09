package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command {
	private Game g;
	private GameWorld gw;
	private ButtonCommand b;

	public PauseCommand(GameWorld gw, Game g, ButtonCommand b) {
		super("pause");
		this.g = g;
		this.gw = gw;
		this.b = b;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		g.pauseG();

	}

}
