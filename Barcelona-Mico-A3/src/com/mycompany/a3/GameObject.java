package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

public abstract class GameObject implements IDrawable, ICollider {

	private Point location;
	private int myColor;
	private int size;
	private Vector<GameObject> collsions;

	public GameObject() {

	}

	public GameObject(float x, float y, int size, int r, int g, int b) {
		this.location = new Point(x, y);
		this.myColor = ColorUtil.rgb(r, g, b);
		this.size = size;
		this.collsions = new Vector<GameObject>();

	}

	public double getLocationX() {

		return location.getX();

	}

	public double getLocationY() {

		return location.getY();

	}

	public int getSize() {
		return this.size;
	}

	public Point getLocation() {
		return location;

	}

	public void setLocation(float x, float y) {
		location.setX((float) ((Math.round(x) * 10.0) / 10.0));
		location.setY((float) ((Math.round(y) * 10.0) / 10.0));

	}

	public int getColor() {
		return this.myColor;

	}

	public void setColor(int r, int g, int b) {
		this.myColor = ColorUtil.rgb(r, g, b);
	}

	public String toString() {
		String s = "loc = " + location.getX() + "," + location.getY() + " Color = [" + ColorUtil.red(myColor) + ","
				+ ColorUtil.green(myColor) + "," + ColorUtil.blue(myColor) + "]" + " Size = " + this.getSize();

		return s;
	}

	public Vector<GameObject> getObjectsCollide() {

		return this.collsions;
	}

	@Override
	public boolean collidesWith(GameObject other) {
		boolean r = false;
		float thisCenterX = this.getLocation().getX();
		float thisCenterY = this.getLocation().getY();

		float otherCenterX = (other).getLocation().getX();
		float otherCenterY = (other).getLocation().getY();

		float dx = thisCenterX - otherCenterX;
		float dy = thisCenterY - otherCenterY;
		float distBetweenCentersSqr = (dx * dx + dy * dy);

		int thisRadius = this.getSize() / 2;
		int otherRadius = (other).getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		if (distBetweenCentersSqr <= radiiSqr) {
			r = true;
		}
		return r;
	}

	public void NotCollision(GameObject otherObj) {
		if (getObjectsCollide().contains(otherObj)) {
			this.collsions.remove(otherObj);
			otherObj.collsions.remove(this);
		}

	}
}
