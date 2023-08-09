package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

/**
 * The SoundCommand class represents a Command to turn the sound ON/OFF in the
 * game. It handles the user's selection of a checkbox for the sound setting.
 * The class interacts with the GameWorld class to turn the sound on/off and
 * play/pause the background sound.
 */
public class SoundCommand extends Command {
	private GameWorld gw;

	public SoundCommand(GameWorld gw) {
		super("Sound");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		CheckBox checkBox = (CheckBox) evt.getComponent(); // Gets the checkbox that triggered the event
		if (checkBox.isSelected()) {
			gw.setSound(true);
			gw.getBgSound().play();
		} else {
			gw.setSound(false);
			gw.getBgSound().pause();
		}
	}
}
