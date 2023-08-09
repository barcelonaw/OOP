package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	// Create label for the value of the each label var
	private Label timeValue;
	private Label livesValue;
	private Label flagValue;
	private Label foodValue;
	private Label healthValue;
	private Label soundValue;

	public ScoreView(Observable Object) {
		Object.addObserver(this);
		setLayout(new FlowLayout(CENTER));
		// Create a label for time
		Label time = new Label("    Time:");
		time.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeValue = new Label("");
		timeValue.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeValue.getAllStyles().setPadding(LEFT, 2);
		timeValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(time);
		this.add(timeValue);

		// Create a label for lives left
		Label lives = new Label(" Lives Left:");
		lives.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		livesValue = new Label("");
		livesValue.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		livesValue.getAllStyles().setPadding(LEFT, 2);
		livesValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(lives);
		this.add(livesValue);

		// Create a label for last flag reached
		Label flagReached = new Label(" Last Flag Reached: ");
		flagReached.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		flagValue = new Label("");
		flagValue.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		flagValue.getAllStyles().setPadding(LEFT, 2);
		flagValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(flagReached);
		this.add(flagValue);

		// Create a label for food level
		Label food = new Label(" Food Level:");
		food.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodValue = new Label("");
		foodValue.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodValue.getAllStyles().setPadding(LEFT, 2);
		foodValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(food);
		this.add(foodValue);

		// Create a label for health
		Label health = new Label(" Health Level: ");
		health.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthValue = new Label("");
		healthValue.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthValue.getAllStyles().setPadding(LEFT, 2);
		healthValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(health);
		this.add(healthValue);

		// Create a label for sound
		Label sound = new Label(" Sound: ");
		sound.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundValue = new Label("");
		soundValue.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundValue.getAllStyles().setPadding(LEFT, 2);
		soundValue.getAllStyles().setPadding(RIGHT, 5);
		this.add(sound);
		this.add(soundValue);

	}

	@Override
	public void update(Observable observable, Object data) {
		GameWorld gw = (GameWorld) observable;
		timeValue.setText("" + (gw.getTime()));
		foodValue.setText("" + (gw.getFood()));
		livesValue.setText("" + (gw.getLives()));
		healthValue.setText("" + (gw.getHealth()));
		flagValue.setText("" + (gw.getLastFlag()));
		if (gw.getSound()) {
			this.soundValue.setText("ON");

		} else {
			this.soundValue.setText("OFF");

		}
		// revalidate makes sure that it updates properly
		this.revalidate();
		this.repaint();
		gw.Display();
	}

}
