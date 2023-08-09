package com.mycompany.a3;

import com.codename1.ui.geom.Point;

public abstract class Fixed extends GameObject implements ISelectable {

	private boolean isSelected = false;

	public Fixed(float x, float y, int size, int r, int g, int b) {
		super(x, y, size, r, g, b);
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();

		int xLoc = (int) (pCmpRelPrnt.getX() + this.getLocation().getX() - (this.getSize() / 2));
		int yLoc = (int) (pCmpRelPrnt.getY() + this.getLocation().getY() - (this.getSize() / 2));

		return px >= xLoc && px <= xLoc + this.getSize() && py >= yLoc && py <= yLoc + this.getSize();
	}

	@Override
	public boolean isSelected() {
		return this.isSelected;
	}

	@Override
	public void setSelected(boolean b) {
		this.isSelected = b;
	}
}
