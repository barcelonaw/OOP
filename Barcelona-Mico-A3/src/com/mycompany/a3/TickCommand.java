package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;

public class TickCommand extends Command {
	private GameWorld gw;

	public TickCommand(GameWorld gw) {
		super("Tick");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		System.out.println("Clock has ticked by 1");
		Dimension dCmpSize = new Dimension(gw.getWidth(), gw.getHeight());

		gw.tick(20, dCmpSize);

	}
}
