package com.mycompany.a3;

import com.codename1.ui.geom.Dimension;
import com.codename1.charts.models.Point;

public abstract class MoveableGameObject extends GameObject {
	private int Speed;
	private int Heading;
	private GameWorld gw;
	private Dimension dCmpSize;

	public MoveableGameObject(float x, float y, int size, int heading, int speed, int r, int g, int b) {
		super(x, y, size, r, g, b);
		this.Heading = heading;
		this.Speed = speed;

	}

	public void Move(int elapseTime, Dimension dCmpSize) {
		// Create delta X an Y variables
		float deltaX = 0;
		float deltaY = 0;
		// Create the old and new location variables

		// Point oldLocation = getLocation();
		Point newLocation = new Point(0, 0);
		// Find the angle of theta
		int theta = 90 - this.getHeading();

		// Find delta x and y using the dis*speed*(time/1000) equation

		deltaY = (float) (Math.sin(Math.toRadians(theta)) * getSpeed());// (elapseTime/1000)
		deltaX = (float) (Math.cos(Math.toRadians(theta)) * getSpeed());// (elapseTime/1000)

		// Change the newLocation values using delta x and y and the old locations

		newLocation.setX((float) (deltaX + this.getLocation().getX()));
		newLocation.setY((float) (deltaY + this.getLocation().getY()));

		// replaces the current location to newLocation based on if it hits the moveable
		// x and y is less than 0
		// or is greater equal than the width and height of the mapview custom container
		// then changes the heading so it would stay in the boarder of the mapview
		if (newLocation.getX() + this.getSize() / 2 >= dCmpSize.getWidth()) {
			newLocation.setX(newLocation.getX() - this.getSize() / 2);
			this.setHeading(180 + getHeading());
		} else if (newLocation.getX() < 0) {
			newLocation.setX(((float) 0.0 + this.getSize() / 2));
			this.setHeading(180 + getHeading());
		}
		if (newLocation.getY() + this.getSize() / 2 >= dCmpSize.getHeight()) {
			newLocation.setY(newLocation.getY() - this.getSize() / 2);
			this.setHeading(180 - getHeading());
		} else if (newLocation.getY() < 0) {
			newLocation.setY(((float) 0.0 + this.getSize() / 2));
			this.setHeading(180 - getHeading());
		}
		// set location
		this.setLocation(newLocation.getX(), newLocation.getY());
	}

	public void setSpeed(int s) {
		this.Speed = s;

	}

	public int getSpeed() {
		return this.Speed;
	}

	public int getHeading() {
		return this.Heading;
	}

	public void setHeading(int h) {
		this.Heading = h;
	}

	public String toString() {
		String superS = super.toString();
		String s = " Speed = " + this.Speed + " Heading = " + this.Heading;
		String r = superS + s;
		return r;
	}

}
