package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point2D;

public class Spider extends MoveableGameObject {
	private GameWorld gw;

	public Spider(float x, float y, int size, int heading, int speed) {
		super(x, y, size, heading, speed, 0, 0, 0);
		// set spider to black
		setColor(0, 0, 0);
		// set location
		setLocation(x, y);
	}

	@Override
	public void Move(int elapseTime, Dimension dCmpSize) {
		super.Move(elapseTime, dCmpSize);

	}

	public String toString() {
		String s = super.toString();
		return "Spider: " + s;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLoc = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		int[] xPoints = { xLoc, (xLoc - getSize() / 2), (xLoc + getSize() / 2) };
		int[] yPoints = { (yLoc + getSize() / 2), (yLoc - getSize() / 2), (yLoc - getSize() / 2) };
		g.setColor(ColorUtil.BLACK);
		g.drawPolygon(xPoints, yPoints, 3);
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		if (!this.getObjectsCollide().contains(otherObject) && otherObject.getObjectsCollide().contains(this)) {
			if (otherObject instanceof Ant)
				gw.collideSpider();

		}
		otherObject.getObjectsCollide().add(this);
		this.getObjectsCollide().add(otherObject);
	}
}