package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {
	public int capacity;

	private GameWorld gw;

	public FoodStation(float x, float y, int size, GameWorld gw) {
		super(x, y, size, 0, 255, 0);
		this.capacity = this.getSize();
		setColor(0, 255, 0);
		this.gw = gw;

	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String toString() {
		String SuperS = super.toString();
		return "Foodstation: " + SuperS + " capacity = " + this.capacity;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int x = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int y = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		int size = this.getSize();
		int halfSize = size / 2;

		g.setColor(this.getColor());
		if (super.isSelected()) {
			g.drawRect(x - halfSize, y - halfSize, size, size);
		} else {
			g.fillRect(x - halfSize, y - halfSize, size, size);
		}

		g.setColor(ColorUtil.BLACK);
		g.drawString(Integer.toString(this.getCapacity()), x - halfSize, y - halfSize);
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		// if hasn't handled collision
		if (!this.getObjectsCollide().contains(otherObject)) {
			if (otherObject instanceof Ant)
				gw.collideFood((FoodStation) this);
			this.getObjectsCollide().add(otherObject);
			otherObject.getObjectsCollide().add(this);

		}

	}

}
